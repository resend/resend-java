package com.resend.core.provider.impl;

import com.resend.core.provider.AuthenticationProvider;

/**
 * A default implementation of the AuthenticationProvider class.
 * This class provides default implementations for obtaining authentication-related information,
 * using the properties provided by the associated PropertiesProvider.
 */
public class AuthenticationProviderDefault extends AuthenticationProvider {

    /**
     * Gets the authentication token from the properties provider.
     *
     * @return The authentication token.
     */
    @Override
    public String token() {
        return super.getPropertiesProvider().getApiKey();
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

