package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for creating an inbox label.
 */
public class CreateInboxLabelResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor
     */
    public CreateInboxLabelResponseSuccess() {

    }

    /**
     * Constructs a CreateInboxLabelResponseSuccess.
     *
     * @param object The object type. Always {@code inbox_label}.
     * @param id     The ID of the label.
     */
    public CreateInboxLabelResponseSuccess(String object, String id) {
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
     * Get the ID of the label.
     *
     * @return The ID of the label.
     */
    public String getId() {
        return id;
    }
}
