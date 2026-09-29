package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response for removing a segment.
 */
public class RemoveSegmentResponseSuccess {

    @JsonProperty("id")
    private @Nullable String id;
    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("deleted")
    private boolean deleted;

    /**
     * Default constructor
     */
    public RemoveSegmentResponseSuccess() {
    }

    /**
     * Constructs a successful response for removing a segment.
     *
     * @param id The ID of the removed segment.
     * @param object The Object of the removed segment.
     * @param deleted The boolean indicating if the data was deleted.
     */
    public RemoveSegmentResponseSuccess(final @Nullable String id, final @Nullable String object, final boolean deleted) {
        this.id = id;
        this.object = object;
        this.deleted = deleted;
    }

    /**
     * Get the ID of the removed segment.
     *
     * @return The ID of the removed segment.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Get the Object of the removed segment.
     *
     * @return The Object of the removed segment.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Get the state of the removed segment.
     *
     * @return The boolean indicating the state of the segment.
     */
    public boolean getDeleted() {
        return deleted;
    }
}
