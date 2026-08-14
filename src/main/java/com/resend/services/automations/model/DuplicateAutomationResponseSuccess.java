package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response from duplicating an automation.
 */
public class DuplicateAutomationResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor for deserialization.
     */
    public DuplicateAutomationResponseSuccess() {
    }

    /**
     * Constructs a DuplicateAutomationResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param id The ID of the newly created automation.
     */
    public DuplicateAutomationResponseSuccess(String object, String id) {
        this.object = object;
        this.id = id;
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
     * Retrieves the ID of the newly created automation.
     *
     * @return The automation ID.
     */
    public String getId() {
        return id;
    }
}
