package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.resend.core.util.DateTimeDeserializer;
import com.resend.core.util.DateTimeSerializer;

import java.time.OffsetDateTime;

/**
 * An abstract class representing a domain entity with common attributes.
 */
public abstract class AbstractDomain {

    /**
     * The ID of the domain.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The name of the domain.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The creation timestamp of the domain.
     */
    @JsonProperty("created_at")
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private OffsetDateTime createdAt;

    /**
     * The status of the domain.
     */
    @JsonProperty("status")
    private String status;

    /**
     * The region of the domain.
     */
    @JsonProperty("region")
    private String region;

    /**
     * Default constructor for creating an AbstractDomain instance with uninitialized fields.
     */
    public AbstractDomain() {
    }

    /**
     * Constructor to create an immutable AbstractDomain instance with the provided attributes.
     *
     * @param id          The ID of the domain.
     * @param name        The name of the domain.
     * @param createdAt   The creation timestamp of the domain.
     * @param status      The status of the domain.
     * @param region      The region of the domain.
     */
    public AbstractDomain(final String id,
                          final String name,
                          final OffsetDateTime createdAt,
                          final String status,
                          final String region) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.status = status;
        this.region = region;
    }

    /**
     * Get the ID of the domain.
     *
     * @return The ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the name of the domain.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the creation timestamp of the domain.
     *
     * @return The creation timestamp.
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Get the status of the domain.
     *
     * @return The status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the region of the domain.
     *
     * @return The region.
     */
    public String getRegion() {
        return region;
    }
}
