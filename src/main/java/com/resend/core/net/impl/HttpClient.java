package com.resend.core.net.impl;

import com.resend.core.SdkVersion;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;

import com.resend.core.net.RequestOptions;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * An implementation of the {@link IHttpClient} interface for performing HTTP requests.
 * This implementation utilizes the OkHttp library for handling HTTP communication.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class HttpClient implements IHttpClient<Response> {

    /** The base URL for the API. */
    public static final String BASE_API = "https://api.resend.com";

    /** The User-Agent header value for HTTP requests. */
    public static final String USER_AGENT = "resend-java/" + SdkVersion.getVersion();

    /** The OkHttpClient instance for handling HTTP requests. */
    private final OkHttpClient httpClient;

    /**
     * Constructs an instance of the HttpClient with the provided API key.
     *
     */
    public HttpClient() {
        this.httpClient = new OkHttpClient();
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
    public AbstractHttpResponse perform(final String path, final String apiKey, final HttpMethod method, final String payload, MediaType mediaType) {

        RequestBody requestBody = null;
        if(payload != null) {
            requestBody = RequestBody.create(payload, mediaType);
        }

        Request request = new Request.Builder()
                .url(BASE_API + path)
                .addHeader("Accept", "application/json")
                .addHeader("User-Agent", USER_AGENT)
                .addHeader("Authorization", "Bearer " + apiKey)
                .method(method.name(), requestBody)
                .build();

        try {
            Response response =  httpClient.newCall(request).execute();
            return new AbstractHttpResponse(response.code(), response.body().string(), response.isSuccessful());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public AbstractHttpResponse perform(
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
                .url(BASE_API + path)
                .addHeader("Accept", "application/json")
                .addHeader("User-Agent", USER_AGENT)
                .addHeader("Authorization", "Bearer " + apiKey)
                .method(method.name(), requestBody);


        if (additionalHeaders != null) {
            for (Map.Entry<String,String> h : additionalHeaders.entrySet()) {
                requestBuilder.addHeader(h.getKey(), h.getValue());
            }
        }

        Request request = requestBuilder.build();

        try {
            Response response =  httpClient.newCall(request).execute();
            return new AbstractHttpResponse(response.code(), response.body().string(), response.isSuccessful());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public AbstractHttpResponse perform(
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
                .url(BASE_API + path)
                .addHeader("Accept", "application/json")
                .addHeader("User-Agent", USER_AGENT)
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

        try {
            Response response =  httpClient.newCall(request).execute();
            return new AbstractHttpResponse(response.code(), response.body().string(), response.isSuccessful());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public AbstractHttpResponse performMultipart(
            final String path,
            final String apiKey,
            final HttpMethod method,
            final File file,
            final MediaType fileMediaType,
            final Map<String, String> formFields) {

        return performMultipart(path, apiKey, method, file, fileMediaType, formFields, null);
    }

    @Override
    public AbstractHttpResponse performMultipart(
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
    public AbstractHttpResponse performMultipart(
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
    public AbstractHttpResponse performMultipart(
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

    private AbstractHttpResponse executeMultipart(
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
                .url(BASE_API + path)
                .addHeader("Accept", "application/json")
                .addHeader("User-Agent", USER_AGENT)
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

        try {
            Response response = httpClient.newCall(requestBuilder.build()).execute();
            return new AbstractHttpResponse(response.code(), response.body().string(), response.isSuccessful());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

