package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a step execution within an automation run.
 */
public class AutomationRunStep {

    @JsonProperty("key")
    private @Nullable String key;

    @JsonProperty("type")
    private @Nullable StepType type;

    @JsonProperty("status")
    private @Nullable String status;

    @JsonProperty("started_at")
    private @Nullable String startedAt;

    @JsonProperty("completed_at")
    private @Nullable String completedAt;

    @JsonProperty("output")
    private @Nullable Object output;

    @JsonProperty("error")
    private @Nullable Object error;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    /**
     * Default constructor for deserialization.
     */
    public AutomationRunStep() {
    }

    /**
     * Constructs an AutomationRunStep with specified values.
     *
     * @param key The step key.
     * @param type The step type.
     * @param status The execution status.
     * @param startedAt The start timestamp.
     * @param completedAt The completion timestamp.
     * @param output The step output.
     * @param error The step error.
     * @param createdAt The creation timestamp.
     */
    public AutomationRunStep(@Nullable String key, @Nullable StepType type, @Nullable String status, @Nullable String startedAt, @Nullable String completedAt,
                             @Nullable Object output, @Nullable Object error, @Nullable String createdAt) {
        this.key = key;
        this.type = type;
        this.status = status;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.output = output;
        this.error = error;
        this.createdAt = createdAt;
    }

    /**
     * Retrieves the step key.
     *
     * @return The step key.
     */
    public @Nullable String getKey() {
        return key;
    }

    /**
     * Retrieves the step type.
     *
     * @return The step type.
     */
    public @Nullable StepType getType() {
        return type;
    }

    /**
     * Retrieves the step execution status.
     *
     * @return The step status.
     */
    public @Nullable String getStatus() {
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
     * Retrieves the step output.
     *
     * @return The step output.
     */
    public @Nullable Object getOutput() {
        return output;
    }

    /**
     * Retrieves the step error if any.
     *
     * @return The step error.
     */
    public @Nullable Object getError() {
        return error;
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
