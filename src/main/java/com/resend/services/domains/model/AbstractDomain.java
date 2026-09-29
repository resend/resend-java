package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * An abstract class representing a domain entity with common attributes.
 */
public abstract class AbstractDomain {

    /**
     * The ID of the domain.
     */
    @JsonProperty("id")
    private @Nullable String id;

    /**
     * The name of the domain.
     */
    @JsonProperty("name")
    private @Nullable String name;

    /**
     * The creation timestamp of the domain.
     */
    @JsonProperty("created_at")
    private @Nullable String createdAt;

    /**
     * The status of the domain.
     */
    @JsonProperty("status")
    private @Nullable String status;

    /**
     * The region of the domain.
     */
    @JsonProperty("region")
    private @Nullable String region;

    /**
     * Whether open tracking is enabled for this domain.
     */
    @JsonProperty("open_tracking")
    private @Nullable Boolean openTracking;

    /**
     * Whether click tracking is enabled for this domain.
     */
    @JsonProperty("click_tracking")
    private @Nullable Boolean clickTracking;

    /**
     * The subdomain used for click and open tracking.
     */
    @JsonProperty("tracking_subdomain")
    private @Nullable String trackingSubdomain;

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
    public AbstractDomain(final @Nullable String id,
                          final @Nullable String name,
                          final @Nullable String createdAt,
                          final @Nullable String status,
                          final @Nullable String region) {
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
    public @Nullable String getId() {
        return id;
    }

    /**
     * Get the name of the domain.
     *
     * @return The name.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Get the creation timestamp of the domain.
     *
     * @return The creation timestamp.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Get the status of the domain.
     *
     * @return The status.
     */
    public @Nullable String getStatus() {
        return status;
    }

    /**
     * Get the region of the domain.
     *
     * @return The region.
     */
    public @Nullable String getRegion() {
        return region;
    }

    /**
     * Get whether open tracking is enabled for this domain.
     *
     * @return Whether open tracking is enabled.
     */
    public @Nullable Boolean getOpenTracking() {
        return openTracking;
    }

    /**
     * Get whether click tracking is enabled for this domain.
     *
     * @return Whether click tracking is enabled.
     */
    public @Nullable Boolean getClickTracking() {
        return clickTracking;
    }

    /**
     * Get the subdomain used for click and open tracking.
     *
     * @return The tracking subdomain.
     */
    public @Nullable String getTrackingSubdomain() {
        return trackingSubdomain;
    }
}
