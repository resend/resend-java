package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a variable in a template.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Variable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("key")
    private String key;

    @JsonProperty("type")
    private VariableType type;

    @JsonProperty("fallback_value")
    private Object fallbackValue;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * Default constructor.
     */
    public Variable() {
    }

    /**
     * Constructs a Variable with the specified key and type.
     *
     * @param key  The key of the variable.
     * @param type The type of the variable.
     */
    public Variable(String key, VariableType type) {
        this.key = key;
        this.type = type;
    }

    /**
     * Constructs a Variable with the specified key, type, and fallback value.
     *
     * @param key           The key of the variable.
     * @param type          The type of the variable.
     * @param fallbackValue The fallback value of the variable.
     */
    public Variable(String key, VariableType type, Object fallbackValue) {
        this.key = key;
        this.type = type;
        this.fallbackValue = fallbackValue;
    }

    /**
     * Gets the ID of the variable.
     *
     * @return The ID of the variable.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the variable.
     *
     * @param id The ID of the variable.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the key of the variable.
     *
     * @return The key of the variable.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key of the variable.
     *
     * @param key The key of the variable.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the type of the variable.
     *
     * @return The type of the variable.
     */
    public VariableType getType() {
        return type;
    }

    /**
     * Sets the type of the variable.
     *
     * @param type The type of the variable.
     */
    public void setType(VariableType type) {
        this.type = type;
    }

    /**
     * Gets the fallback value of the variable.
     *
     * @return The fallback value of the variable.
     */
    public Object getFallbackValue() {
        return fallbackValue;
    }

    /**
     * Sets the fallback value of the variable.
     *
     * @param fallbackValue The fallback value of the variable.
     */
    public void setFallbackValue(Object fallbackValue) {
        this.fallbackValue = fallbackValue;
    }

    /**
     * Gets the creation timestamp of the variable.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the variable.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last update timestamp of the variable.
     *
     * @return The last update timestamp.
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update timestamp of the variable.
     *
     * @param updatedAt The last update timestamp.
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Creates a new builder instance to construct Variable.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing Variable instances.
     */
    public static class Builder {
        private String key;
        private VariableType type;
        private Object fallbackValue;

        /**
         * Sets the key of the variable.
         *
         * @param key The key of the variable.
         * @return This builder instance for method chaining.
         */
        public Builder key(String key) {
            this.key = key;
            return this;
        }

        /**
         * Sets the type of the variable.
         *
         * @param type The type of the variable.
         * @return This builder instance for method chaining.
         */
        public Builder type(VariableType type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the fallback value of the variable.
         *
         * @param fallbackValue The fallback value of the variable.
         * @return This builder instance for method chaining.
         */
        public Builder fallbackValue(Object fallbackValue) {
            this.fallbackValue = fallbackValue;
            return this;
        }

        /**
         * Builds and returns a Variable instance.
         *
         * @return A Variable instance.
         */
        public Variable build() {
            Variable variable = new Variable();
            variable.key = this.key;
            variable.type = this.type;
            variable.fallbackValue = this.fallbackValue;
            return variable;
        }
    }
}
