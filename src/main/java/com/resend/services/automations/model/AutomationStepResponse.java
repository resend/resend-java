package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.Map;

/**
 * Represents a step in an automation response.
 */
public class AutomationStepResponse {

    @JsonProperty("key")
    private @Nullable String key;

    @JsonProperty("type")
    private @Nullable StepType type;

    @JsonProperty("config")
    private @Nullable Map<String, @Nullable Object> config;

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
    public AutomationStepResponse(@Nullable String key, @Nullable StepType type, @Nullable Map<String, @Nullable Object> config) {
        this.key = key;
        this.type = type;
        this.config = config;
    }

    /**
     * Retrieves the step key.
     *
     * @return The step key.
     */
    public @Nullable String getKey() {
        return key;
    }

    /**
     * Retrieves the step type.
     *
     * @return The step type.
     */
    public @Nullable StepType getType() {
        return type;
    }

    /**
     * Retrieves the step configuration.
     *
     * @return The configuration as a map of key-value pairs.
     */
    public @Nullable Map<String, @Nullable Object> getConfig() {
        return config;
    }
}
