package com.resend.services.topics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a response after creating a topic.
 */
public class CreateTopicResponseSuccess {

    /**
     * The unique identifier associated with the topic.
     */
    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Constructs a new instance of {@code CreateTopicResponse}.
     */
    public CreateTopicResponseSuccess() {
    }

    /**
     * Constructs a CreateTopicResponse with the provided ID.
     *
     * @param id The ID associated with the created topic.
     */
    public CreateTopicResponseSuccess(@Nullable String id) {
        this.id = id;
    }

    /**
     * Retrieves the ID associated with the created topic.
     *
     * @return The ID of the created topic.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the ID for the created topic.
     *
     * @param id The ID to be set.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }
}
