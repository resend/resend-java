package com.resend.services.apikeys.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a list API keys response.
 */
public class ListApiKeysResponse {

    @JsonProperty("data")
    private List<ApiKey> data;

    @JsonProperty("has_more")
    private Boolean hasMore;

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
    public ListApiKeysResponse(List<ApiKey> data, Boolean hasMore) {
        this.data = data;
        this.hasMore = hasMore;
    }

    /**
     * Gets the list of API key items.
     *
     * @return The list of API key items.
     */
    public List<ApiKey> getData() {
        return data;
    }

    /**
     * Gets the indicator whether there are more items available for pagination.
     *
     * @return Whether there are more items available for pagination.
     */
    public Boolean hasMore() {
        return hasMore;
    }
}

