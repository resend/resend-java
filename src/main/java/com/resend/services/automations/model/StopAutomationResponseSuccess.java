package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response from stopping an automation.
 */
public class StopAutomationResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private AutomationStatus status;

    /**
     * Default constructor for deserialization.
     */
    public StopAutomationResponseSuccess() {
    }

    /**
     * Constructs a StopAutomationResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param id The automation ID.
     * @param status The automation status.
     */
    public StopAutomationResponseSuccess(String object, String id, AutomationStatus status) {
        this.object = object;
        this.id = id;
        this.status = status;
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
     * Retrieves the stopped automation ID.
     *
     * @return The automation ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the automation status after stopping.
     *
     * @return The automation status.
     */
    public AutomationStatus getStatus() {
        return status;
    }
}
