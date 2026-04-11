package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a request to update an automation.
 */
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

    @JsonProperty("connections")
    private final List<AutomationConnection> connections;

    public UpdateAutomationOptions(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.status = builder.status;
        this.steps = builder.stepsSet ? builder.steps : null;
        this.connections = builder.connectionsSet ? builder.connections : null;
    }

    /**
     * Retrieves the automation ID.
     *
     * @return The automation ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the automation name.
     *
     * @return The automation name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the automation status.
     *
     * @return The automation status.
     */
    public AutomationStatus getStatus() {
        return status;
    }

    /**
     * Retrieves the list of automation steps.
     *
     * @return The list of steps.
     */
    public List<AutomationStep> getSteps() {
        return steps;
    }

    /**
     * Retrieves the list of automation connections.
     *
     * @return The list of connections.
     */
    public List<AutomationConnection> getConnections() {
        return connections;
    }

    /**
     * Creates a new builder instance for UpdateAutomationOptions.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private AutomationStatus status;
        private List<AutomationStep> steps;
        private List<AutomationConnection> connections;
        private boolean stepsSet = false;
        private boolean connectionsSet = false;

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
            if (this.steps == null) {
                this.steps = new ArrayList<>();
            }
            this.steps.addAll(Arrays.asList(steps));
            this.stepsSet = true;
            return this;
        }

        public Builder step(AutomationStep step) {
            if (this.steps == null) {
                this.steps = new ArrayList<>();
            }
            this.steps.add(step);
            this.stepsSet = true;
            return this;
        }

        public Builder connections(List<AutomationConnection> connections) {
            this.connections = connections;
            this.connectionsSet = true;
            return this;
        }

        public Builder connections(AutomationConnection... connections) {
            if (this.connections == null) {
                this.connections = new ArrayList<>();
            }
            this.connections.addAll(Arrays.asList(connections));
            this.connectionsSet = true;
            return this;
        }

        public Builder connection(AutomationConnection connection) {
            if (this.connections == null) {
                this.connections = new ArrayList<>();
            }
            this.connections.add(connection);
            this.connectionsSet = true;
            return this;
        }

        public UpdateAutomationOptions build() {
            return new UpdateAutomationOptions(this);
        }
    }
}
