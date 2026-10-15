package com.resend.core.net.impl;

import com.resend.core.SdkVersion;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;

import com.resend.core.net.RequestOptions;
import okhttp3.*;

import java.io.File;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * The built-in {@link IHttpClient}, backed by OkHttp.
 *
 * <p>To use your own {@code OkHttpClient} (interceptors, TLS settings, a shared connection pool), pass one to
 * {@link #HttpClient(OkHttpClient, String)} and hand the result to {@code Resend.builder().httpClient(...)}. That
 * requires declaring the {@code com.squareup.okhttp3:okhttp-jvm} dependency in your own build.</p>
 */
public class HttpClient implements IHttpClient<String> {

    /** The base URL for the API. */
    public static final String BASE_API = "https://api.resend.com";

    /** The User-Agent header value for HTTP requests. */
    public static final String USER_AGENT = "resend-java/" + SdkVersion.getVersion();

    private static final long MIN_RETRY_DELAY_MILLIS = 500L;

    private static final long MAX_RETRY_DELAY_MILLIS = 5000L;

    private static final long MAX_RETRY_AFTER_MILLIS = 30000L;

    private static final int MAX_RETRY_AFTER_DIGITS = 9;

    /**
     * Lazily-initialized client shared by every service that isn't given one explicitly, so the whole SDK
     * reuses one OkHttp connection pool and dispatcher by default.
     */
    private static final class DefaultHolder {
        static final HttpClient INSTANCE = new HttpClient();
    }

    /** The OkHttpClient instance for handling HTTP requests. */
    private final OkHttpClient httpClient;

    /** The base URL requests are sent to, without a trailing slash. */
    private final String baseUrl;

    /** The number of retries used when a request doesn't set its own. */
    private final int maxRetries;

    /**
     * Constructs an HttpClient with a new {@link OkHttpClient} that sends requests to {@link #BASE_API}.
     */
    public HttpClient() {
        this(new OkHttpClient());
    }

    /**
     * Constructs an HttpClient that sends requests to {@link #BASE_API} through the given {@link OkHttpClient}.
     *
     * @param okHttpClient The OkHttp client used to execute requests.
     */
    public HttpClient(final OkHttpClient okHttpClient) {
        this(okHttpClient, BASE_API);
    }

    /**
     * Constructs an HttpClient that sends requests to the given base URL through the given {@link OkHttpClient}.
     *
     * @param okHttpClient The OkHttp client used to execute requests.
     * @param baseUrl      The base URL of the Resend API, e.g. {@code https://api.resend.com}.
     * @throws IllegalArgumentException If {@code baseUrl} is not a valid http(s) URL, or has a query or fragment.
     */
    public HttpClient(final OkHttpClient okHttpClient, final String baseUrl) {
        this(okHttpClient, baseUrl, 0);
    }

    /**
     * Constructs an HttpClient that retries failed requests.
     *
     * <p>A request is retried on HTTP 429, on HTTP 5xx and on connection failures (a refused or reset connection, or
     * one closed mid-response), waiting between attempts with exponential backoff, or for the time the
     * {@code Retry-After} header asks for. Timeouts and failures that a retry can't fix, such as an unknown host or
     * a TLS error, are not retried. A {@code POST} is retried on HTTP 429 always, but on HTTP 5xx or a connection
     * failure only when it carries an {@code Idempotency-Key} header, because the request may already have been
     * processed.</p>
     *
     * <p>The waits between attempts block the calling thread and are not covered by the request timeout, which
     * applies to each attempt; interrupting the thread ends the wait.</p>
     *
     * @param okHttpClient The OkHttp client used to execute requests.
     * @param baseUrl      The base URL of the Resend API, e.g. {@code https://api.resend.com}.
     * @param maxRetries   The maximum number of retries per request, unless a request sets its own through
     *                     {@link RequestOptions}. Zero disables retries.
     * @throws IllegalArgumentException If {@code baseUrl} is not a valid http(s) URL, has a query or fragment, or
     *                                  {@code maxRetries} is negative.
     */
    public HttpClient(final OkHttpClient okHttpClient, final String baseUrl, final int maxRetries) {
        if (okHttpClient == null) {
            throw new IllegalArgumentException("okHttpClient must not be null");
        }
        if (maxRetries < 0) {
            throw new IllegalArgumentException("maxRetries must not be negative, got: " + maxRetries);
        }
        this.httpClient = okHttpClient;
        this.baseUrl = normalizeBaseUrl(baseUrl);
        this.maxRetries = maxRetries;
    }

    /**
     * Returns the client shared by every service that isn't configured with its own.
     *
     * @return The shared default HttpClient.
     */
    public static HttpClient getDefault() {
        return DefaultHolder.INSTANCE;
    }

    /**
     * Gets the underlying OkHttp client.
     *
     * @return The OkHttp client.
     */
    public OkHttpClient getOkHttpClient() {
        return httpClient;
    }

    /**
     * Gets the base URL requests are sent to.
     *
     * @return The base URL, without a trailing slash.
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Gets the number of retries used when a request doesn't set its own.
     *
     * @return The default maximum number of retries; zero means retries are disabled.
     */
    public int getMaxRetries() {
        return maxRetries;
    }

    /**
     * Performs an HTTP request with the specified path, HTTP method, and payload.
     *
     * @param path    The path or endpoint of the request.
     * @param apiKey  The API Key used to authenticate the request.
     * @param method  The HTTP method (GET, POST, PUT, DELETE, etc.).
     * @param payload The payload or data to send with the request.
     * @param mediaType The media type of the request.
     * @return An {@link AbstractHttpResponse} representing the response from the server.
     */
    @Override
    public AbstractHttpResponse<String> perform(final String path, final String apiKey, final HttpMethod method, final String payload, MediaType mediaType) {

        return execute(buildRequest(path, apiKey, method, toRequestBody(payload, mediaType), null, null), null);
    }

    /**
     * Performs an HTTP request with the specified path, HTTP method, and payload.
     *
     * @param path         The endpoint path.
     * @param apiKey       Your API key.
     * @param method       The HTTP method.
     * @param payload      The body payload (or null).
     * @param mediaType    The media type for the payload.
     * @param additionalHeaders A map of header-name → header-value to add.
     * @return An {@link AbstractHttpResponse} representing the response from the server.
     */
    @Deprecated
    public AbstractHttpResponse<String> perform(
            final String path,
            final String apiKey,
            final HttpMethod method,
            final String payload,
            final MediaType mediaType,
            final Map<String,String> additionalHeaders) {

        return execute(buildRequest(path, apiKey, method, toRequestBody(payload, mediaType), additionalHeaders, null), null);
    }

    /**
     * Performs an HTTP request with the specified path, HTTP method, and payload.
     *
     * @param path         The endpoint path.
     * @param apiKey       Your API key.
     * @param method       The HTTP method.
     * @param payload      The body payload (or null).
     * @param mediaType    The media type for the payload.
     * @param requestOptions A map of header-name → header-value to add.
     * @return An {@link AbstractHttpResponse} representing the response from the server.
     */
    public AbstractHttpResponse<String> perform(
            final String path,
            final String apiKey,
            final HttpMethod method,
            final String payload,
            final MediaType mediaType,
            final RequestOptions requestOptions) {

        return execute(buildRequest(path, apiKey, method, toRequestBody(payload, mediaType), null, requestOptions), requestOptions);
    }

    /**
     * Performs an HTTP request with a {@code multipart/form-data} body containing a single
     * file part and zero or more text form fields.
     *
     * @param path         The endpoint path.
     * @param apiKey       Your API key.
     * @param method       The HTTP method.
     * @param file         The file to upload (sent as the {@code file} form field).
     * @param fileMediaType The media type of the file.
     * @param formFields   Map of additional form field name &rarr; value pairs to include
     *                     in the multipart body. JSON-encoded payloads should be sent as
     *                     strings here.
     * @return An {@link AbstractHttpResponse} representing the response from the server.
     */
    @Override
    public AbstractHttpResponse<String> performMultipart(
            final String path,
            final String apiKey,
            final HttpMethod method,
            final File file,
            final MediaType fileMediaType,
            final Map<String, String> formFields) {

        return performMultipart(path, apiKey, method, file, fileMediaType, formFields, null);
    }

    @Override
    public AbstractHttpResponse<String> performMultipart(
            final String path,
            final String apiKey,
            final HttpMethod method,
            final File file,
            final MediaType fileMediaType,
            final Map<String, String> formFields,
            final RequestOptions requestOptions) {

        return executeMultipart(path, apiKey, method,
                RequestBody.create(file, fileMediaType),
                file.getName(),
                formFields,
                requestOptions);
    }

    /**
     * Performs an HTTP request with a {@code multipart/form-data} body using the supplied
     * raw bytes as the file part.
     *
     * @param path           The endpoint path.
     * @param apiKey         Your API key.
     * @param method         The HTTP method.
     * @param fileBytes      The file content as bytes.
     * @param fileName       The file name to advertise in the multipart part.
     * @param fileMediaType  The media type of the file.
     * @param formFields     Map of additional form field name &rarr; value pairs.
     * @return An {@link AbstractHttpResponse} representing the response from the server.
     */
    @Override
    public AbstractHttpResponse<String> performMultipart(
            final String path,
            final String apiKey,
            final HttpMethod method,
            final byte[] fileBytes,
            final String fileName,
            final MediaType fileMediaType,
            final Map<String, String> formFields) {

        return performMultipart(path, apiKey, method, fileBytes, fileName, fileMediaType, formFields, null);
    }

    @Override
    public AbstractHttpResponse<String> performMultipart(
            final String path,
            final String apiKey,
            final HttpMethod method,
            final byte[] fileBytes,
            final String fileName,
            final MediaType fileMediaType,
            final Map<String, String> formFields,
            final RequestOptions requestOptions) {

        return executeMultipart(path, apiKey, method,
                RequestBody.create(fileBytes, fileMediaType),
                fileName,
                formFields,
                requestOptions);
    }

    private AbstractHttpResponse<String> executeMultipart(
            final String path,
            final String apiKey,
            final HttpMethod method,
            final RequestBody fileBody,
            final String fileName,
            final Map<String, String> formFields,
            final RequestOptions requestOptions) {

        if (method == HttpMethod.GET) {
            throw new IllegalArgumentException(
                    "Multipart requests require a body and cannot use HTTP method " + method);
        }

        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName, fileBody);

        if (formFields != null) {
            for (Map.Entry<String, String> entry : formFields.entrySet()) {
                if (entry.getValue() != null) {
                    bodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
                }
            }
        }

        return execute(buildRequest(path, apiKey, method, bodyBuilder.build(), null, requestOptions), requestOptions);
    }

    private static RequestBody toRequestBody(final String payload, final MediaType mediaType) {
        return payload == null ? null : RequestBody.create(payload, mediaType);
    }

    private Request buildRequest(
            final String path,
            final String apiKey,
            final HttpMethod method,
            final RequestBody body,
            final Map<String, String> additionalHeaders,
            final RequestOptions requestOptions) {

        Request.Builder requestBuilder = new Request.Builder()
                .url(baseUrl + path)
                .addHeader("Accept", "application/json")
                .addHeader("User-Agent", USER_AGENT)
                .addHeader("Authorization", "Bearer " + apiKey)
                .method(method.name(), body);

        if (additionalHeaders != null) {
            for (Map.Entry<String, String> entry : additionalHeaders.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        if (requestOptions != null) {
            if (requestOptions.getIdempotencyKey() != null && !requestOptions.getIdempotencyKey().isEmpty()) {
                requestBuilder.addHeader("Idempotency-Key", requestOptions.getIdempotencyKey());
            }
            if (requestOptions.getAdditionalHeaders() != null && !requestOptions.getAdditionalHeaders().isEmpty()) {
                for (Map.Entry<String, String> entry : requestOptions.getAdditionalHeaders().entrySet()) {
                    requestBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }

        return requestBuilder.build();
    }

    private AbstractHttpResponse<String> execute(final Request request, final RequestOptions requestOptions) {
        int retries = requestOptions != null && requestOptions.getMaxRetries() != null
                ? requestOptions.getMaxRetries()
                : maxRetries;

        for (int attempt = 0; ; attempt++) {
            long delayMillis;
            Call call = httpClient.newCall(request);
            if (requestOptions != null && requestOptions.getTimeout() != null) {
                call.timeout().timeout(requestOptions.getTimeout().toNanos(), TimeUnit.NANOSECONDS);
            }
            try (Response response = call.execute()) {
                AbstractHttpResponse<String> result =
                        new AbstractHttpResponse<>(response.code(), response.body().string(), response.isSuccessful());
                if (attempt >= retries || !isRetryable(request, response.code())) {
                    return result;
                }
                delayMillis = retryDelayMillis(response, attempt);
            } catch (IOException e) {
                if (attempt >= retries || !isRetryable(request, e)) {
                    throw new RuntimeException(e);
                }
                delayMillis = backoffMillis(attempt);
            }
            pause(delayMillis);
        }
    }

    private static boolean isRetryable(final Request request, final int statusCode) {
        if (statusCode == 429) {
            return true;
        }
        return statusCode >= 500 && mayRepeat(request);
    }

    private static boolean isRetryable(final Request request, final IOException failure) {
        return (failure instanceof SocketException || failure instanceof EOFException) && mayRepeat(request);
    }

    private static boolean mayRepeat(final Request request) {
        return !"POST".equals(request.method()) || request.header("Idempotency-Key") != null;
    }

    private static long retryDelayMillis(final Response response, final int attempt) {
        String retryAfter = response.header("Retry-After");
        if (retryAfter != null) {
            long delayMillis = -1L;
            String seconds = retryAfter.trim();
            if (seconds.matches("[0-9]+")) {
                delayMillis = seconds.length() > MAX_RETRY_AFTER_DIGITS
                        ? MAX_RETRY_AFTER_MILLIS
                        : Long.parseLong(seconds) * 1000L;
            } else {
                Date date = response.headers().getDate("Retry-After");
                if (date != null) {
                    delayMillis = date.getTime() - System.currentTimeMillis();
                }
            }
            if (delayMillis >= 0L) {
                return Math.min(delayMillis, MAX_RETRY_AFTER_MILLIS);
            }
        }
        return backoffMillis(attempt);
    }

    private static long backoffMillis(final int attempt) {
        long exponential = Math.min(MAX_RETRY_DELAY_MILLIS, MIN_RETRY_DELAY_MILLIS * (1L << Math.min(attempt, 10)));
        return (long) (exponential * (0.75 + 0.25 * ThreadLocalRandom.current().nextDouble()));
    }

    private void pause(final long delayMillis) {
        if (delayMillis <= 0L) {
            return;
        }
        try {
            sleep(delayMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting to retry a request", e);
        }
    }

    /**
     * Blocks the calling thread between retry attempts. Overridable so tests don't have to wait.
     *
     * @param millis How long to wait, in milliseconds.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    void sleep(final long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    private static String normalizeBaseUrl(final String baseUrl) {
        HttpUrl parsed = baseUrl == null ? null : HttpUrl.parse(baseUrl);
        if (parsed == null) {
            throw new IllegalArgumentException("baseUrl must be a valid http or https URL, got: " + baseUrl);
        }
        // Endpoint paths (which may carry their own query string) are appended to the base URL as-is, so a
        // query or fragment here would swallow them.
        if (parsed.query() != null || parsed.fragment() != null) {
            throw new IllegalArgumentException("baseUrl must not contain a query or fragment, got: " + baseUrl);
        }
        String normalized = baseUrl;
        while (normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }
}

