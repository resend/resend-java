package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for removing an audience.
 */
public class RemoveAudienceResponseSuccess {

    @JsonProperty("id")
    private String id;
    @JsonProperty("object")
    private String object;

    @JsonProperty("deleted")
    private boolean deleted;

    /**
     * Default constructor
     */
    public RemoveAudienceResponseSuccess() {
    }

    /**
     * Constructs a successful response for removing an audience.
     *
     * @param id The ID of the removed audience.
     * @param object The Object of the removed audience.
     * @param deleted The boolean indicating if the data was deleted.
     */
    public RemoveAudienceResponseSuccess(final String id, final String object, final boolean deleted) {
        this.id = id;
        this.object = object;
        this.deleted = deleted;
    }

    /**
     * Get the ID of the removed audience.
     *
     * @return The ID of the removed audience.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the Object of the removed audience.
     *
     * @return The Object of the removed audience.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the state of the removed audience.
     *
     * @return The boolean indicating the state of the audience.
     */
    public boolean getDeleted() {
        return deleted;
    }
}