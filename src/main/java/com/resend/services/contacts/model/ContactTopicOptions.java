package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents options for a topic subscription update.
 */
public class ContactTopicOptions {

    @JsonProperty("id")
    private String id;

    @JsonProperty("subscription")
    private String subscription;

    /**
     * Default constructor
     */
    public ContactTopicOptions() {
    }

    /**
     * Creates an instance of TopicSubscriptionOptions.
     *
     * @param id            The topic ID.
     * @param subscription  The subscription action (opt_in or opt_out).
     */
    public ContactTopicOptions(final String id, final String subscription) {
        this.id = id;
        this.subscription = subscription;
    }

    /**
     * Gets the topic ID.
     *
     * @return The topic ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the topic ID.
     *
     * @param id The topic ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the subscription action.
     *
     * @return The subscription action (opt_in or opt_out).
     */
    public String getSubscription() {
        return subscription;
    }

    /**
     * Sets the subscription action.
     *
     * @param subscription The subscription action (opt_in or opt_out).
     */
    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    /**
     * Creates a builder for TopicSubscriptionOptions.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for TopicSubscriptionOptions.
     */
    public static class Builder {
        private String id;
        private String subscription;

        /**
         * Sets the topic ID.
         *
         * @param id The topic ID.
         * @return This builder instance.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the subscription action.
         *
         * @param subscription The subscription action (opt_in or opt_out).
         * @return This builder instance.
         */
        public Builder subscription(String subscription) {
            this.subscription = subscription;
            return this;
        }

        /**
         * Builds the ContactTopicOptions instance.
         *
         * @return A new TopicSubscriptionOptions instance.
         */
        public ContactTopicOptions build() {
            return new ContactTopicOptions(id, subscription);
        }
    }
}
