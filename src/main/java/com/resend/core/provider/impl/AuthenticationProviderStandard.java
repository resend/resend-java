package com.resend.core.provider.impl;

import com.resend.core.provider.AuthenticationProvider;

/**
 * An implementation of the AuthenticationProvider class that uses a standard authentication token.
 * This class provides an authentication token directly during initialization.
 */
public class AuthenticationProviderStandard extends AuthenticationProvider {

    private String token;

    /**
     * Constructs an AuthenticationProviderStandard instance with the specified authentication token.
     *
     * @param token The authentication token to use.
     */
    public AuthenticationProviderStandard(final String token) {
        this.token = token;
    }

    /**
     * Gets the authentication token provided during initialization.
     *
     * @return The authentication token.
     */
    @Override
    public String token() {
        return this.token;
    }

    /**
     * Gets the base URL for authentication from the properties provider.
     *
     * @return The base URL for authentication.
     */
    @Override
    public String baseUrl() {
        return super.getPropertiesProvider().getApiUrl();
    }
}

