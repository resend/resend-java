package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the response for a successful broadcast cancellation.
 * Extends the BaseBroadcastResponse class.
 */
public class CancelBroadcastResponseSuccess extends BaseBroadcastResponse {
    @JsonProperty("object")
    private @Nullable String object;

    /**
     * Default constructor
     */
    public CancelBroadcastResponseSuccess() {

    }

    /**
     * Constructs a successful response for cancelling a broadcast.
     *
     * @param id        The ID of the broadcast.
     * @param object    The object of the broadcast.
     */
    public CancelBroadcastResponseSuccess(@Nullable String id, @Nullable String object) {
        super(id);
        this.object = object;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public @Nullable String getObject() {
        return object;
    }
}
