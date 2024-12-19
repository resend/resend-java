package com.resend.services.broadcasts.model;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response for a successful broadcast removal.
 * Extends the BaseBroadcastResponse class.
 */
public class RemoveBroadcastResponseSuccess extends BaseBroadcastResponse {
    @JsonProperty("object")
    private String object;

    @JsonProperty("deleted")
    private boolean deleted;

    /**
     * Default constructor
     */
    public RemoveBroadcastResponseSuccess() {

    }

    /**
     * Constructs a successful response for deleting a broadcast.
     *
     * @param id        The ID of the broadcast.
     * @param object    The object of the broadcast.
     * @param deleted    The state of the broadcast.
     */
    public RemoveBroadcastResponseSuccess(String id, String object, boolean deleted) {
        super(id);
        this.object = object;
        this.deleted = deleted;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the state.
     *
     * @return The state of the data.
     */
    public boolean isDeleted() {
        return deleted;
    }
}
