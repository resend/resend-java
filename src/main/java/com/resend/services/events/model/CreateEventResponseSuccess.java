package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response from creating an event.
 */
public class CreateEventResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    public CreateEventResponseSuccess() {
    }

    public CreateEventResponseSuccess(String object, String id) {
        this.object = object;
        this.id = id;
    }

    /**
     * Retrieves the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Retrieves the created event ID.
     *
     * @return The event ID.
     */
    public String getId() {
        return id;
    }
}
