package com.resend.core.service;

import com.resend.core.mapper.ResendMapper;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.impl.HttpClient;
import com.resend.core.provider.AuthenticationProvider;
import com.resend.core.provider.impl.AuthenticationProviderDefault;

/**
 * An abstract base class for service implementations, providing common functionality such as HTTP client,
 * authentication provider, and mapper initialization.
 */
public abstract class BaseService {

    /**
     * Authentication provider used for authenticating requests.
     */
    private AuthenticationProvider authenticationProvider;

    /**
     * HTTP client for making HTTP requests.
     */
    protected final IHttpClient httpClient;

    /**
     * Mapper responsible for mapping data between different representations.
     */
    protected final ResendMapper resendMapper;

    /**
     * Constructs a BaseService instance with default authentication provider, HTTP client, and mapper.
     * The default authentication provider is an instance of AuthenticationProviderDefault.
     */
    public BaseService() {
        this.authenticationProvider = new AuthenticationProviderDefault();
        this.httpClient = new HttpClient();
        this.resendMapper = new ResendMapper();
    }

    /**
     * Constructs a BaseService instance with the specified authentication provider, default HTTP client, and mapper.
     *
     * @param authenticationProvider The authentication provider to use.
     */
    public BaseService(final AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
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

    /**
     * Gets the authentication provider associated with this service instance.
     *
     * @return The authentication provider.
     */
    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }
}
