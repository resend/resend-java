package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update a domain.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateDomainOptions {

    @JsonProperty("id")
    private final String id;
    @JsonProperty("click_tracking")
    private final Boolean clickTracking;

    @JsonProperty("open_tracking")
    private final Boolean openTracking;

    @JsonProperty("tls")
    private final Tls tls;

    @JsonProperty("tracking_subdomain")
    private final String trackingSubdomain;

    /**
     * Constructs a UpdateDomainOptions object using the provided builder.
     *
     * @param builder The builder to construct the UpdateDomainOptions from.
     */
    public UpdateDomainOptions(Builder builder) {
        this.id = builder.id;
        this.clickTracking = builder.clickTracking;
        this.openTracking = builder.openTracking;
        this.tls = builder.tls;
        this.trackingSubdomain = builder.trackingSubdomain;
    }

    /**
     * Get the id of the domain.
     *
     * @return The id of the domain.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the clickTracking state in the domain.
     *
     * @return The clickTracking state in the domain.
     */
    public Boolean getClickTracking() {
        return clickTracking;
    }

    /**
     * Get the openTracking state in the domain.
     *
     * @return The openTracking state in the domain.
     */
    public Boolean getOpenTracking() {
        return openTracking;
    }

    /**
     * Get the TLS setting for the domain.
     *
     * @return The TLS setting for the domain.
     */
    public Tls getTls() {
        return tls;
    }

    /**
     * Get the trackingSubdomain of the domain.
     *
     * @return The subdomain used for click and open tracking.
     */
    public String getTrackingSubdomain() {
        return trackingSubdomain;
    }

    /**
     * Create a new builder instance for constructing UpdateDomainOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateDomainOptions objects.
     */
    public static class Builder {
        private String id;
        private Boolean clickTracking;
        private Boolean openTracking;
        private Tls tls;
        private String trackingSubdomain;

        /**
         * Set the id of the domain.
         *
         * @param id The id of the domain.
         * @return The builder instance.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Set the clickTracking state in the domain.
         *
         * @param clickTracking The clickTracking state in the domain.
         * @return The builder instance.
         */
        public Builder clickTracking(Boolean clickTracking) {
            this.clickTracking = clickTracking;
            return this;
        }

        /**
         * Set the openTracking state in the domain.
         *
         * @param openTracking The openTracking state in the domain.
         * @return The builder instance.
         */
        public Builder openTracking(Boolean openTracking) {
            this.openTracking = openTracking;
            return this;
        }

        /**
         * Set the TLS setting for the domain.
         *
         * @param tls The TLS setting for the domain.
         * @return The builder instance.
         */
        public Builder tls(Tls tls) {
            this.tls = tls;
            return this;
        }

        /**
         * Set the trackingSubdomain of the domain.
         *
         * @param trackingSubdomain The subdomain to use for click and open tracking.
         * @return The builder instance.
         */
        public Builder trackingSubdomain(String trackingSubdomain) {
            this.trackingSubdomain = trackingSubdomain;
            return this;
        }

        /**
         * Build a new UpdateDomainOptions object.
         *
         * @return A new UpdateDomainOptions object.
         */
        public UpdateDomainOptions build() {
            return new UpdateDomainOptions(this);
        }
    }
}