package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a base response for a broadcast.
 */
public abstract class BaseBroadcastResponse {
    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Default constructor
     */
    public BaseBroadcastResponse() {

    }

    /**
     * Constructs a base response for a broadcast.
     *
     * @param id        The ID of the broadcast.
     */
    public BaseBroadcastResponse(@Nullable String id) {
        this.id = id;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public @Nullable String getId() {
        return id;
    }
}
