package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a segment that a contact belongs to.
 */
public class ContactSegment {

    /**
     * The segment ID.
     */
    @JsonProperty("id")
    private @Nullable String id;

    /**
     * The segment name.
     */
    @JsonProperty("name")
    private @Nullable String name;

    /**
     * The segment creation timestamp.
     */
    @JsonProperty("created_at")
    private @Nullable String createdAt;

    /**
     * Default constructor.
     */
    public ContactSegment() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id        The segment ID.
     * @param name      The segment name.
     * @param createdAt The segment creation timestamp.
     */
    public ContactSegment(@Nullable String id, @Nullable String name, @Nullable String createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    /**
     * Gets the segment ID.
     *
     * @return The segment ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the segment ID.
     *
     * @param id The segment ID.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }

    /**
     * Gets the segment name.
     *
     * @return The segment name.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Sets the segment name.
     *
     * @param name The segment name.
     */
    public void setName(@Nullable String name) {
        this.name = name;
    }

    /**
     * Gets the segment creation timestamp.
     *
     * @return The segment creation timestamp.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the segment creation timestamp.
     *
     * @param createdAt The segment creation timestamp.
     */
    public void setCreatedAt(@Nullable String createdAt) {
        this.createdAt = createdAt;
    }
}
