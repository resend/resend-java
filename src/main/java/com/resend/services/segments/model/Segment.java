package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a segment.
 */
public class Segment extends BaseSegment {

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    /**
     * Default constructor
     */
    public Segment() {

    }

    /**
     * Constructs a segment.
     *
     * @param createdAt The creation timestamp of the segment.
     * @param id          The ID of the segment.
     * @param name        The name of the segment.
     */
    public Segment(final @Nullable String id, final @Nullable String name, final @Nullable String createdAt) {
        super(id, name);
        this.createdAt = createdAt;
    }

    /**
     * Gets the creation timestamp of the segment.
     *
     * @return The creation timestamp of the segment.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }
}
