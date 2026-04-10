package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Represents a step in an automation response.
 */
public class AutomationStepResponse {

    @JsonProperty("type")
    private StepType type;

    @JsonProperty("config")
    private Map<String, Object> config;

    public AutomationStepResponse() {
    }

    public AutomationStepResponse(StepType type, Map<String, Object> config) {
        this.type = type;
        this.config = config;
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
