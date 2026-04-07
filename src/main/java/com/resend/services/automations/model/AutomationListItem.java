package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AutomationListItem {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private AutomationStatus status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    public AutomationListItem() {
    }

    public AutomationListItem(String id, String name, AutomationStatus status, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AutomationStatus getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
