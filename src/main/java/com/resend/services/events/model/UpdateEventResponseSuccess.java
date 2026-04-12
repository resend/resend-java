package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response from updating an event.
 */
public class UpdateEventResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor for deserialization.
     */
    public UpdateEventResponseSuccess() {
    }

    /**
     * Constructs an UpdateEventResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param id The event ID.
     */
    public UpdateEventResponseSuccess(String object, String id) {
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
     * Retrieves the updated event ID.
     *
     * @return The event ID.
     */
    public String getId() {
        return id;
    }
}
