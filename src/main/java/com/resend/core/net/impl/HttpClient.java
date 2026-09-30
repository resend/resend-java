package com.resend.core.net.impl;

import com.resend.ResendOptions;
import com.resend.core.SdkVersion;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;

import com.resend.core.net.RequestOptions;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * The built-in {@link IHttpClient}, backed by OkHttp.
 *
 * <p>To use your own {@code OkHttpClient} (interceptors, TLS settings, a shared connection pool), pass one to
 * {@link #HttpClient(OkHttpClient, String)} and hand the result to {@code Resend.builder().httpClient(...)}. That
 * requires declaring the {@code com.squareup.okhttp3:okhttp-jvm} dependency in your own build.</p>
 */
public class HttpClient implements IHttpClient<String> {

    /** The default base URL for the API. */
    public static final String BASE_API = "https://api.resend.com";

    /** The default User-Agent header value for HTTP requests. */
    public static final String USER_AGENT = "resend-java/" + SdkVersion.getVersion();

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
        if (okHttpClient == null) {
            throw new IllegalArgumentException("okHttpClient must not be null");
        }
        this.httpClient = okHttpClient;
        this.baseUrl = normalizeBaseUrl(baseUrl);
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

        RequestBody requestBody = null;
        if(payload != null) {
            requestBody = RequestBody.create(payload, mediaType);
        }

        Request request = new Request.Builder()
                .url(baseUrl + path)
                .addHeader("Accept", "application/json")
                .addHeader("User-Agent", userAgent)
                .addHeader("Authorization", "Bearer " + apiKey)
                .method(method.name(), requestBody)
                .build();

        return execute(request);
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

        RequestBody requestBody = null;
        if(payload != null) {
            requestBody = RequestBody.create(payload, mediaType);
        }

        Request.Builder requestBuilder = new Request.Builder()
                .url(baseUrl + path)
                .addHeader("Accept", "application/json")
                .addHeader("User-Agent", userAgent)
                .addHeader("Authorization", "Bearer " + apiKey)
                .method(method.name(), requestBody);


        if (additionalHeaders != null) {
            for (Map.Entry<String,String> h : additionalHeaders.entrySet()) {
                requestBuilder.addHeader(h.getKey(), h.getValue());
            }
        }

        Request request = requestBuilder.build();

        return execute(request);
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

        RequestBody requestBody = null;
        if(payload != null) {
            requestBody = RequestBody.create(payload, mediaType);
        }

        Request.Builder requestBuilder = new Request.Builder()
                .url(baseUrl + path)
                .addHeader("Accept", "application/json")
                .addHeader("User-Agent", userAgent)
                .addHeader("Authorization", "Bearer " + apiKey)
                .method(method.name(), requestBody);

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

        Request request = requestBuilder.build();

        return execute(request);
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

        Request.Builder requestBuilder = new Request.Builder()
                .url(baseUrl + path)
                .addHeader("Accept", "application/json")
                .addHeader("User-Agent", userAgent)
                .addHeader("Authorization", "Bearer " + apiKey)
                .method(method.name(), bodyBuilder.build());

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

        return execute(requestBuilder.build());
    }

    private AbstractHttpResponse<String> execute(final Request request) {
        try (Response response = httpClient.newCall(request).execute()) {
            return new AbstractHttpResponse<>(response.code(), response.body().string(), response.isSuccessful());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
