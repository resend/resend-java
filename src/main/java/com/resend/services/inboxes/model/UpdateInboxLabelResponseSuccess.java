package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for updating an inbox label.
 */
public class UpdateInboxLabelResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor
     */
    public UpdateInboxLabelResponseSuccess() {

    }

    /**
     * Constructs a UpdateInboxLabelResponseSuccess.
     *
     * @param object The object type. Always {@code inbox_label}.
     * @param id     The ID of the updated label.
     */
    public UpdateInboxLabelResponseSuccess(String object, String id) {
        this.object = object;
        this.id = id;
    }

    /**
     * Get the object type. Always {@code inbox_label}.
     *
     * @return The object type. Always {@code inbox_label}.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the ID of the updated label.
     *
     * @return The ID of the updated label.
     */
    public String getId() {
        return id;
    }
}
