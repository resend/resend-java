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

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor. Creates an instance of ListApiKeysResponse with an empty data list.
     */
    public ListApiKeysResponse() {
    }

    /**
     * Creates an instance of ListApiKeyResponse with the specified data.
     *
     * @param data The list of API key items.
     * @param hasMore Indicate if there are more items to be returned.
     * @param object the object type of the module.
     */
    public ListApiKeysResponse(List<ApiKey> data, Boolean hasMore, String object) {
        this.data = data;
        this.hasMore = hasMore;
        this.object = object;
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

    /**
     * Get the type of the object.
     *
     * @return The type of the object.
     */
    public String getObject() {
        return object;
    }
}

