package com.resend.services.topics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Abstract base class representing common topic attributes.
 * This class contains fields shared across different topic response types.
 */
public abstract class AbstractTopic {

    /**
     * The unique identifier of the topic.
     */
    @JsonProperty("id")
    private @Nullable String id;

    /**
     * The name of the topic.
     */
    @JsonProperty("name")
    private @Nullable String name;

    /**
     * The description of the topic.
     */
    @JsonProperty("description")
    private @Nullable String description;

    /**
     * The default subscription preference for new contacts.
     */
    @JsonProperty("default_subscription")
    private @Nullable String defaultSubscription;

    /**
     * The creation timestamp of the topic.
     */
    @JsonProperty("created_at")
    private @Nullable String createdAt;

    /**
     * Default constructor for creating an empty AbstractTopic object.
     */
    public AbstractTopic() {
    }

    /**
     * Constructs an AbstractTopic with the provided attributes.
     *
     * @param id The unique identifier of the topic.
     * @param name The name of the topic.
     * @param description The description of the topic.
     * @param defaultSubscription The default subscription preference.
     * @param createdAt The creation timestamp.
     */
    public AbstractTopic(@Nullable String id, @Nullable String name, @Nullable String description, @Nullable String defaultSubscription, @Nullable String createdAt) {
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
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the topic.
     *
     * @param id The topic ID to set.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }

    /**
     * Gets the name of the topic.
     *
     * @return The topic name.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Sets the name of the topic.
     *
     * @param name The topic name to set.
     */
    public void setName(@Nullable String name) {
        this.name = name;
    }

    /**
     * Gets the description of the topic.
     *
     * @return The topic description.
     */
    public @Nullable String getDescription() {
        return description;
    }

    /**
     * Sets the description of the topic.
     *
     * @param description The topic description to set.
     */
    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    /**
     * Gets the default subscription preference for new contacts.
     *
     * @return The default subscription preference.
     */
    public @Nullable String getDefaultSubscription() {
        return defaultSubscription;
    }

    /**
     * Sets the default subscription preference for new contacts.
     *
     * @param defaultSubscription The default subscription preference to set.
     */
    public void setDefaultSubscription(@Nullable String defaultSubscription) {
        this.defaultSubscription = defaultSubscription;
    }

    /**
     * Gets the creation timestamp of the topic.
     *
     * @return The creation timestamp.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the topic.
     *
     * @param createdAt The creation timestamp to set.
     */
    public void setCreatedAt(@Nullable String createdAt) {
        this.createdAt = createdAt;
    }
}
