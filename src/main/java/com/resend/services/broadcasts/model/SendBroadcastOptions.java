package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the options for sending a broadcast email, including scheduling.
 */
public class SendBroadcastOptions {

    @JsonProperty("scheduled_at")
    private String scheduledAt;

    /**
     * Constructs a Broadcast Options object using the provided builder.
     *
     * @param builder The builder to construct the Broadcast Options.
     */
    private SendBroadcastOptions(Builder builder) {
        this.scheduledAt = builder.scheduledAt;
    }

    /**
     * @return The scheduled time for sending the email, in natural language or ISO 8601 format.
     */
    public String getScheduledAt() {
        return scheduledAt;
    }

    /**
     * Builder for creating instances of {@link SendBroadcastOptions}.
     */
    public static class Builder {

        private String scheduledAt;

        /**
         * Sets the scheduled time for sending the email.
         *
         * @param scheduledAt The scheduled time, in natural language (e.g., "in 1 min") or ISO 8601 format (e.g., "2024-08-05T11:52:01.858Z").
         * @return The Builder instance for chaining.
         */
        public Builder scheduledAt(String scheduledAt) {
            this.scheduledAt = scheduledAt;
            return this;
        }

        /**
         * Builds an instance of {@link SendBroadcastOptions} with the specified options.
         *
         * @return A new {@link SendBroadcastOptions} instance.
         */
        public SendBroadcastOptions build() {
            return new SendBroadcastOptions(this);
        }
    }

    /**
     * Creates a new Builder instance for {@link SendBroadcastOptions}.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }
}
