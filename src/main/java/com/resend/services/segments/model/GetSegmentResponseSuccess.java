package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response for retrieving a segment.
 * Extends the Segments class.
 */
public class GetSegmentResponseSuccess extends Segment {

    @JsonProperty("object")
    private @Nullable String object;

    /**
     * Default constructor
     */
    public GetSegmentResponseSuccess() {

    }

    /**
     * Constructs a successful response for retrieving a segment.
     *
     * @param id        The ID of the segment.
     * @param name      The name of the segment.
     * @param created_at The creation timestamp of the segment.
     * @param object    Additional information about the segment.
     */
    public GetSegmentResponseSuccess(final @Nullable String id, final @Nullable String name, final @Nullable String created_at, final @Nullable String object) {
        super(id, name, created_at);
        this.object = object;
    }

    /**
     * Get the additional information about the segment.
     *
     * @return The additional information about the segment.
     */
    public @Nullable String getObject() {
        return object;
    }
}
