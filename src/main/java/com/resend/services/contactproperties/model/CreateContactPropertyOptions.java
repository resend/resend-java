package com.resend.services.contactproperties.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a request to create a contact property.
 */
public class CreateContactPropertyOptions {

    @JsonProperty("key")
    private final @Nullable String key;

    @JsonProperty("type")
    private final @Nullable String type;

    @JsonProperty("fallback_value")
    private final @Nullable Object fallbackValue;

    /**
     * Constructs a CreateContactPropertyOptions object using the provided builder.
     *
     * @param builder The builder to construct the CreateContactPropertyOptions.
     */
    public CreateContactPropertyOptions(Builder builder) {
        this.key = builder.key;
        this.type = builder.type;
        this.fallbackValue = builder.fallbackValue;
    }

    /**
     * Get the key of the contact property.
     *
     * @return The key of the contact property.
     */
    public @Nullable String getKey() {
        return key;
    }

    /**
     * Get the type of the contact property.
     *
     * @return The type of the contact property.
     */
    public @Nullable String getType() {
        return type;
    }

    /**
     * Get the fallback value of the contact property.
     *
     * @return The fallback value of the contact property.
     */
    public @Nullable Object getFallbackValue() {
        return fallbackValue;
    }

    /**
     * Create a new builder instance for constructing CreateContactPropertyOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateContactPropertyOptions objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private @Nullable String key;
        private @Nullable String type;
        private @Nullable Object fallbackValue;

        /**
         * Set the key of the contact property.
         *
         * @param key The key of the contact property.
         * @return The builder instance.
         */
        public Builder key(String key) {
            this.key = key;
            return this;
        }

        /**
         * Set the type of the contact property.
         *
         * @param type The type of the contact property.
         * @return The builder instance.
         */
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * Set the fallback value of the contact property.
         *
         * @param fallbackValue The fallback value of the contact property.
         * @return The builder instance.
         */
        public Builder fallbackValue(@Nullable Object fallbackValue) {
            this.fallbackValue = fallbackValue;
            return this;
        }

        /**
         * Build a new CreateContactPropertyOptions object.
         *
         * @return A new CreateContactPropertyOptions object.
         */
        public CreateContactPropertyOptions build() {
            return new CreateContactPropertyOptions(this);
        }
    }
}
