package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a template configuration for sending emails.
 */
public class Template {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("variables")
    private final Map<String, Object> variables;

    /**
     * Constructs a Template using the provided builder.
     *
     * @param builder The builder to construct the Template.
     */
    private Template(Builder builder) {
        this.id = builder.id;
        this.variables = builder.variables;
    }

    /**
     * Gets the template ID.
     *
     * @return The template ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the template variables.
     *
     * @return The template variables.
     */
    public Map<String, Object> getVariables() {
        return variables;
    }

    /**
     * Creates a new builder instance for constructing Template objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Helper method to create a Variable for use with varargs methods.
     *
     * @param key The variable key.
     * @param value The variable value.
     * @return A new Variable instance.
     */
    public static Variable variable(String key, Object value) {
        return new Variable(key, value);
    }

    /**
     * Represents a template variable with a key and value.
     */
    public static class Variable {
        private final String key;
        private final Object value;

        /**
         * Constructs a Variable with the specified key and value.
         *
         * @param key The variable key.
         * @param value The variable value.
         */
        public Variable(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Gets the variable key.
         *
         * @return The variable key.
         */
        public String getKey() {
            return key;
        }

        /**
         * Gets the variable value.
         *
         * @return The variable value.
         */
        public Object getValue() {
            return value;
        }
    }

    /**
     * Builder class for constructing Template objects.
     */
    public static class Builder {
        private String id;
        private Map<String, Object> variables;

        /**
         * Set the template ID.
         *
         * @param id The template ID (must be a published template).
         * @return The builder instance.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Set the template variables.
         *
         * @param variables The template variables as key/value pairs.
         * @return The builder instance.
         */
        public Builder variables(Map<String, Object> variables) {
            this.variables = variables;
            return this;
        }

        /**
         * Add multiple template variables using varargs.
         *
         * @param variables The variables to add.
         * @return The builder instance.
         */
        public Builder variables(Variable... variables) {
            if (this.variables == null) {
                this.variables = new HashMap<>();
            }
            for (Variable variable : variables) {
                this.variables.put(variable.getKey(), variable.getValue());
            }
            return this;
        }

        /**
         * Add a single template variable.
         *
         * @param key The variable key.
         * @param value The variable value.
         * @return The builder instance.
         */
        public Builder addVariable(String key, Object value) {
            if (this.variables == null) {
                this.variables = new HashMap<>();
            }
            this.variables.put(key, value);
            return this;
        }

        /**
         * Build a new Template object.
         *
         * @return A new Template object.
         */
        public Template build() {
            return new Template(this);
        }
    }
}
