package com.resend.services.contactproperties.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update a contact property.
 */
public class UpdateContactPropertyOptions {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("fallback_value")
    private final Object fallbackValue;

    /**
     * Constructs an UpdateContactPropertyOptions object using the provided builder.
     *
     * @param builder The builder to construct the UpdateContactPropertyOptions.
     */
    public UpdateContactPropertyOptions(Builder builder) {
        this.id = builder.id;
        this.fallbackValue = builder.fallbackValue;
    }

    /**
     * Get the ID of the contact property.
     *
     * @return The ID of the contact property.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the fallback value of the contact property.
     *
     * @return The fallback value of the contact property.
     */
    public Object getFallbackValue() {
        return fallbackValue;
    }

    /**
     * Create a new builder instance for constructing UpdateContactPropertyOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateContactPropertyOptions objects.
     */
    public static class Builder {
        private String id;
        private Object fallbackValue;

        /**
         * Set the ID of the contact property.
         *
         * @param id The ID of the contact property.
         * @return The builder instance.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Set the fallback value of the contact property.
         *
         * @param fallbackValue The fallback value of the contact property.
         * @return The builder instance.
         */
        public Builder fallbackValue(Object fallbackValue) {
            this.fallbackValue = fallbackValue;
            return this;
        }

        /**
         * Build a new UpdateContactPropertyOptions object.
         *
         * @return A new UpdateContactPropertyOptions object.
         */
        public UpdateContactPropertyOptions build() {
            return new UpdateContactPropertyOptions(this);
        }
    }
}
