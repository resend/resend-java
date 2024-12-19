package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a base response for a broadcast.
 */
public abstract class BaseBroadcastResponse {
    @JsonProperty("id")
    private String id;

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
    public BaseBroadcastResponse(String id) {
        this.id = id;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public String getId() {
        return id;
    }
}
