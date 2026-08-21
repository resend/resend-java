package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to create a shareable link for an email.
 */
public class ShareEmailOptions {

    @JsonProperty("expires_in")
    private final String expiresIn;

    private ShareEmailOptions(Builder builder) {

        this.expiresIn = builder.expiresIn;

    }

    /**
     * Retrieves how long the shareable link stays valid for.
     *
     * @return The expiration duration of the shareable link.
     */
    public String getExpiresIn() {
        return expiresIn;
    }

    /**
     * Creates a new builder instance to construct ShareEmailOptions.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ShareEmailOptions instances.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String expiresIn;

        /**
         * Set how long the shareable link stays valid for.
         *
         * @param expiresIn A human-readable duration (e.g., "10m", "2 hours", "1 day", "1h 30m"). Defaults to "48h" and is capped at 48 hours.
         * @return This builder instance for method chaining.
         */
        public Builder expiresIn(String expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        /**
         * Builds and returns a {@code ShareEmailOptions} based on the configured properties.
         *
         * @return A {@code ShareEmailOptions} instance.
         */
        public ShareEmailOptions build() {
            return new ShareEmailOptions(this);
        }
    }
}
