package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AutomationRunStep {

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

    public AutomationRunStep() {
    }

    public AutomationRunStep(StepType type, String status, String startedAt, String completedAt,
                             Object output, Object error, String createdAt) {
        this.type = type;
        this.status = status;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.output = output;
        this.error = error;
        this.createdAt = createdAt;
    }

    public StepType getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public Object getOutput() {
        return output;
    }

    public Object getError() {
        return error;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
