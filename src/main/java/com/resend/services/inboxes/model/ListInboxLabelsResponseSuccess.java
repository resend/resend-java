package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing the labels of an inbox.
 */
public class ListInboxLabelsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("data")
    private List<InboxLabel> data;

    /**
     * Default constructor
     */
    public ListInboxLabelsResponseSuccess() {

    }

    /**
     * Constructs a ListInboxLabelsResponseSuccess.
     *
     * @param object The object type. Always {@code list}.
     * @param data   The labels on the inbox, oldest first.
     */
    public ListInboxLabelsResponseSuccess(String object, List<InboxLabel> data) {
        this.object = object;
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
     * Get the labels on the inbox, oldest first.
     *
     * @return The labels on the inbox, oldest first.
     */
    public List<InboxLabel> getData() {
        return data;
    }
}
