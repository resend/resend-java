package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for updating a segment.
 * Extends the BaseSegment class.
 */
public class UpdateSegmentResponseSuccess extends BaseSegment {

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor
     */
    public UpdateSegmentResponseSuccess() {

    }

    /**
     * Constructs a successful response for updating a segment.
     *
     * @param id        The ID of the segment.
     * @param name      The name of the segment.
     * @param object    The object of the segment.
     */
    public UpdateSegmentResponseSuccess(String id, String name, String object) {
        super(id, name);
        this.object = object;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public String getObject() {
        return object;
    }
}
