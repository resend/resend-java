package com.resend.core.provider.impl;

/**
 * A class responsible for providing properties related to API authentication and URL configuration.
 */
public class PropertiesProvider {

    private String apiKey;
    private String apiUrl = "";

    /**
     * Constructs a new instance of {@code PropertiesProvider} and initializes the API key from the environment variable {@code RESEND_API_KEY}.
     */
    public PropertiesProvider() {
        this.apiKey = System.getenv("RESEND_API_KEY");
    }

    /**
     * Returns the API key used for authentication.
     *
     * @return The API key.
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the API key used for authentication.
     *
     * @param apiKey The API key to set.
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Returns the API URL.
     *
     * @return The API URL.
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * Sets the API URL.
     *
     * @param apiUrl The API URL to set.
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}

