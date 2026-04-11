package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response from creating an automation.
 */
public class CreateAutomationResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor for deserialization.
     */
    public CreateAutomationResponseSuccess() {
    }

    /**
     * Constructs a CreateAutomationResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param id The automation ID.
     */
    public CreateAutomationResponseSuccess(String object, String id) {
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
     * Retrieves the created automation ID.
     *
     * @return The automation ID.
     */
    public String getId() {
        return id;
    }
}
