package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public AutomationRunListItem() {
    }

    public AutomationRunListItem(String id, RunStatus status, String startedAt,
                                 String completedAt, String createdAt) {
        this.id = id;
        this.status = status;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
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
}
