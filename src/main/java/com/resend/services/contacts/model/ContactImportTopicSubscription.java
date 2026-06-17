package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a topic subscription to apply to all contacts in an import.
 */
public class ContactImportTopicSubscription {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("subscription")
    private final String subscription;

    /**
     * Constructs a ContactImportTopicSubscription with the provided values.
     *
     * @param id           The Topic UUID.
     * @param subscription The subscription status ({@code "opt_in"} or {@code "opt_out"}).
     */
    public ContactImportTopicSubscription(
            @JsonProperty("id") final String id,
            @JsonProperty("subscription") final String subscription) {
        this.id = id;
        this.subscription = subscription;
    }

    /**
     * Gets the Topic UUID.
     *
     * @return The Topic UUID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the subscription status.
     *
     * @return The subscription status ({@code "opt_in"} or {@code "opt_out"}).
     */
    public String getSubscription() {
        return subscription;
    }

    /**
     * Creates a new builder instance for constructing ContactImportTopicSubscription objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ContactImportTopicSubscription objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String id;
        private String subscription;

        /**
         * Sets the Topic UUID.
         *
         * @param id The Topic UUID.
         * @return The builder instance.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the subscription status.
         *
         * @param subscription The subscription status ({@code "opt_in"} or {@code "opt_out"}).
         * @return The builder instance.
         */
        public Builder subscription(String subscription) {
            this.subscription = subscription;
            return this;
        }

        /**
         * Builds a new ContactImportTopicSubscription instance.
         *
         * @return A new ContactImportTopicSubscription instance.
         */
        public ContactImportTopicSubscription build() {
            return new ContactImportTopicSubscription(id, subscription);
        }
    }
}
