package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a list of broadcasts, containing metadata and associated data.
 */
public class ListBroadcastsResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("data")
    private @Nullable List<Broadcast> data;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    /**
     * Default constructor
     */
    public ListBroadcastsResponseSuccess() {

    }

    /**
     * Constructs a new BroadcastList instance.
     *
     * @param object Type of the object (e.g., "list").
     * @param data List of Broadcast objects.
     * @param hasMore Indicate if there are more items to be returned.
     */
    public ListBroadcastsResponseSuccess(@Nullable String object, @Nullable List<Broadcast> data, @Nullable Boolean hasMore) {
        this.object = object;
        this.data = data;
        this.hasMore = hasMore;
    }

    /**
     * Gets the type of the object.
     *
     * @return the object type (e.g., "list")
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Gets the list of Broadcast objects.
     *
     * @return the list of broadcasts
     */
    public @Nullable List<Broadcast> getData() {
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

