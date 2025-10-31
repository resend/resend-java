package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a segment that a contact belongs to.
 */
public class ContactSegment {

    /**
     * The segment ID.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The segment name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The segment creation timestamp.
     */
    @JsonProperty("created_at")
    private String createdAt;

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
    public ContactSegment(String id, String name, String createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    /**
     * Gets the segment ID.
     *
     * @return The segment ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the segment ID.
     *
     * @param id The segment ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the segment name.
     *
     * @return The segment name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the segment name.
     *
     * @param name The segment name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the segment creation timestamp.
     *
     * @return The segment creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the segment creation timestamp.
     *
     * @param createdAt The segment creation timestamp.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
