package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a request to update an event's schema.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateEventOptions {

    @JsonIgnore
    private final String identifier;

    @JsonProperty("schema")
    private final Map<String, String> schema;

    public UpdateEventOptions(Builder builder) {
        this.identifier = builder.identifier;
        this.schema = builder.schema;
    }

    /**
     * Retrieves the event identifier (UUID or name).
     *
     * @return The event identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Retrieves the updated event schema.
     *
     * @return The event schema as a map of field names to types.
     */
    public Map<String, String> getSchema() {
        return schema;
    }

    /**
     * Creates a new builder instance for UpdateEventOptions.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String identifier;
        private Map<String, String> schema;

        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder schema(Map<String, String> schema) {
            this.schema = schema;
            return this;
        }

        public Builder addSchema(String key, String type) {
            if (this.schema == null) {
                this.schema = new HashMap<>();
            }
            this.schema.put(key, type);
            return this;
        }

        public UpdateEventOptions build() {
            return new UpdateEventOptions(this);
        }
    }
}
