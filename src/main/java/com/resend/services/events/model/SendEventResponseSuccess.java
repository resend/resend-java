package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response from sending an event.
 */
public class SendEventResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("event")
    private String event;

    /**
     * Default constructor for deserialization.
     */
    public SendEventResponseSuccess() {
    }

    /**
     * Constructs a SendEventResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param event The event name.
     */
    public SendEventResponseSuccess(String object, String event) {
        this.object = object;
        this.event = event;
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
     * Retrieves the sent event name.
     *
     * @return The event name.
     */
    public String getEvent() {
        return event;
    }
}
