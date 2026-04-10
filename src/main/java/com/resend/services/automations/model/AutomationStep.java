package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a step in an automation workflow.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutomationStep {

    @JsonProperty("key")
    private final String key;

    @JsonProperty("type")
    private final StepType type;

    @JsonProperty("config")
    private final Map<String, Object> config;

    public AutomationStep(Builder builder) {
        this.key = builder.key;
        this.type = builder.type;
        this.config = builder.config;
    }

    /**
     * Retrieves the step key identifier.
     *
     * @return The step key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Retrieves the step type.
     *
     * @return The step type.
     */
    public StepType getType() {
        return type;
    }

    /**
     * Retrieves the step configuration.
     *
     * @return The configuration as a map of key-value pairs.
     */
    public Map<String, Object> getConfig() {
        return config;
    }

    /**
     * Creates a new builder instance for AutomationStep.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String key;
        private StepType type;
        private Map<String, Object> config = new HashMap<>();

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder type(StepType type) {
            this.type = type;
            return this;
        }

        public Builder config(Map<String, Object> config) {
            this.config = config;
            return this;
        }

        public Builder addConfig(String key, Object value) {
            this.config.put(key, value);
            return this;
        }

        public AutomationStep build() {
            return new AutomationStep(this);
        }
    }
}
