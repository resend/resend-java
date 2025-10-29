package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a contact topic subscription.
 */
public class ContactTopic {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("subscription")
    private String subscription;

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
    public ContactTopic(final String id, final String name, final String description, final String subscription) {
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
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the topic.
     *
     * @return The name of the topic.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the topic.
     *
     * @return The description of the topic.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the subscription status of the topic.
     *
     * @return The subscription status (opt_in or opt_out).
     */
    public String getSubscription() {
        return subscription;
    }
}
