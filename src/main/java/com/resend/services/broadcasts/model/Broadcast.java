package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a broadcast with its metadata and delivery status.
 */
public class Broadcast {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("audience_id")
    private @Nullable String audienceId;

    @JsonProperty("status")
    private @Nullable String status;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("scheduled_at")
    private @Nullable String scheduledAt;

    @JsonProperty("sent_at")
    private @Nullable String sentAt;

    /**
     * Default constructor
     */
    public Broadcast() {

    }

    /**
     * Constructs a new Broadcast instance.
     *
     * @param id Unique identifier for the broadcast.
     * @param audienceId Identifier for the audience associated with this broadcast.
     * @param status Current status of the broadcast (e.g., draft, sent, queued).
     * @param createdAt Timestamp when the broadcast was created.
     * @param scheduledAt Scheduled timestamp for sending the broadcast.
     * @param sentAt Timestamp when the broadcast was sent.
     */
    public Broadcast(@Nullable String id, @Nullable String audienceId,
                     @Nullable String status, @Nullable String createdAt, @Nullable String scheduledAt, @Nullable String sentAt) {
        this.id = id;
        this.audienceId = audienceId;
        this.status = status;
        this.createdAt = createdAt;
        this.scheduledAt = scheduledAt;
        this.sentAt = sentAt;
    }

    /**
     * Gets the unique identifier for the broadcast.
     *
     * @return the broadcast ID
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the identifier for the audience associated with this broadcast.
     *
     * @return the audience ID
     */
    public @Nullable String getAudienceId() {
        return audienceId;
    }

    /**
     * Gets the current status of the broadcast.
     *
     * @return the status (e.g., draft, sent, queued)
     */
    public @Nullable String getStatus() {
        return status;
    }

    /**
     * Gets the timestamp when the broadcast was created.
     *
     * @return the creation timestamp
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the scheduled timestamp for sending the broadcast.
     *
     * @return the scheduled timestamp
     */
    public @Nullable String getScheduledAt() {
        return scheduledAt;
    }

    /**
     * Gets the timestamp when the broadcast was sent.
     *
     * @return the sent timestamp
     */
    public @Nullable String getSentAt() {
        return sentAt;
    }
}
