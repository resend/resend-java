package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response from stopping an automation.
 */
public class StopAutomationResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("status")
    private @Nullable AutomationStatus status;

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
    public StopAutomationResponseSuccess(@Nullable String object, @Nullable String id, @Nullable AutomationStatus status) {
        this.object = object;
        this.id = id;
        this.status = status;
    }

    /**
     * Retrieves the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Retrieves the stopped automation ID.
     *
     * @return The automation ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Retrieves the automation status after stopping.
     *
     * @return The automation status.
     */
    public @Nullable AutomationStatus getStatus() {
        return status;
    }
}
