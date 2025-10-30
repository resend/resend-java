package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a segment.
 */
public abstract class BaseSegment {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

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
    public BaseSegment(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the ID of the segment.
     *
     * @return The ID of the segment.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the segment.
     *
     * @return The name of the segment.
     */
    public String getName() {
        return name;
    }
}
