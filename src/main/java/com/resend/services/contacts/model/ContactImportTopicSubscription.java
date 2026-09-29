package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a topic subscription to apply to all contacts in an import.
 */
public class ContactImportTopicSubscription {

    @JsonProperty("id")
    private final @Nullable String id;

    @JsonProperty("subscription")
    private final @Nullable String subscription;

    /**
     * Constructs a ContactImportTopicSubscription with the provided values.
     *
     * @param id           The Topic UUID.
     * @param subscription The subscription status ({@code "opt_in"} or {@code "opt_out"}).
     */
    public ContactImportTopicSubscription(
            @JsonProperty("id") final @Nullable String id,
            @JsonProperty("subscription") final @Nullable String subscription) {
        this.id = id;
        this.subscription = subscription;
    }

    /**
     * Gets the Topic UUID.
     *
     * @return The Topic UUID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the subscription status.
     *
     * @return The subscription status ({@code "opt_in"} or {@code "opt_out"}).
     */
    public @Nullable String getSubscription() {
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

        private @Nullable String id;
        private @Nullable String subscription;

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
