package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for updating a segment.
 */
public class UpdateSegmentResponseSuccess {

    @JsonProperty("id")
    private String id;

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
     * @param object    The object of the segment.
     */
    public UpdateSegmentResponseSuccess(String id, String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Get the ID.
     *
     * @return The ID of the segment.
     */
    public String getId() {
        return id;
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
