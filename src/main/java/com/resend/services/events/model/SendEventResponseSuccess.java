package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response from sending an event.
 */
public class SendEventResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("event")
    private @Nullable String event;

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
    public SendEventResponseSuccess(@Nullable String object, @Nullable String event) {
        this.object = object;
        this.event = event;
    }

    /**
     * Retrieves the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Retrieves the sent event name.
     *
     * @return The event name.
     */
    public @Nullable String getEvent() {
        return event;
    }
}
