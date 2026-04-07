package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateAutomationOptions {

    @JsonIgnore
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("status")
    private final AutomationStatus status;

    @JsonProperty("steps")
    private final List<AutomationStep> steps;

    @JsonProperty("edges")
    private final List<AutomationEdge> edges;

    public UpdateAutomationOptions(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.status = builder.status;
        this.steps = builder.stepsSet ? builder.steps : null;
        this.edges = builder.edgesSet ? builder.edges : null;
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

    public List<AutomationStep> getSteps() {
        return steps;
    }

    public List<AutomationEdge> getEdges() {
        return edges;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private AutomationStatus status;
        private List<AutomationStep> steps = new ArrayList<>();
        private List<AutomationEdge> edges = new ArrayList<>();
        private boolean stepsSet = false;
        private boolean edgesSet = false;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder status(AutomationStatus status) {
            this.status = status;
            return this;
        }

        public Builder steps(List<AutomationStep> steps) {
            this.steps = steps;
            this.stepsSet = true;
            return this;
        }

        public Builder steps(AutomationStep... steps) {
            this.steps = Arrays.asList(steps);
            this.stepsSet = true;
            return this;
        }

        public Builder step(AutomationStep step) {
            this.steps.add(step);
            this.stepsSet = true;
            return this;
        }

        public Builder edges(List<AutomationEdge> edges) {
            this.edges = edges;
            this.edgesSet = true;
            return this;
        }

        public Builder edges(AutomationEdge... edges) {
            this.edges = Arrays.asList(edges);
            this.edgesSet = true;
            return this;
        }

        public Builder edge(AutomationEdge edge) {
            this.edges.add(edge);
            this.edgesSet = true;
            return this;
        }

        public UpdateAutomationOptions build() {
            return new UpdateAutomationOptions(this);
        }
    }
}
