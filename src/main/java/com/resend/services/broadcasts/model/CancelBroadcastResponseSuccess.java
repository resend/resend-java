package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response for a successful broadcast cancellation.
 * Extends the BaseBroadcastResponse class.
 */
public class CancelBroadcastResponseSuccess extends BaseBroadcastResponse {
    @JsonProperty("object")
    private String object;

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
    public CancelBroadcastResponseSuccess(String id, String object) {
        super(id);
        this.object = object;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public String getObject() {
        return object;
    }
}
