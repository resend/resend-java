package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update emails.
 */
public class UpdateEmailOptions {

    @JsonProperty("scheduled_at")
    private final String scheduledAt;

    private UpdateEmailOptions(Builder builder) {

        this.scheduledAt = builder.scheduledAt;

    }

    /**
     * Retrieves the schedule of the email.
     *
     * @return The schedule of the email.
     */
    public String getScheduledAt() {
        return scheduledAt;
    }

    /**
     * Creates a new builder instance to construct CreateEmailOptions.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateEmailOptions instances.
     */
    public static class Builder {
        private String scheduledAt;

        /**
         * Set the schedule of the email.
         *
         * @param scheduledAt The schedule of the email in ISO 8601 format (e.g., 2024-08-05T11:52:01.858Z).
         * @return This builder instance for method chaining.
         */
        public Builder scheduledAt(String scheduledAt) {
            this.scheduledAt = scheduledAt;
            return this;
        }

        /**
         * Builds and returns a {@code UpdateEmailOptions} based on the configured properties.
         *
         * @return A {@code UpdateEmailOptions} instance.
         */
        public UpdateEmailOptions build() {
            return new UpdateEmailOptions(this);
        }
    }
}
