package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing a broadcast's recipients.
 */
public class ListBroadcastRecipientsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<BroadcastRecipient> data;

    /**
     * Default constructor
     */
    public ListBroadcastRecipientsResponseSuccess() {

    }

    /**
     * Constructs a successful response for listing a broadcast's recipients.
     *
     * @param object  The object type of the list.
     * @param hasMore Whether there are more recipients available for pagination.
     * @param data    The list of broadcast recipients.
     */
    public ListBroadcastRecipientsResponseSuccess(String object, Boolean hasMore, List<BroadcastRecipient> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    /**
     * Get the object type.
     *
     * @return The object type of the list.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the indicator whether there are more recipients available for pagination.
     *
     * @return Whether there are more recipients available for pagination.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Get the list of broadcast recipients.
     *
     * @return The list of broadcast recipients.
     */
    public List<BroadcastRecipient> getData() {
        return data;
    }
}
