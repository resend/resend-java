package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for creating an audience.
 * Extends the Audiences class.
 */
public class CreateAudienceResponseSuccess extends BaseAudience {

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor
     */
    public CreateAudienceResponseSuccess() {

    }

    /**
     * Constructs a successful response for creating an audience.
     *
     * @param id        The ID of the audience.
     * @param name      The name of the audience.
     * @param object    The object of the audience.
     */
    public CreateAudienceResponseSuccess(String id, String name, String object) {
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
