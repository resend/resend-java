package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

    public String getObject() {
        return object;
    }

    public String getId() {
        return id;
    }

    public RunStatus getStatus() {
        return status;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<AutomationRunStep> getSteps() {
        return steps;
    }
}
