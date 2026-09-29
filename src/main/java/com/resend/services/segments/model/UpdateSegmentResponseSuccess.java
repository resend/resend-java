package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response for updating a segment.
 */
public class UpdateSegmentResponseSuccess {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("object")
    private @Nullable String object;

    /**
     * Default constructor
     */
    public UpdateSegmentResponseSuccess() {

    }

    /**
     * Constructs a successful response for updating a segment.
     *
     * @param id        The ID of the segment.
     * @param object    The object of the segment.
     */
    public UpdateSegmentResponseSuccess(@Nullable String id, @Nullable String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Get the ID.
     *
     * @return The ID of the segment.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public @Nullable String getObject() {
        return object;
    }
}
