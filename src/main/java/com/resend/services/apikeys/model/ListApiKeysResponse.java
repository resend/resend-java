package com.resend.services.apikeys.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a list API keys response.
 */
public class ListApiKeysResponse {

    @JsonProperty("data")
    private List<ApiKeyItem> data;

    /**
     * Default constructor. Creates an instance of ListApiKeysResponse with an empty data list.
     */
    public ListApiKeysResponse() {
    }

    /**
     * Creates an instance of ListApiKeyResponse with the specified data.
     *
     * @param data The list of API key items.
     */
    public ListApiKeysResponse(List<ApiKeyItem> data) {
        this.data = data;
    }

    /**
     * Gets the list of API key items.
     *
     * @return The list of API key items.
     */
    public List<ApiKeyItem> getData() {
        return data;
    }
}

