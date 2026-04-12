package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Represents a step in an automation response.
 */
public class AutomationStepResponse {

    @JsonProperty("key")
    private String key;

    @JsonProperty("type")
    private StepType type;

    @JsonProperty("config")
    private Map<String, Object> config;

    /**
     * Default constructor for deserialization.
     */
    public AutomationStepResponse() {
    }

    /**
     * Constructs an AutomationStepResponse with specified values.
     *
     * @param key The step key.
     * @param type The step type.
     * @param config The step configuration.
     */
    public AutomationStepResponse(String key, StepType type, Map<String, Object> config) {
        this.key = key;
        this.type = type;
        this.config = config;
    }

    /**
     * Retrieves the step key.
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
}
