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

    @JsonProperty("forge_return_path")
    private final String forgeReturnPath;

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
        this.forgeReturnPath = builder.forgeReturnPath;
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
     * Get the host the domain's Forge return path should move to.
     *
     * @return The Forge return path.
     */
    public String getForgeReturnPath() {
        return forgeReturnPath;
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
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String id;
        private Boolean clickTracking;
        private Boolean openTracking;
        private Tls tls;
        private String trackingSubdomain;
        private String forgeReturnPath;

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
         * Move the domain's Forge return path to another host. Only possible while
         * the Forge record is still verifying. Moving the path does not start
         * verification: fetch the new record with {@code Domains.get}, add it to your
         * DNS, then call {@code Domains.verify} with a scope of
         * {@code VerifyDomainScope.FORGE}.
         *
         * @param forgeReturnPath The host to move the Forge return path to.
         * @return The builder instance.
         */
        public Builder forgeReturnPath(String forgeReturnPath) {
            this.forgeReturnPath = forgeReturnPath;
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