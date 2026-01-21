package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents options for creating a broadcast.
 * Extends {@link BroadcastOptions} with additional fields for immediate sending and scheduling.
 */
public class CreateBroadcastOptions extends BroadcastOptions {

    @JsonProperty("send")
    private final Boolean send;

    @JsonProperty("scheduled_at")
    private final String scheduledAt;

    /**
     * Constructs a CreateBroadcastOptions object using the provided builder.
     *
     * @param builder The builder to construct the CreateBroadcastOptions.
     */
    public CreateBroadcastOptions(Builder builder) {
        super(builder);
        this.send = builder.send;
        this.scheduledAt = builder.scheduledAt;
    }

    /**
     * Gets the send flag indicating whether the broadcast should be sent immediately.
     *
     * @return true if the broadcast should be sent immediately, false or null for draft.
     */
    public Boolean getSend() {
        return send;
    }

    /**
     * Gets the scheduled time for sending the broadcast.
     *
     * @return The scheduled time in ISO 8601 format, or null if not scheduled.
     */
    public String getScheduledAt() {
        return scheduledAt;
    }

    /**
     * Creates a new builder instance for constructing CreateBroadcastOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateBroadcastOptions objects.
     */
    public static class Builder extends BroadcastOptions.Builder<CreateBroadcastOptions, Builder> {

        private Boolean send;
        private String scheduledAt;

        /**
         * Sets the send flag to immediately send the broadcast upon creation.
         *
         * @param send true to send immediately, false or null to create as draft.
         * @return The builder instance for chaining.
         */
        public Builder send(Boolean send) {
            this.send = send;
            return self();
        }

        /**
         * Sets the scheduled time for sending the broadcast.
         * Only valid when send is set to true.
         *
         * @param scheduledAt The scheduled time in ISO 8601 format (e.g., "2024-12-25T10:00:00.000Z").
         * @return The builder instance for chaining.
         */
        public Builder scheduledAt(String scheduledAt) {
            this.scheduledAt = scheduledAt;
            return self();
        }

        /**
         * Builds a new CreateBroadcastOptions object.
         *
         * @return A new CreateBroadcastOptions object.
         */
        @Override
        public CreateBroadcastOptions build() {
            return new CreateBroadcastOptions(this);
        }

        /**
         * Returns the builder instance.
         *
         * @return The builder instance.
         */
        @Override
        protected Builder self() {
            return this;
        }
    }
}
