package com.resend.core.net;

import com.resend.core.exception.ResendException;
import okhttp3.MediaType;

import java.io.File;
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

    /**
     * Perform an HTTP request with the specified path, method, and payload.
     *
     * @param path    The path or endpoint of the request.
     * @param apiKey  The API Key used to authenticate the request.
     * @param method  The HTTP method (GET, POST, PUT, DELETE, etc.).
     * @param payload The payload or data to send with the request.
     * @param mediaType The media type of the request.
     * @param requestOptions The object containing the request options
     * @return An {@link AbstractHttpResponse} representing the response from the server.
     */
    AbstractHttpResponse<T> perform(final String path, final String apiKey, final HttpMethod method, final String payload, final MediaType mediaType, final RequestOptions requestOptions);

    /**
     * Perform an HTTP request with a {@code multipart/form-data} body containing a file and
     * additional form fields. Object/array fields should be serialized to a JSON string and
     * passed via {@code formFields}.
     *
     * @param path           The path or endpoint of the request.
     * @param apiKey         The API Key used to authenticate the request.
     * @param method         The HTTP method (typically POST).
     * @param file           The file to upload (sent as the {@code file} form field).
     * @param fileMediaType  The media type of the file.
     * @param formFields     A map of additional form field name &rarr; value pairs.
     * @return An {@link AbstractHttpResponse} representing the response from the server.
     */
    AbstractHttpResponse<T> performMultipart(final String path, final String apiKey, final HttpMethod method, final File file, final MediaType fileMediaType, final Map<String, String> formFields);

    /**
     * Perform an HTTP request with a {@code multipart/form-data} body, providing the file
     * content as raw bytes with an explicit file name.
     *
     * @param path           The path or endpoint of the request.
     * @param apiKey         The API Key used to authenticate the request.
     * @param method         The HTTP method (typically POST).
     * @param fileBytes      The file content as bytes (sent as the {@code file} form field).
     * @param fileName       The file name to use in the multipart part.
     * @param fileMediaType  The media type of the file.
     * @param formFields     A map of additional form field name &rarr; value pairs.
     * @return An {@link AbstractHttpResponse} representing the response from the server.
     */
    AbstractHttpResponse<T> performMultipart(final String path, final String apiKey, final HttpMethod method, final byte[] fileBytes, final String fileName, final MediaType fileMediaType, final Map<String, String> formFields);
}
