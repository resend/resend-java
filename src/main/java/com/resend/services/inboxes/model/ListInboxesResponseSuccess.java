package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing inboxes.
 */
public class ListInboxesResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<InboxListItem> data;

    /**
     * Default constructor
     */
    public ListInboxesResponseSuccess() {

    }

    /**
     * Constructs a ListInboxesResponseSuccess.
     *
     * @param object  The object type. Always {@code list}.
     * @param hasMore Whether there are more inboxes available for pagination.
     * @param data    The list of inboxes, newest first.
     */
    public ListInboxesResponseSuccess(String object, Boolean hasMore, List<InboxListItem> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    /**
     * Get the object type. Always {@code list}.
     *
     * @return The object type. Always {@code list}.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get whether there are more inboxes available for pagination.
     *
     * @return Whether there are more inboxes available for pagination.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Get the list of inboxes, newest first.
     *
     * @return The list of inboxes, newest first.
     */
    public List<InboxListItem> getData() {
        return data;
    }
}
