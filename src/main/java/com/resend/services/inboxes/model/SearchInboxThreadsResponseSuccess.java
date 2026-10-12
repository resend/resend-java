package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for searching the threads of an inbox.
 */
public class SearchInboxThreadsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<InboxThreadSearchResult> data;

    /**
     * Default constructor
     */
    public SearchInboxThreadsResponseSuccess() {

    }

    /**
     * Constructs a SearchInboxThreadsResponseSuccess.
     *
     * @param object  The object type. Always {@code list}.
     * @param hasMore Whether there are more threads available for pagination.
     * @param data    The matching threads, most recently active first.
     */
    public SearchInboxThreadsResponseSuccess(String object, Boolean hasMore, List<InboxThreadSearchResult> data) {
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
     * Get whether there are more threads available for pagination.
     *
     * @return Whether there are more threads available for pagination.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Get the matching threads, most recently active first.
     *
     * @return The matching threads, most recently active first.
     */
    public List<InboxThreadSearchResult> getData() {
        return data;
    }
}
