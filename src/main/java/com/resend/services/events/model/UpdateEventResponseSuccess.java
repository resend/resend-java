package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response from updating an event.
 */
public class UpdateEventResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

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
    public UpdateEventResponseSuccess(@Nullable String object, @Nullable String id) {
        this.object = object;
        this.id = id;
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
     * Retrieves the updated event ID.
     *
     * @return The event ID.
     */
    public @Nullable String getId() {
        return id;
    }
}
