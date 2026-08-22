package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a paginated list of a broadcast's clicked links.
 */
public class ListBroadcastClickedLinksResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("data")
    private List<BroadcastClickedLink> data;

    @JsonProperty("has_more")
    private Boolean hasMore;

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
    public ListBroadcastClickedLinksResponseSuccess(String object, List<BroadcastClickedLink> data, Boolean hasMore) {
        this.object = object;
        this.data = data;
        this.hasMore = hasMore;
    }

    /**
     * Gets the type of the object.
     *
     * @return the object type (e.g., "list")
     */
    public String getObject() {
        return object;
    }

    /**
     * Gets the list of BroadcastClickedLink objects.
     *
     * @return the list of clicked links
     */
    public List<BroadcastClickedLink> getData() {
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
