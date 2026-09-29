package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a request to claim a domain already verified by another team.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClaimDomainOptions {

    @JsonProperty("name")
    private final @Nullable String name;

    @JsonProperty("region")
    private final @Nullable String region;

    @JsonProperty("custom_return_path")
    private final @Nullable String customReturnPath;

    @JsonProperty("open_tracking")
    private final @Nullable Boolean openTracking;

    @JsonProperty("click_tracking")
    private final @Nullable Boolean clickTracking;

    @JsonProperty("tracking_subdomain")
    private final @Nullable String trackingSubdomain;

    /**
     * Constructs a ClaimDomainOptions object using the provided builder.
     *
     * @param builder The builder to construct the ClaimDomainOptions from.
     */
    public ClaimDomainOptions(Builder builder) {
        this.name = builder.name;
        this.region = builder.region;
        this.customReturnPath = builder.customReturnPath;
        this.openTracking = builder.openTracking;
        this.clickTracking = builder.clickTracking;
        this.trackingSubdomain = builder.trackingSubdomain;
    }

    /**
     * Get the domain name to claim.
     *
     * @return The domain name.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Get the region where emails will be sent from.
     *
     * @return The region.
     */
    public @Nullable String getRegion() {
        return region;
    }

    /**
     * Get the custom return path subdomain.
     *
     * @return The custom return path.
     */
    public @Nullable String getCustomReturnPath() {
        return customReturnPath;
    }

    /**
     * Get whether open tracking is enabled.
     *
     * @return The open tracking setting.
     */
    public @Nullable Boolean getOpenTracking() {
        return openTracking;
    }

    /**
     * Get whether click tracking is enabled.
     *
     * @return The click tracking setting.
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

    /**
     * Create a new builder instance for constructing ClaimDomainOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ClaimDomainOptions objects.
     */
    public static class Builder {

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private @Nullable String name;
        private @Nullable String region;
        private @Nullable String customReturnPath;
        private @Nullable Boolean openTracking;
        private @Nullable Boolean clickTracking;
        private @Nullable String trackingSubdomain;

        /**
         * Set the domain name to claim.
         *
         * @param name The domain name.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the region where emails will be sent from.
         *
         * @param region The region.
         * @return The builder instance.
         */
        public Builder region(String region) {
            this.region = region;
            return this;
        }

        /**
         * Set the custom return path subdomain.
         *
         * @param customReturnPath The custom return path.
         * @return The builder instance.
         */
        public Builder customReturnPath(String customReturnPath) {
            this.customReturnPath = customReturnPath;
            return this;
        }

        /**
         * Set whether open tracking is enabled.
         *
         * @param openTracking The open tracking setting.
         * @return The builder instance.
         */
        public Builder openTracking(Boolean openTracking) {
            this.openTracking = openTracking;
            return this;
        }

        /**
         * Set whether click tracking is enabled.
         *
         * @param clickTracking The click tracking setting.
         * @return The builder instance.
         */
        public Builder clickTracking(Boolean clickTracking) {
            this.clickTracking = clickTracking;
            return this;
        }

        /**
         * Set the subdomain used for click and open tracking.
         *
         * @param trackingSubdomain The tracking subdomain.
         * @return The builder instance.
         */
        public Builder trackingSubdomain(String trackingSubdomain) {
            this.trackingSubdomain = trackingSubdomain;
            return this;
        }

        /**
         * Build a new ClaimDomainOptions object.
         *
         * @return A new ClaimDomainOptions object.
         */
        public ClaimDomainOptions build() {
            return new ClaimDomainOptions(this);
        }
    }
}
