package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response for creating a segment.
 * Extends the Segments class.
 */
public class CreateSegmentResponseSuccess extends BaseSegment {

    @JsonProperty("object")
    private @Nullable String object;

    /**
     * Default constructor
     */
    public CreateSegmentResponseSuccess() {

    }

    /**
     * Constructs a successful response for creating a segment.
     *
     * @param id        The ID of the segment.
     * @param name      The name of the segment.
     * @param object    The object of the segment.
     */
    public CreateSegmentResponseSuccess(@Nullable String id, @Nullable String name, @Nullable String object) {
        super(id, name);
        this.object = object;
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
