package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an automation run summary in list responses.
 */
public class AutomationRunListItem {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private RunStatus status;

    @JsonProperty("started_at")
    private String startedAt;

    @JsonProperty("completed_at")
    private String completedAt;

    @JsonProperty("created_at")
    private String createdAt;

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
    public AutomationRunListItem(String id, RunStatus status, String startedAt,
                                 String completedAt, String createdAt) {
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
    public String getId() {
        return id;
    }

    /**
     * Retrieves the run status.
     *
     * @return The run status.
     */
    public RunStatus getStatus() {
        return status;
    }

    /**
     * Retrieves the start timestamp.
     *
     * @return The start timestamp.
     */
    public String getStartedAt() {
        return startedAt;
    }

    /**
     * Retrieves the completion timestamp.
     *
     * @return The completion timestamp.
     */
    public String getCompletedAt() {
        return completedAt;
    }

    /**
     * Retrieves the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }
}
