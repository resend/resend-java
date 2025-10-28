package com.resend.services.topics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a response after updating a topic.
 */
public class UpdateTopicResponseSuccess {

    /**
     * The unique identifier associated with the topic.
     */
    @JsonProperty("id")
    private String id;

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
    public UpdateTopicResponseSuccess(String id) {
        this.id = id;
    }

    /**
     * Retrieves the ID associated with the updated topic.
     *
     * @return The ID of the updated topic.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID for the updated topic.
     *
     * @param id The ID to be set.
     */
    public void setId(String id) {
        this.id = id;
    }
}
