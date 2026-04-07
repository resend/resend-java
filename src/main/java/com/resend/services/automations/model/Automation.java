package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Automation {

    @JsonProperty("object")
    private String object;

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

    @JsonProperty("steps")
    private List<AutomationStepResponse> steps;

    @JsonProperty("edges")
    private List<AutomationEdge> edges;

    public Automation() {
    }

    public Automation(String object, String id, String name, AutomationStatus status,
                      String createdAt, String updatedAt,
                      List<AutomationStepResponse> steps, List<AutomationEdge> edges) {
        this.object = object;
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.steps = steps;
        this.edges = edges;
    }

    public String getObject() {
        return object;
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

    public List<AutomationStepResponse> getSteps() {
        return steps;
    }

    public List<AutomationEdge> getEdges() {
        return edges;
    }
}
