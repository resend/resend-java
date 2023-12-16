package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/**
 * Represents an audience.
 */
public abstract class BaseAudience {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    // Default constructor
    public BaseAudience() {

    }

    /**
     * Constructs an audience.
     *
     * @param id          The ID of the audience.
     * @param name        The name of the audience.
     */
    public BaseAudience(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the ID of the audience.
     *
     * @return The ID of the audience.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the audience.
     *
     * @return The name of the audience.
     */
    public String getName() {
        return name;
    }
}
