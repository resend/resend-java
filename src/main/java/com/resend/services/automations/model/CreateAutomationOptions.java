package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a request to create an automation.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateAutomationOptions {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("status")
    private final AutomationStatus status;

    @JsonProperty("steps")
    private final List<AutomationStep> steps;

    @JsonProperty("connections")
    private final List<AutomationConnection> connections;

    /**
     * Constructs CreateAutomationOptions using the provided builder.
     *
     * @param builder The builder to construct the options.
     */
    public CreateAutomationOptions(Builder builder) {
        this.name = builder.name;
        this.status = builder.status;
        this.steps = builder.steps;
        this.connections = builder.connections;
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
     * Creates a new builder instance for CreateAutomationOptions.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateAutomationOptions objects.
     */
    public static class Builder {
        /**
         * Constructs a new Builder instance.
         */
        public Builder() {}

        private String name;
        private AutomationStatus status;
        private List<AutomationStep> steps;
        private List<AutomationConnection> connections;

        /**
         * Sets the automation name.
         *
         * @param name The automation name.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the automation status.
         *
         * @param status The automation status.
         * @return The builder instance.
         */
        public Builder status(AutomationStatus status) {
            this.status = status;
            return this;
        }

        /**
         * Sets the list of automation steps.
         *
         * @param steps The list of steps.
         * @return The builder instance.
         */
        public Builder steps(List<AutomationStep> steps) {
            this.steps = steps;
            return this;
        }

        /**
         * Sets the automation steps using varargs.
         *
         * @param steps The steps to add.
         * @return The builder instance.
         */
        public Builder steps(AutomationStep... steps) {
            if (this.steps == null) {
                this.steps = new ArrayList<>();
            }
            this.steps.addAll(Arrays.asList(steps));
            return this;
        }

        /**
         * Adds a single automation step.
         *
         * @param step The step to add.
         * @return The builder instance.
         */
        public Builder step(AutomationStep step) {
            if (this.steps == null) {
                this.steps = new ArrayList<>();
            }
            this.steps.add(step);
            return this;
        }

        /**
         * Sets the list of automation connections.
         *
         * @param connections The list of connections.
         * @return The builder instance.
         */
        public Builder connections(List<AutomationConnection> connections) {
            this.connections = connections;
            return this;
        }

        /**
         * Sets the automation connections using varargs.
         *
         * @param connections The connections to add.
         * @return The builder instance.
         */
        public Builder connections(AutomationConnection... connections) {
            if (this.connections == null) {
                this.connections = new ArrayList<>();
            }
            this.connections.addAll(Arrays.asList(connections));
            return this;
        }

        /**
         * Adds a single automation connection.
         *
         * @param connection The connection to add.
         * @return The builder instance.
         */
        public Builder connection(AutomationConnection connection) {
            if (this.connections == null) {
                this.connections = new ArrayList<>();
            }
            this.connections.add(connection);
            return this;
        }

        /**
         * Builds a new CreateAutomationOptions instance.
         *
         * @return A new CreateAutomationOptions.
         */
        public CreateAutomationOptions build() {
            return new CreateAutomationOptions(this);
        }
    }
}
