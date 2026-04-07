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
        private List<AutomationStep> steps;
        private List<AutomationEdge> edges;

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
            if (this.steps == null) {
                this.steps = new ArrayList<>();
            }
            this.steps.addAll(Arrays.asList(steps));
            return this;
        }

        public Builder step(AutomationStep step) {
            if (this.steps == null) {
                this.steps = new ArrayList<>();
            }
            this.steps.add(step);
            return this;
        }

        public Builder edges(List<AutomationEdge> edges) {
            this.edges = edges;
            return this;
        }

        public Builder edges(AutomationEdge... edges) {
            if (this.edges == null) {
                this.edges = new ArrayList<>();
            }
            this.edges.addAll(Arrays.asList(edges));
            return this;
        }

        public Builder edge(AutomationEdge edge) {
            if (this.edges == null) {
                this.edges = new ArrayList<>();
            }
            this.edges.add(edge);
            return this;
        }

        public CreateAutomationOptions build() {
            return new CreateAutomationOptions(this);
        }
    }
}
