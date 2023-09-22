package com.resend.core.service;

import com.resend.core.mapper.ResendMapper;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.impl.HttpClient;

/**
 * An abstract base class for service implementations, providing common functionality such as HTTP client,
 * authentication provider, and mapper initialization.
 */
public abstract class BaseService {

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
        this.httpClient = new HttpClient();
        this.resendMapper = new ResendMapper();
    }

    /**
     * Gets the HTTP client associated with this service instance.
     *
     * @return The HTTP client.
     */
    public IHttpClient getHttpClient() {
        return httpClient;
    }
}
