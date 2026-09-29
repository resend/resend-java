package com.resend.services.topics.model;

import org.jspecify.annotations.Nullable;


/**
 * Represents a topic in a list response.
 * This class is used when topics are returned as part of a collection.
 */
public class Topic extends AbstractTopic {

    /**
     * Default constructor for creating an empty Topic object.
     */
    public Topic() {
        super();
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
    public Topic(@Nullable String id, @Nullable String name, @Nullable String description, @Nullable String defaultSubscription, @Nullable String createdAt) {
        super(id, name, description, defaultSubscription, createdAt);
    }
}
