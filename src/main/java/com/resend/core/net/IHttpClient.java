package com.resend.core.net;

import com.resend.core.exception.ResendException;
import okhttp3.MediaType;

import java.util.Map;

/**
 * An interface representing an HTTP client for performing HTTP requests and receiving responses.
 *
 * @param <T> The type of response data expected.
 */
public interface IHttpClient<T> {

    /**
     * Perform an HTTP request with the specified path, method, and payload.
     *
     * @param path    The path or endpoint of the request.
     * @param apiKey  The API Key used to authenticate the request.
     * @param method  The HTTP method (GET, POST, PUT, DELETE, etc.).
     * @param payload The payload or data to send with the request.
     * @param mediaType The media type of the request.
     * @return An {@link AbstractHttpResponse} representing the response from the server.
     */
    AbstractHttpResponse<T> perform(final String path, final String apiKey, final HttpMethod method, final String payload, final MediaType mediaType);

    /**
     * Perform an HTTP request with the specified path, method, and payload.
     *
     * @param path    The path or endpoint of the request.
     * @param apiKey  The API Key used to authenticate the request.
     * @param method  The HTTP method (GET, POST, PUT, DELETE, etc.).
     * @param payload The payload or data to send with the request.
     * @param mediaType The media type of the request.
     * @param additionalHeaders A map of header-name → header-value to add.
     * @return An {@link AbstractHttpResponse} representing the response from the server.
     */
    AbstractHttpResponse<T> perform(final String path, final String apiKey, final HttpMethod method, final String payload, final MediaType mediaType, final Map<String,String> additionalHeaders);
}
