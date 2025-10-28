package com.resend.services.topics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to create a topic.
 */
public class CreateTopicOptions {

    /**
     * The topic name.
     */
    @JsonProperty("name")
    private final String name;

    /**
     * The default subscription preference for new contacts.
     */
    @JsonProperty("default_subscription")
    private final String defaultSubscription;

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
    private CreateTopicOptions(Builder builder) {
        this.name = builder.name;
        this.defaultSubscription = builder.defaultSubscription;
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
     * Retrieves the default subscription preference for new contacts.
     *
     * @return The default subscription preference.
     */
    public String getDefaultSubscription() {
        return defaultSubscription;
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
     * Creates a new builder instance to construct CreateTopicOptions.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateTopicOptions instances.
     */
    public static class Builder {
        private String name;
        private String defaultSubscription;
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
         * Set the default subscription preference for new contacts.
         *
         * @param defaultSubscription The default subscription preference. Possible values: "opt_in" or "opt_out".
         * @return This builder instance for method chaining.
         */
        public Builder defaultSubscription(String defaultSubscription) {
            this.defaultSubscription = defaultSubscription;
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
         * Builds and returns a {@code CreateTopicOptions} based on the configured properties.
         *
         * @return A {@code CreateTopicOptions} instance.
         */
        public CreateTopicOptions build() {
            return new CreateTopicOptions(this);
        }
    }
}
