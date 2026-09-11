package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response for a successful broadcast duplication.
 * Extends the BaseBroadcastResponse class.
 */
public class DuplicateBroadcastResponseSuccess extends BaseBroadcastResponse {
    @JsonProperty("object")
    private String object;

    /**
     * Default constructor
     */
    public DuplicateBroadcastResponseSuccess() {

    }

    /**
     * Constructs a successful response for duplicating a broadcast.
     *
     * @param id        The ID of the new draft broadcast.
     * @param object    The object of the broadcast.
     */
    public DuplicateBroadcastResponseSuccess(String id, String object) {
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
