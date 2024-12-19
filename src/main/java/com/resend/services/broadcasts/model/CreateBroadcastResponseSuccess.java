package com.resend.services.broadcasts.model;

/**
 * Represents a broadcast response for creating a broadcast.
 * Extends the BaseBroadcastResponse class.
 */
public class CreateBroadcastResponseSuccess extends BaseBroadcastResponse {

    /**
     * Default constructor
     */
    public CreateBroadcastResponseSuccess() {
    }

    /**
     * Constructs a successful response for creating a broadcast.
     *
     * @param id        The ID of the audience.
     */
    public CreateBroadcastResponseSuccess(String id) {
        super(id);
    }
}

