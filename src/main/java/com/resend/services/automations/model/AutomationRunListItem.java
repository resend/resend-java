package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents an automation run summary in list responses.
 */
public class AutomationRunListItem {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("status")
    private @Nullable RunStatus status;

    @JsonProperty("started_at")
    private @Nullable String startedAt;

    @JsonProperty("completed_at")
    private @Nullable String completedAt;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    /**
     * Default constructor for deserialization.
     */
    public AutomationRunListItem() {
    }

    /**
     * Constructs an AutomationRunListItem with specified values.
     *
     * @param id The run ID.
     * @param status The run status.
     * @param startedAt The start timestamp.
     * @param completedAt The completion timestamp.
     * @param createdAt The creation timestamp.
     */
    public AutomationRunListItem(@Nullable String id, @Nullable RunStatus status, @Nullable String startedAt,
                                 @Nullable String completedAt, @Nullable String createdAt) {
        this.id = id;
        this.status = status;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
    }

    /**
     * Retrieves the run ID.
     *
     * @return The run ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Retrieves the run status.
     *
     * @return The run status.
     */
    public @Nullable RunStatus getStatus() {
        return status;
    }

    /**
     * Retrieves the start timestamp.
     *
     * @return The start timestamp.
     */
    public @Nullable String getStartedAt() {
        return startedAt;
    }

    /**
     * Retrieves the completion timestamp.
     *
     * @return The completion timestamp.
     */
    public @Nullable String getCompletedAt() {
        return completedAt;
    }

    /**
     * Retrieves the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }
}
