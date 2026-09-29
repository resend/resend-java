package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents an audience.
 */
public class Audience extends BaseAudience {

    @JsonProperty("created_at")
    private @Nullable String createdAt;

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
    public Audience(final @Nullable String id, final @Nullable String name, final @Nullable String createdAt) {
        super(id, name);
        this.createdAt = createdAt;
    }

    /**
     * Gets the creation timestamp of the audience.
     *
     * @return The creation timestamp of the audience.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }
}
