package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a request to create a domain.
 */
public class CreateDomainOptions {

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

    @JsonProperty("tls")
    private final @Nullable Tls tls;

    @JsonProperty("capabilities")
    private final @Nullable DomainCapabilities capabilities;

    @JsonProperty("tracking_subdomain")
    private final @Nullable String trackingSubdomain;

    /**
     * Constructs a CreateDomainOptions object using the provided builder.
     *
     * @param builder The builder to construct the CreateDomainOptions from.
     */
    public CreateDomainOptions(Builder builder) {
        this.name = builder.name;
        this.region = builder.region;
        this.customReturnPath = builder.customReturnPath;
        this.openTracking = builder.openTracking;
        this.clickTracking = builder.clickTracking;
        this.tls = builder.tls;
        this.capabilities = builder.capabilities;
        this.trackingSubdomain = builder.trackingSubdomain;
    }

    /**
     * Get the name of the domain.
     *
     * @return The name of the domain.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Get the region of the domain.
     *
     * @return The region of the domain.
     */
    public @Nullable String getRegion() {
        return region;
    }

    /**
     * Get the customReturnPath of the domain.
     *
     * @return The customReturnPath of the domain.
     */
    public @Nullable String getCustomReturnPath() {
        return customReturnPath;
    }

    /**
     * Get the openTracking state in the domain.
     *
     * @return The openTracking state in the domain.
     */
    public @Nullable Boolean getOpenTracking() {
        return openTracking;
    }

    /**
     * Get the clickTracking state in the domain.
     *
     * @return The clickTracking state in the domain.
     */
    public @Nullable Boolean getClickTracking() {
        return clickTracking;
    }

    /**
     * Get the TLS setting for the domain.
     *
     * @return The TLS setting for the domain.
     */
    public @Nullable Tls getTls() {
        return tls;
    }

    /**
     * Get the sending and receiving capabilities of the domain.
     *
     * @return The capabilities of the domain.
     */
    public @Nullable DomainCapabilities getCapabilities() {
        return capabilities;
    }

    /**
     * Get the trackingSubdomain of the domain.
     *
     * @return The trackingSubdomain of the domain.
     */
    public @Nullable String getTrackingSubdomain() {
        return trackingSubdomain;
    }

    /**
     * Create a new builder instance for constructing CreateDomainOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateDomainOptions objects.
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
        private @Nullable Tls tls;
        private @Nullable DomainCapabilities capabilities;
        private @Nullable String trackingSubdomain;

        /**
         * Set the name of the domain.
         *
         * @param name The name of the domain.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the region of the domain.
         *
         * @param region The region of the domain.
         * @return The builder instance.
         */
        public Builder region(String region) {
            this.region = region;
            return this;
        }

        /**
         * Set the customReturnPath of the domain.
         *
         * @param customReturnPath The customReturnPath of the domain.
         * @return The builder instance.
         */
        public Builder customReturnPath(String customReturnPath) {
            this.customReturnPath = customReturnPath;
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
         * Set the sending and receiving capabilities of the domain.
         *
         * @param capabilities The capabilities of the domain.
         * @return The builder instance.
         */
        public Builder capabilities(DomainCapabilities capabilities) {
            this.capabilities = capabilities;
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
         * Build a new CreateDomainOptions object.
         *
         * @return A new CreateDomainOptions object.
         */
        public CreateDomainOptions build() {
            return new CreateDomainOptions(this);
        }
    }
}