package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateAutomationOptions {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("status")
    private final AutomationStatus status;

    @JsonProperty("steps")
    private final List<AutomationStep> steps;

    @JsonProperty("edges")
    private final List<AutomationEdge> edges;

    public CreateAutomationOptions(Builder builder) {
        this.name = builder.name;
        this.status = builder.status;
        this.steps = builder.steps;
        this.edges = builder.edges;
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
        private String name;
        private AutomationStatus status;
        private List<AutomationStep> steps = new ArrayList<>();
        private List<AutomationEdge> edges = new ArrayList<>();

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
            return this;
        }

        public Builder steps(AutomationStep... steps) {
            this.steps = Arrays.asList(steps);
            return this;
        }

        public Builder step(AutomationStep step) {
            this.steps.add(step);
            return this;
        }

        public Builder edges(List<AutomationEdge> edges) {
            this.edges = edges;
            return this;
        }

        public Builder edges(AutomationEdge... edges) {
            this.edges = Arrays.asList(edges);
            return this;
        }

        public Builder edge(AutomationEdge edge) {
            this.edges.add(edge);
            return this;
        }

        public CreateAutomationOptions build() {
            return new CreateAutomationOptions(this);
        }
    }
}
