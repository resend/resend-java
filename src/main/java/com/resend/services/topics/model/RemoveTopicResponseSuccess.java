package com.resend.services.topics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a response object for a topic deletion operation.
 */
public class RemoveTopicResponseSuccess {

    /**
     * The object type of the response.
     */
    @JsonProperty("object")
    private String object;

    /**
     * The unique identifier associated with the topic.
     */
    @JsonProperty("id")
    private String id;

    /**
     * A boolean flag indicating whether the topic was successfully deleted.
     */
    @JsonProperty("deleted")
    private boolean deleted;

    /**
     * Default constructor for creating an empty RemoveTopicResponse object.
     */
    public RemoveTopicResponseSuccess() {
    }

    /**
     * Constructor to create a RemoveTopicResponse object with the provided attributes.
     *
     * @param object The object type of the response.
     * @param id The unique identifier associated with the topic.
     * @param deleted A boolean flag indicating whether the topic was successfully deleted.
     */
    public RemoveTopicResponseSuccess(String object, String id, boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
    }

    /**
     * Get the object type of the response.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Set the object type of the response.
     *
     * @param object The object type to set.
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Get the unique identifier associated with the topic.
     *
     * @return The unique identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the unique identifier associated with the topic.
     *
     * @param id The unique identifier to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the boolean flag indicating whether the topic was successfully deleted.
     *
     * @return True if the topic was successfully deleted; otherwise, false.
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Set the boolean flag indicating whether the topic was successfully deleted.
     *
     * @param deleted The deletion status to set.
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
