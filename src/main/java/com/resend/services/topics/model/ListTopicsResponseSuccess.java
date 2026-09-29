package com.resend.services.topics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * Represents a successful response for listing topics.
 */
public class ListTopicsResponseSuccess {

    /**
     * The object type of the response.
     */
    @JsonProperty("object")
    private @Nullable String object;

    /**
     * The list of topics.
     */
    @JsonProperty("data")
    private @Nullable List<Topic> data;

    /**
     * Indicates whether there are more topics available for pagination.
     */
    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    /**
     * Default constructor
     */
    public ListTopicsResponseSuccess() {
    }

    /**
     * Constructs a successful response for listing topics.
     *
     * @param object The object type of the response.
     * @param data The list of topics.
     * @param hasMore Whether there are more topics available for pagination.
     */
    public ListTopicsResponseSuccess(@Nullable String object, @Nullable List<Topic> data, @Nullable Boolean hasMore) {
        this.object = object;
        this.data = data;
        this.hasMore = hasMore;
    }

    /**
     * Gets the object type of the response.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Sets the object type of the response.
     *
     * @param object The object type to set.
     */
    public void setObject(@Nullable String object) {
        this.object = object;
    }

    /**
     * Gets the list of topics.
     *
     * @return The list of topics.
     */
    public @Nullable List<Topic> getData() {
        return data;
    }

    /**
     * Sets the list of topics.
     *
     * @param data The list of topics to set.
     */
    public void setData(@Nullable List<Topic> data) {
        this.data = data;
    }

    /**
     * Gets the indicator whether there are more items available for pagination.
     *
     * @return Whether there are more items available for pagination.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }

    /**
     * Sets the indicator whether there are more items available for pagination.
     *
     * @param hasMore Whether there are more items available for pagination.
     */
    public void setHasMore(@Nullable Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
