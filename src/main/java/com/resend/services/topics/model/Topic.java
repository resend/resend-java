package com.resend.services.topics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a topic for email segmentation.
 */
public class Topic {

    /**
     * The unique identifier of the topic.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The name of the topic.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The description of the topic.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The default subscription preference for new contacts.
     */
    @JsonProperty("default_subscription")
    private String defaultSubscription;

    /**
     * The creation timestamp of the topic.
     */
    @JsonProperty("created_at")
    private String createdAt;

    /**
     * Default constructor for creating an empty Topic object.
     */
    public Topic() {
    }

    /**
     * Constructs a Topic with the provided attributes.
     *
     * @param id The unique identifier of the topic.
     * @param name The name of the topic.
     * @param description The description of the topic.
     * @param defaultSubscription The default subscription preference.
     * @param createdAt The creation timestamp.
     */
    public Topic(String id, String name, String description, String defaultSubscription, String createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.defaultSubscription = defaultSubscription;
        this.createdAt = createdAt;
    }

    /**
     * Gets the unique identifier of the topic.
     *
     * @return The topic ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the topic.
     *
     * @param id The topic ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the topic.
     *
     * @return The topic name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the topic.
     *
     * @param name The topic name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the topic.
     *
     * @return The topic description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the topic.
     *
     * @param description The topic description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the default subscription preference for new contacts.
     *
     * @return The default subscription preference.
     */
    public String getDefaultSubscription() {
        return defaultSubscription;
    }

    /**
     * Sets the default subscription preference for new contacts.
     *
     * @param defaultSubscription The default subscription preference to set.
     */
    public void setDefaultSubscription(String defaultSubscription) {
        this.defaultSubscription = defaultSubscription;
    }

    /**
     * Gets the creation timestamp of the topic.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the topic.
     *
     * @param createdAt The creation timestamp to set.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
