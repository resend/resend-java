package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/**
 * Represents a domain object.
 */
public class Domain {

    @JsonProperty("object")
    private final String object;

    @JsonProperty("id")
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("status")
    private final String status;

    @JsonProperty("created_at")
    private final OffsetDateTime createdAt;

    @JsonProperty("region")
    private final String region;

    /**
     * Constructor to create an immutable Domain instance.
     *
     * @param object The type of the object (e.g., "domain").
     * @param id The ID of the domain.
     * @param name The name of the domain.
     * @param status The status of the domain.
     * @param createdAt The creation timestamp of the domain.
     * @param region The region of the domain.
     */
    public Domain(final String object,
                  final String id,
                  final String name,
                  final String status,
                  final OffsetDateTime createdAt,
                  final String region) {
        this.object = object;
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.region = region;
    }

    /**
     * Get the type of the object.
     *
     * @return The type of the object.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the ID of the domain.
     *
     * @return The ID of the domain.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the name of the domain.
     *
     * @return The name of the domain.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the status of the domain.
     *
     * @return The status of the domain.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the creation timestamp of the domain.
     *
     * @return The creation timestamp of the domain.
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Get the region of the domain.
     *
     * @return The region of the domain.
     */
    public String getRegion() {
        return region;
    }
}

