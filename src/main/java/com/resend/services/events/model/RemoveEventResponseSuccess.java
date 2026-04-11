package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response from removing an event.
 */
public class RemoveEventResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private Boolean deleted;

    /**
     * Default constructor for deserialization.
     */
    public RemoveEventResponseSuccess() {
    }

    /**
     * Constructs a RemoveEventResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param id The event ID.
     * @param deleted Whether the event was deleted.
     */
    public RemoveEventResponseSuccess(String object, String id, Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
    }

    /**
     * Retrieves the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Retrieves the removed event ID.
     *
     * @return The event ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Indicates if the event was successfully deleted.
     *
     * @return True if deleted, false otherwise.
     */
    public Boolean getDeleted() {
        return deleted;
    }
}
