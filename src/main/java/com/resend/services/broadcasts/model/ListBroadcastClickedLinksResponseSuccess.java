package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a paginated list of a broadcast's clicked links.
 */
public class ListBroadcastClickedLinksResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("data")
    private @Nullable List<BroadcastClickedLink> data;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    /**
     * Default constructor
     */
    public ListBroadcastClickedLinksResponseSuccess() {

    }

    /**
     * Constructs a new ListBroadcastClickedLinksResponseSuccess instance.
     *
     * @param object Type of the object (e.g., "list").
     * @param data List of BroadcastClickedLink objects.
     * @param hasMore Indicate if there are more items to be returned.
     */
    public ListBroadcastClickedLinksResponseSuccess(@Nullable String object, @Nullable List<BroadcastClickedLink> data, @Nullable Boolean hasMore) {
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
     * Gets the list of BroadcastClickedLink objects.
     *
     * @return the list of clicked links
     */
    public @Nullable List<BroadcastClickedLink> getData() {
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
