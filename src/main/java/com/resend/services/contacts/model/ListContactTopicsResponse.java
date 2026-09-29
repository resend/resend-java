package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a successful response for listing contact topics.
 */
public class ListContactTopicsResponse {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("data")
    private @Nullable List<ContactTopic> data;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

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
    public ListContactTopicsResponse(final @Nullable String object, final @Nullable List<ContactTopic> data, final @Nullable Boolean hasMore) {
        this.object = object;
        this.data = data;
        this.hasMore = hasMore;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Gets the list of contact topics.
     *
     * @return The list of contact topics.
     */
    public @Nullable List<ContactTopic> getData() {
        return data;
    }

    /**
     * Gets the indicator whether there are more items available for pagination.
     *
     * @return Whether there are more items available for pagination.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }
}
