package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a step execution within an automation run.
 */
public class AutomationRunStep {

    @JsonProperty("key")
    private String key;

    @JsonProperty("type")
    private StepType type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("started_at")
    private String startedAt;

    @JsonProperty("completed_at")
    private String completedAt;

    @JsonProperty("output")
    private Object output;

    @JsonProperty("error")
    private Object error;

    @JsonProperty("created_at")
    private String createdAt;

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
    public AutomationRunStep(String key, StepType type, String status, String startedAt, String completedAt,
                             Object output, Object error, String createdAt) {
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
    public String getKey() {
        return key;
    }

    /**
     * Retrieves the step type.
     *
     * @return The step type.
     */
    public StepType getType() {
        return type;
    }

    /**
     * Retrieves the step execution status.
     *
     * @return The step status.
     */
    public String getStatus() {
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
     * Retrieves the step output.
     *
     * @return The step output.
     */
    public Object getOutput() {
        return output;
    }

    /**
     * Retrieves the step error if any.
     *
     * @return The step error.
     */
    public Object getError() {
        return error;
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
