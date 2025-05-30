package com.resend.core.net.impl;

import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;

import com.resend.core.net.RequestOptions;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * An implementation of the {@link IHttpClient} interface for performing HTTP requests.
 * This implementation utilizes the OkHttp library for handling HTTP communication.
 */
public class HttpClient implements IHttpClient<Response> {

    /** The base URL for the API. */
    public static final String BASE_API = "https://api.resend.com";

    /** The version of the API */
    private static final String VERSION_NAME = "4.3.0";

    /** The User-Agent header value for HTTP requests. */
    public static final String USER_AGENT = "resend-java/" + VERSION_NAME;

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

        if (requestOptions != null && requestOptions.getIdempotencyKey() != null && !requestOptions.getIdempotencyKey().isEmpty()) {
            requestBuilder.addHeader("Idempotency-Key", requestOptions.getIdempotencyKey());
        }

        Request request = requestBuilder.build();

        try {
            Response response =  httpClient.newCall(request).execute();
            return new AbstractHttpResponse(response.code(), response.body().string(), response.isSuccessful());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

