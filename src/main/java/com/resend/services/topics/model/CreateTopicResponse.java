package com.resend.services.topics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a response after creating a topic.
 */
public class CreateTopicResponse {

    /**
     * The unique identifier associated with the topic.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Constructs a new instance of {@code CreateTopicResponse}.
     */
    public CreateTopicResponse() {
    }

    /**
     * Constructs a CreateTopicResponse with the provided ID.
     *
     * @param id The ID associated with the created topic.
     */
    public CreateTopicResponse(String id) {
        this.id = id;
    }

    /**
     * Retrieves the ID associated with the created topic.
     *
     * @return The ID of the created topic.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID for the created topic.
     *
     * @param id The ID to be set.
     */
    public void setId(String id) {
        this.id = id;
    }
}
