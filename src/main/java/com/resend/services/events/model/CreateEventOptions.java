package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a request to create an event.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateEventOptions {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("schema")
    private final Map<String, String> schema;

    public CreateEventOptions(Builder builder) {
        this.name = builder.name;
        this.schema = builder.schema;
    }

    /**
     * Retrieves the event name.
     *
     * @return The event name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the event schema.
     *
     * @return The event schema as a map of field names to types.
     */
    public Map<String, String> getSchema() {
        return schema;
    }

    /**
     * Creates a new builder instance for CreateEventOptions.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Map<String, String> schema;

        public Builder name(String name) {
            this.name = name;
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

        public CreateEventOptions build() {
            return new CreateEventOptions(this);
        }
    }
}
