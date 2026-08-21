package com.resend.services.apikeys.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update an api key.
 */
public class UpdateApiKeyOptions {

    @JsonProperty("name")
    private final String name;

    /**
     * Constructs an UpdateApiKeyOptions object using the provided builder.
     *
     * @param builder The builder to construct the UpdateApiKeyOptions.
     */
    private UpdateApiKeyOptions(Builder builder) {
        this.name = builder.name;
    }

    /**
     * Get the name of the API Key.
     *
     * @return The name of the API Key.
     */
    public String getName() {
        return name;
    }

    /**
     * Create a new builder instance for constructing UpdateApiKeyOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateApiKeyOptions objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String name;

        /**
         * Set the name of the Api Key.
         *
         * @param name The name of the Api Key.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Build a new UpdateApiKeyOptions object.
         *
         * @return A new UpdateApiKeyOptions object.
         */
        public UpdateApiKeyOptions build() {
            return new UpdateApiKeyOptions(this);
        }
    }
}
