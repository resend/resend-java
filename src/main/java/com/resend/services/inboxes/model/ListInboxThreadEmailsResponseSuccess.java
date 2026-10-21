package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing the emails of a thread.
 */
public class ListInboxThreadEmailsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<InboxThreadEmail> data;

    /**
     * Default constructor
     */
    public ListInboxThreadEmailsResponseSuccess() {

    }

    /**
     * Constructs a ListInboxThreadEmailsResponseSuccess.
     *
     * @param object  The object type. Always {@code list}.
     * @param hasMore Whether there are more emails available for pagination.
     * @param data    The messages of the thread on this page, oldest first.
     */
    public ListInboxThreadEmailsResponseSuccess(String object, Boolean hasMore, List<InboxThreadEmail> data) {
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
     * Get whether there are more emails available for pagination.
     *
     * @return Whether there are more emails available for pagination.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Get the messages of the thread on this page, oldest first.
     *
     * @return The messages of the thread on this page, oldest first.
     */
    public List<InboxThreadEmail> getData() {
        return data;
    }
}
