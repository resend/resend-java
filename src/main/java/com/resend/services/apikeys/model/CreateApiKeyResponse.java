package com.resend.services.apikeys.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the API key response.
 */
public class CreateApiKeyResponse {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("token")
    private @Nullable String token;

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
    public CreateApiKeyResponse(@Nullable String id, @Nullable String token) {
        this.id = id;
        this.token = token;
    }

    /**
     * Gets the ID of the API key.
     *
     * @return The ID of the API key.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the token of the API key.
     *
     * @return The token of the API key.
     */
    public @Nullable String getToken() {
        return token;
    }
}


