package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a segment.
 */
public abstract class BaseSegment {
    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("name")
    private @Nullable String name;

    /**
     * Default constructor
     */
    public BaseSegment() {

    }

    /**
     * Constructs a segment.
     *
     * @param id          The ID of the segment.
     * @param name        The name of the segment.
     */
    public BaseSegment(final @Nullable String id, final @Nullable String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the ID of the segment.
     *
     * @return The ID of the segment.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the name of the segment.
     *
     * @return The name of the segment.
     */
    public @Nullable String getName() {
        return name;
    }
}
