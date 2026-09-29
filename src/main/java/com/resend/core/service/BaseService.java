package com.resend.core.service;

import com.resend.core.exception.ResendException;
import com.resend.core.mapper.ResendMapper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.RequestOptions;
import com.resend.core.net.impl.HttpClient;
import okhttp3.MediaType;

import java.util.Map;

/**
 * An abstract base class for service implementations, providing common functionality such as HTTP client,
 * authentication provider, and mapper initialization.
 */
public abstract class BaseService {

    /**
     * Lazily-initialized mapper shared by every service instance. The shared default HTTP client lives in
     * {@link HttpClient#getDefault()}.
     */
    private static final class Defaults {
        static final ResendMapper MAPPER = new ResendMapper();
    }

    /**
     * Apikey used for authenticating requests.
     */
    protected final String apiKey;

    /**
     * HTTP client for making HTTP requests. Every {@link IHttpClient} implementation returns the response body
     * as a {@code String}, so the client is typed accordingly here.
     */
    protected final IHttpClient<String> httpClient;

    /**
     * Mapper responsible for mapping data between different representations.
     */
    protected final ResendMapper resendMapper;

    /**
     * Constructs a BaseService instance with the specified authentication provider, default HTTP client, and mapper.
     *
     * @param apiKey The apiKey to use.
     */
    public BaseService(final String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.getDefault();
        this.resendMapper = Defaults.MAPPER;
    }

    /**
     * Constructs a BaseService instance with a provided HTTP client, e.g. one configured through
     * {@code Resend.builder()} or a mock in tests.
     *
     * @param apiKey     The apiKey to use.
     * @param httpClient The HTTP client to use.
     */
    protected BaseService(final String apiKey, final IHttpClient<String> httpClient) {
        this.apiKey = apiKey;
        this.httpClient = httpClient;
        this.resendMapper = Defaults.MAPPER;
    }

    /**
     * Gets the HTTP client associated with this service instance.
     *
     * @return The HTTP client.
     */
    public IHttpClient<String> getHttpClient() {
        return httpClient;
    }

    /**
     * Performs a request and deserializes a successful response body.
     *
     * @param path         The endpoint path.
     * @param method       The HTTP method.
     * @param payload      The body payload (or null).
     * @param mediaType    The media type for the payload.
     * @param responseType The class to deserialize the response body into.
     * @param <T>          The response type.
     * @return The deserialized response.
     * @throws ResendException If the response is not successful.
     */
    protected <T> T execute(final String path, final HttpMethod method, final String payload,
                            final MediaType mediaType, final Class<T> responseType) throws ResendException {
        return handle(httpClient.perform(path, apiKey, method, payload, mediaType), responseType);
    }

    /**
     * Performs a request with additional request options and deserializes a successful response body.
     *
     * @param path           The endpoint path.
     * @param method         The HTTP method.
     * @param payload        The body payload (or null).
     * @param mediaType      The media type for the payload.
     * @param requestOptions The options with additional headers.
     * @param responseType   The class to deserialize the response body into.
     * @param <T>            The response type.
     * @return The deserialized response.
     * @throws ResendException If the response is not successful.
     */
    protected <T> T execute(final String path, final HttpMethod method, final String payload,
                            final MediaType mediaType, final RequestOptions requestOptions,
                            final Class<T> responseType) throws ResendException {
        return handle(httpClient.perform(path, apiKey, method, payload, mediaType, requestOptions), responseType);
    }

    /**
     * Performs a request with additional headers and deserializes a successful response body.
     *
     * @param path              The endpoint path.
     * @param method            The HTTP method.
     * @param payload           The body payload (or null).
     * @param mediaType         The media type for the payload.
     * @param additionalHeaders A map of header-name to header-value to add.
     * @param responseType      The class to deserialize the response body into.
     * @param <T>               The response type.
     * @return The deserialized response.
     * @throws ResendException If the response is not successful.
     * @deprecated Use {@link #execute(String, HttpMethod, String, MediaType, RequestOptions, Class)} instead.
     */
    @Deprecated
    protected <T> T execute(final String path, final HttpMethod method, final String payload,
                            final MediaType mediaType, final Map<String, String> additionalHeaders,
                            final Class<T> responseType) throws ResendException {
        return handle(httpClient.perform(path, apiKey, method, payload, mediaType, additionalHeaders), responseType);
    }

    /**
     * Checks a response and deserializes its body.
     *
     * @param response     The HTTP response.
     * @param responseType The class to deserialize the response body into.
     * @param <T>          The response type.
     * @return The deserialized response.
     * @throws ResendException If the response is not successful.
     */
    protected <T> T handle(final AbstractHttpResponse<String> response, final Class<T> responseType) throws ResendException {
        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }
        return resendMapper.readValue(response.getBody(), responseType);
    }
}
