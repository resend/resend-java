package com.resend.services.broadcasts.model;

import org.jspecify.annotations.Nullable;


/**
 * Represents a broadcast response for sending a broadcast.
 * Extends the BaseBroadcastResponse class.
 */
public class SendBroadcastResponseSuccess extends BaseBroadcastResponse {

    /**
     * Default constructor
     */
    public SendBroadcastResponseSuccess() {
    }

    /**
     * Constructs a successful response for sending a broadcast.
     *
     * @param id        The ID of the audience.
     */
    public SendBroadcastResponseSuccess(@Nullable String id) {
        super(id);
    }
}
