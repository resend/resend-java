package com.resend.core.provider;

import com.resend.core.provider.impl.PropertiesProvider;

/**
 * An abstract class representing an authentication provider.
 * This class defines the basic structure of an authentication provider
 * and provides methods for obtaining authentication-related information.
 */
public abstract class AuthenticationProvider {

    /**
     * The properties provider for handling configuration properties.
     */
    private PropertiesProvider propertiesProvider;

    /**
     * Constructs a new AuthenticationProvider instance.
     * Initializes the properties provider for handling configuration properties.
     */
    public AuthenticationProvider() {
        this.propertiesProvider = new PropertiesProvider();
    }

    /**
     * Gets the authentication token.
     *
     * @return The authentication token.
     */
    public abstract String token();

    /**
     * Gets the base URL for authentication.
     *
     * @return The base URL for authentication.
     */
    public abstract String baseUrl();

    /**
     * Gets the properties provider associated with this authentication provider.
     *
     * @return The properties provider.
     */
    public PropertiesProvider getPropertiesProvider() {
        return propertiesProvider;
    }

    /**
     * Sets the properties provider for this authentication provider.
     *
     * @param propertiesProvider The properties provider to set.
     */
    public void setPropertiesProvider(PropertiesProvider propertiesProvider) {
        this.propertiesProvider = propertiesProvider;
    }
}
