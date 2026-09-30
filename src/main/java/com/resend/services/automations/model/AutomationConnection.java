package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a connection between steps in an automation workflow.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutomationConnection {

    @JsonProperty("from")
    private @Nullable String from;

    @JsonProperty("to")
    private @Nullable String to;

    @JsonProperty("type")
    private @Nullable ConnectionType type;

    /**
     * Default constructor for deserialization.
     */
    public AutomationConnection() {
    }

    /**
     * Constructs an AutomationConnection using the provided builder.
     *
     * @param builder The builder to construct the connection.
     */
    public AutomationConnection(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.type = builder.type;
    }

    /**
     * Constructs an AutomationConnection with specified values.
     *
     * @param from The source step key.
     * @param to The target step key.
     * @param type The connection type.
     */
    public AutomationConnection(@Nullable String from, @Nullable String to, @Nullable ConnectionType type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    /**
     * Retrieves the source step key.
     *
     * @return The source step key.
     */
    public @Nullable String getFrom() {
        return from;
    }

    /**
     * Retrieves the target step key.
     *
     * @return The target step key.
     */
    public @Nullable String getTo() {
        return to;
    }

    /**
     * Retrieves the connection type.
     *
     * @return The connection type.
     */
    public @Nullable ConnectionType getType() {
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

    /**
     * Builder class for constructing AutomationConnection objects.
     */
    public static class Builder {
        /**
         * Constructs a new Builder instance.
         */
        public Builder() {}

        private @Nullable String from;
        private @Nullable String to;
        private @Nullable ConnectionType type;

        /**
         * Sets the source step key.
         *
         * @param from The source step key.
         * @return The builder instance.
         */
        public Builder from(String from) {
            this.from = from;
            return this;
        }

        /**
         * Sets the target step key.
         *
         * @param to The target step key.
         * @return The builder instance.
         */
        public Builder to(String to) {
            this.to = to;
            return this;
        }

        /**
         * Sets the connection type.
         *
         * @param type The connection type.
         * @return The builder instance.
         */
        public Builder type(@Nullable ConnectionType type) {
            this.type = type;
            return this;
        }

        /**
         * Builds a new AutomationConnection instance.
         *
         * @return A new AutomationConnection.
         */
        public AutomationConnection build() {
            return new AutomationConnection(this);
        }
    }
}
