package com.resend.services.topics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a response after updating a topic.
 */
public class UpdateTopicResponseSuccess {

    /**
     * The unique identifier associated with the topic.
     */
    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Constructs a new instance of {@code UpdateTopicResponse}.
     */
    public UpdateTopicResponseSuccess() {
    }

    /**
     * Constructs an UpdateTopicResponse with the provided ID.
     *
     * @param id The ID associated with the updated topic.
     */
    public UpdateTopicResponseSuccess(@Nullable String id) {
        this.id = id;
    }

    /**
     * Retrieves the ID associated with the updated topic.
     *
     * @return The ID of the updated topic.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the ID for the updated topic.
     *
     * @param id The ID to be set.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }
}
