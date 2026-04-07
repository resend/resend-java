package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

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

    public StepType getType() {
        return type;
    }

    public Map<String, Object> getConfig() {
        return config;
    }
}
