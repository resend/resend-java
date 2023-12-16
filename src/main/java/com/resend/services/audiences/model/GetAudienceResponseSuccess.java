package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/**
 * Represents a successful response for retrieving an audience.
 * Extends the Audiences class.
 */
public class GetAudienceResponseSuccess extends Audience {

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor
     */
    public GetAudienceResponseSuccess() {

    }

    /**
     * Constructs a successful response for retrieving an audience.
     *
     * @param id        The ID of the audience.
     * @param name      The name of the audience.
     * @param created_at The creation timestamp of the audience.
     * @param object    Additional information about the audience.
     */
    public GetAudienceResponseSuccess(final String id, final String name, final OffsetDateTime created_at, final String object) {
        super(id, name, created_at);
        this.object = object;
    }

    /**
     * Get the additional information about the audience.
     *
     * @return The additional information about the audience.
     */
    public String getObject() {
        return object;
    }
}
