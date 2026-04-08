package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a run of an automation with its full details.
 */
public class AutomationRun {

    @JsonProperty("object")
    private String object;

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

    @JsonProperty("steps")
    private List<AutomationRunStep> steps;

    public AutomationRun() {
    }

    public AutomationRun(String object, String id, RunStatus status, String startedAt,
                         String completedAt, String createdAt, List<AutomationRunStep> steps) {
        this.object = object;
        this.id = id;
        this.status = status;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
        this.steps = steps;
    }

    /**
     * Retrieves the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
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

    /**
     * Retrieves the list of run steps.
     *
     * @return The list of run steps.
     */
    public List<AutomationRunStep> getSteps() {
        return steps;
    }
}
