package com.resend.services.apikeys.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the API key response.
 */
public class CreateApiKeyResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("token")
    private String token;

    /**
     * Default constructor. Creates an instance of CreateApiKeyResponse with default values for id and token.
     */
    public CreateApiKeyResponse() {
    }

    /**
     * Creates an instance of CreateApiKeyResponse with the specified id and token.
     *
     * @param id    The ID of the API key.
     * @param token The token of the API key.
     */
    public CreateApiKeyResponse(String id, String token) {
        this.id = id;
        this.token = token;
    }

    /**
     * Gets the ID of the API key.
     *
     * @return The ID of the API key.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the token of the API key.
     *
     * @return The token of the API key.
     */
    public String getToken() {
        return token;
    }
}


