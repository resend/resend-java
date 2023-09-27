package com.resend.core.net.impl;

import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;

import okhttp3.*;

import java.io.IOException;

/**
 * An implementation of the {@link IHttpClient} interface for performing HTTP requests.
 * This implementation utilizes the OkHttp library for handling HTTP communication.
 */
public class HttpClient implements IHttpClient<Response> {

    /** The base URL for the API. */
    public static final String BASE_API = "https://api.resend.com";

    /** The version of the Resend Java SDK. */
    public static final String VERSION = "2.0.1";

    /** The User-Agent header value for HTTP requests. */
    public static final String USER_AGENT = "resend-java/" + VERSION;

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
     * @param method  The HTTP method (GET, POST, PUT, DELETE, etc.).
     * @param payload The payload or data to send with the request.
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
}

