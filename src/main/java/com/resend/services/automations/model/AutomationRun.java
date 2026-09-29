package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a run of an automation with its full details.
 */
public class AutomationRun {

    @JsonProperty("object")
    private @Nullable String object;

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

    @JsonProperty("steps")
    private @Nullable List<AutomationRunStep> steps;

    /**
     * Default constructor for deserialization.
     */
    public AutomationRun() {
    }

    /**
     * Constructs an AutomationRun with specified values.
     *
     * @param object The object type.
     * @param id The run ID.
     * @param status The run status.
     * @param startedAt The start timestamp.
     * @param completedAt The completion timestamp.
     * @param createdAt The creation timestamp.
     * @param steps The list of run steps.
     */
    public AutomationRun(@Nullable String object, @Nullable String id, @Nullable RunStatus status, @Nullable String startedAt,
                         @Nullable String completedAt, @Nullable String createdAt, @Nullable List<AutomationRunStep> steps) {
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
    public @Nullable String getObject() {
        return object;
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

    /**
     * Retrieves the list of run steps.
     *
     * @return The list of run steps.
     */
    public @Nullable List<AutomationRunStep> getSteps() {
        return steps;
    }
}
