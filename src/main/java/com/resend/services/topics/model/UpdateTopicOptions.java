package com.resend.services.topics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update a topic.
 */
public class UpdateTopicOptions {

    /**
     * The topic name.
     */
    @JsonProperty("name")
    private final String name;

    /**
     * The topic description.
     */
    @JsonProperty("description")
    private final String description;

    /**
     * Private constructor used by the Builder.
     *
     * @param builder The builder instance.
     */
    private UpdateTopicOptions(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
    }

    /**
     * Retrieves the name of the topic.
     *
     * @return The topic name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the description of the topic.
     *
     * @return The topic description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Creates a new builder instance to construct UpdateTopicOptions.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateTopicOptions instances.
     */
    public static class Builder {
        private String name;
        private String description;

        /**
         * Set the topic name.
         *
         * @param name The topic name. Max length is 50 characters.
         * @return This builder instance for method chaining.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the topic description.
         *
         * @param description The topic description. Max length is 200 characters.
         * @return This builder instance for method chaining.
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Builds and returns a {@code UpdateTopicOptions} based on the configured properties.
         *
         * @return A {@code UpdateTopicOptions} instance.
         */
        public UpdateTopicOptions build() {
            return new UpdateTopicOptions(this);
        }
    }
}
