package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response from creating an event.
 */
public class CreateEventResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Default constructor for deserialization.
     */
    public CreateEventResponseSuccess() {
    }

    /**
     * Constructs a CreateEventResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param id The event ID.
     */
    public CreateEventResponseSuccess(@Nullable String object, @Nullable String id) {
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
     * Retrieves the created event ID.
     *
     * @return The event ID.
     */
    public @Nullable String getId() {
        return id;
    }
}
