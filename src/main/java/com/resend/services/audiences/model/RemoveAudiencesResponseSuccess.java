package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response for removing an audience.
 */
public class RemoveAudiencesResponseSuccess {

    @JsonProperty("id")
    private @Nullable String id;
    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("deleted")
    private boolean deleted;

    /**
     * Default constructor
     */
    public RemoveAudiencesResponseSuccess() {
    }

    /**
     * Constructs a successful response for removing an audience.
     *
     * @param id The ID of the removed audience.
     * @param object The Object of the removed audience.
     * @param deleted The boolean indicating if the data was deleted.
     */
    public RemoveAudiencesResponseSuccess(final @Nullable String id, final @Nullable String object, final boolean deleted) {
        this.id = id;
        this.object = object;
        this.deleted = deleted;
    }

    /**
     * Get the ID of the removed audience.
     *
     * @return The ID of the removed audience.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Get the Object of the removed audience.
     *
     * @return The Object of the removed audience.
     */
    public @Nullable String getObject() {
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