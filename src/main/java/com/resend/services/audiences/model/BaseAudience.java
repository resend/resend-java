package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents an audience.
 */
public abstract class BaseAudience {
    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("name")
    private @Nullable String name;

    /**
     * Default constructor
     */
    public BaseAudience() {

    }

    /**
     * Constructs an audience.
     *
     * @param id          The ID of the audience.
     * @param name        The name of the audience.
     */
    public BaseAudience(final @Nullable String id, final @Nullable String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the ID of the audience.
     *
     * @return The ID of the audience.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the name of the audience.
     *
     * @return The name of the audience.
     */
    public @Nullable String getName() {
        return name;
    }
}
