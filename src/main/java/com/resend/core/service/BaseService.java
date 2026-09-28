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
@SuppressWarnings("rawtypes")
public abstract class BaseService {

    /**
     * Lazily-initialized defaults shared by every service instance, so that repeated calls such as
     * {@code resend.emails()} reuse one OkHttp connection pool and one Jackson mapper.
     */
    private static final class Defaults {
        static final IHttpClient HTTP_CLIENT = new HttpClient();
        static final ResendMapper MAPPER = new ResendMapper();
    }

    /**
     * Apikey used for authenticating requests.
     */
    protected final String apiKey;

    /**
     * HTTP client for making HTTP requests.
     */
    protected final IHttpClient httpClient;

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
        this.httpClient = Defaults.HTTP_CLIENT;
        this.resendMapper = Defaults.MAPPER;
    }

    /**
     * Constructs a BaseService instance with a provided HTTP client, intended for testing.
     *
     * @param apiKey     The apiKey to use.
     * @param httpClient The HTTP client to use.
     */
    protected BaseService(final String apiKey, final IHttpClient httpClient) {
        this.apiKey = apiKey;
        this.httpClient = httpClient;
        this.resendMapper = Defaults.MAPPER;
    }

    /**
     * Gets the HTTP client associated with this service instance.
     *
     * @return The HTTP client.
     */
    public IHttpClient getHttpClient() {
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
     * Checks a raw response and deserializes its body.
     *
     * @param response     The raw HTTP response.
     * @param responseType The class to deserialize the response body into.
     * @param <T>          The response type.
     * @return The deserialized response.
     * @throws ResendException If the response is not successful.
     */
    @SuppressWarnings("unchecked")
    protected <T> T handle(final AbstractHttpResponse response, final Class<T> responseType) throws ResendException {
        AbstractHttpResponse<String> stringResponse = (AbstractHttpResponse<String>) response;
        if (!stringResponse.isSuccessful()) {
            throw new ResendException(stringResponse.getCode(), stringResponse.getBody());
        }
        return resendMapper.readValue(stringResponse.getBody(), responseType);
    }
}
