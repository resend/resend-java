package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a connection between steps in an automation workflow.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutomationConnection {

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonProperty("type")
    private ConnectionType type;

    public AutomationConnection() {
    }

    public AutomationConnection(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.type = builder.type;
    }

    public AutomationConnection(String from, String to, ConnectionType type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    /**
     * Retrieves the source step key.
     *
     * @return The source step key.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Retrieves the target step key.
     *
     * @return The target step key.
     */
    public String getTo() {
        return to;
    }

    /**
     * Retrieves the connection type.
     *
     * @return The connection type.
     */
    public ConnectionType getType() {
        return type;
    }

    /**
     * Creates a new builder instance for AutomationConnection.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String from;
        private String to;
        private ConnectionType type;

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder type(ConnectionType type) {
            this.type = type;
            return this;
        }

        public AutomationConnection build() {
            return new AutomationConnection(this);
        }
    }
}
