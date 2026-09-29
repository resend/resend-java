package com.resend.services.broadcasts.model;

import org.jspecify.annotations.Nullable;


/**
 * Represents a broadcast response for updating a broadcast.
 * Extends the BaseBroadcastResponse class.
 */
public class UpdateBroadcastResponseSuccess extends BaseBroadcastResponse {

    /**
     * Default constructor
     */
    public UpdateBroadcastResponseSuccess() {
    }

    /**
     * Constructs a successful response for updating a broadcast.
     *
     * @param id        The ID of the audience.
     */
    public UpdateBroadcastResponseSuccess(@Nullable String id) {
        super(id);
    }
}

