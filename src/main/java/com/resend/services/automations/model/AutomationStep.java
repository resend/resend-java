package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutomationStep {

    @JsonProperty("ref")
    private final String ref;

    @JsonProperty("type")
    private final StepType type;

    @JsonProperty("config")
    private final Map<String, Object> config;

    public AutomationStep(Builder builder) {
        this.ref = builder.ref;
        this.type = builder.type;
        this.config = builder.config;
    }

    public String getRef() {
        return ref;
    }

    public StepType getType() {
        return type;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String ref;
        private StepType type;
        private Map<String, Object> config = new HashMap<>();

        public Builder ref(String ref) {
            this.ref = ref;
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
