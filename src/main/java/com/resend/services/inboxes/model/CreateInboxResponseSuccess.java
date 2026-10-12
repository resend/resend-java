package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for creating an inbox.
 */
public class CreateInboxResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor
     */
    public CreateInboxResponseSuccess() {

    }

    /**
     * Constructs a CreateInboxResponseSuccess.
     *
     * @param object The object type. Always {@code inbox}.
     * @param id     The ID of the inbox.
     */
    public CreateInboxResponseSuccess(String object, String id) {
        this.object = object;
        this.id = id;
    }

    /**
     * Get the object type. Always {@code inbox}.
     *
     * @return The object type. Always {@code inbox}.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the ID of the inbox.
     *
     * @return The ID of the inbox.
     */
    public String getId() {
        return id;
    }
}
