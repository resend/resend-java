package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a contact topic subscription.
 */
public class ContactTopic {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("name")
    private @Nullable String name;

    @JsonProperty("description")
    private @Nullable String description;

    @JsonProperty("subscription")
    private @Nullable String subscription;

    /**
     * Default constructor
     */
    public ContactTopic() {
    }

    /**
     * Creates an instance of ContactTopic with the specified attributes.
     *
     * @param id            The ID of the topic.
     * @param name          The name of the topic.
     * @param description   The description of the topic.
     * @param subscription  The subscription status (opt_in or opt_out).
     */
    public ContactTopic(final @Nullable String id, final @Nullable String name, final @Nullable String description, final @Nullable String subscription) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subscription = subscription;
    }

    /**
     * Gets the ID of the topic.
     *
     * @return The ID of the topic.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the name of the topic.
     *
     * @return The name of the topic.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Gets the description of the topic.
     *
     * @return The description of the topic.
     */
    public @Nullable String getDescription() {
        return description;
    }

    /**
     * Gets the subscription status of the topic.
     *
     * @return The subscription status (opt_in or opt_out).
     */
    public @Nullable String getSubscription() {
        return subscription;
    }
}
