package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update a domain.
 */
public class UpdateDomainOptions {

    @JsonProperty("id")
    private final String id;
    @JsonProperty("click_tracking")
    private final boolean clickTracking;

    @JsonProperty("open_tracking")
    private final boolean openTracking;

    @JsonProperty("tls")
    private final Tls tls;
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
    public boolean getClickTracking() {
        return clickTracking;
    }

    /**
     * Get the openTracking state in the domain.
     *
     * @return The openTracking state in the domain.
     */
    public boolean getOpenTracking() {
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
        private boolean clickTracking;
        private boolean openTracking;
        private Tls tls;

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
        public Builder clickTracking(boolean clickTracking) {
            this.clickTracking = clickTracking;
            return this;
        }

        /**
         * Set the openTracking state in the domain.
         *
         * @param openTracking The openTracking state in the domain.
         * @return The builder instance.
         */
        public Builder openTracking(boolean openTracking) {
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
         * Build a new UpdateDomainOptions object.
         *
         * @return A new UpdateDomainOptions object.
         */
        public UpdateDomainOptions build() {
            return new UpdateDomainOptions(this);
        }
    }
}