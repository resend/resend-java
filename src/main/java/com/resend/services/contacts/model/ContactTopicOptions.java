package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents options for a topic subscription update.
 */
public class ContactTopicOptions {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("subscription")
    private @Nullable String subscription;

    /**
     * Default constructor
     */
    public ContactTopicOptions() {
    }

    /**
     * Creates an instance of ContactTopicOptions.
     *
     * @param id            The topic ID.
     * @param subscription  The subscription action (opt_in or opt_out).
     */
    public ContactTopicOptions(final @Nullable String id, final @Nullable String subscription) {
        this.id = id;
        this.subscription = subscription;
    }

    /**
     * Gets the topic ID.
     *
     * @return The topic ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the topic ID.
     *
     * @param id The topic ID.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }

    /**
     * Gets the subscription action.
     *
     * @return The subscription action (opt_in or opt_out).
     */
    public @Nullable String getSubscription() {
        return subscription;
    }

    /**
     * Sets the subscription action.
     *
     * @param subscription The subscription action (opt_in or opt_out).
     */
    public void setSubscription(@Nullable String subscription) {
        this.subscription = subscription;
    }

    /**
     * Creates a builder for ContactTopicOptions.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for ContactTopicOptions.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private @Nullable String id;
        private @Nullable String subscription;

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
