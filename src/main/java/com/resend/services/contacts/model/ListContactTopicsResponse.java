package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing contact topics.
 */
public class ListContactTopicsResponse {

    @JsonProperty("object")
    private String object;

    @JsonProperty("data")
    private List<ContactTopic> data;

    @JsonProperty("has_more")
    private Boolean hasMore;

    /**
     * Default constructor
     */
    public ListContactTopicsResponse() {
    }

    /**
     * Constructs a successful response for listing contact topics.
     *
     * @param object   The object type.
     * @param data     The list of contact topics.
     * @param hasMore  Whether there are more items available for pagination.
     */
    public ListContactTopicsResponse(final String object, final List<ContactTopic> data, final Boolean hasMore) {
        this.object = object;
        this.data = data;
        this.hasMore = hasMore;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Gets the list of contact topics.
     *
     * @return The list of contact topics.
     */
    public List<ContactTopic> getData() {
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
