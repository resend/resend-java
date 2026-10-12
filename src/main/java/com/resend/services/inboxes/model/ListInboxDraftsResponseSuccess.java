package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing the drafts of an inbox.
 */
public class ListInboxDraftsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<InboxDraftListItem> data;

    /**
     * Default constructor
     */
    public ListInboxDraftsResponseSuccess() {

    }

    /**
     * Constructs a ListInboxDraftsResponseSuccess.
     *
     * @param object  The object type. Always {@code list}.
     * @param hasMore Whether there are more drafts available for pagination.
     * @param data    The unsent drafts on this page, most recently updated first.
     */
    public ListInboxDraftsResponseSuccess(String object, Boolean hasMore, List<InboxDraftListItem> data) {
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
     * Get whether there are more drafts available for pagination.
     *
     * @return Whether there are more drafts available for pagination.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Get the unsent drafts on this page, most recently updated first.
     *
     * @return The unsent drafts on this page, most recently updated first.
     */
    public List<InboxDraftListItem> getData() {
        return data;
    }
}
