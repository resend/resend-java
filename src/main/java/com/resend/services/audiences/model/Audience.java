package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.resend.core.util.DateTimeDeserializer;
import com.resend.core.util.DateTimeSerializer;

import java.time.OffsetDateTime;

/**
 * Represents an audience.
 */
public class Audience extends BaseAudience {

    @JsonProperty("created_at")
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private OffsetDateTime createdAt;

    /**
     * Default constructor
     */
    public Audience() {

    }

    /**
     * Constructs an audience.
     *
     * @param createdAt The creation timestamp of the audience.
     * @param id          The ID of the audience.
     * @param name        The name of the audience.
     */
    public Audience(final String id, final String name, final OffsetDateTime createdAt) {
        super(id, name);
        this.createdAt = createdAt;
    }

    /**
     * Gets the creation timestamp of the audience.
     *
     * @return The creation timestamp of the audience.
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
