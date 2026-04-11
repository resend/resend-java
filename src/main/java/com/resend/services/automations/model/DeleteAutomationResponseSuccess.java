package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response from deleting an automation.
 */
public class DeleteAutomationResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private Boolean deleted;

    /**
     * Default constructor for deserialization.
     */
    public DeleteAutomationResponseSuccess() {
    }

    /**
     * Constructs a DeleteAutomationResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param id The automation ID.
     * @param deleted Whether the automation was deleted.
     */
    public DeleteAutomationResponseSuccess(String object, String id, Boolean deleted) {
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
     * Retrieves the deleted automation ID.
     *
     * @return The automation ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Indicates if the automation was successfully deleted.
     *
     * @return True if deleted, false otherwise.
     */
    public Boolean getDeleted() {
        return deleted;
    }
}
