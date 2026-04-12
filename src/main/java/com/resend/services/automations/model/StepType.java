package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the type of a step in an automation workflow.
 */
public enum StepType {
    /** Trigger step that starts the automation. */
    TRIGGER("trigger"),
    /** Step that sends an email. */
    SEND_EMAIL("send_email"),
    /** Step that delays execution. */
    DELAY("delay"),
    /** Step that waits for an event. */
    WAIT_FOR_EVENT("wait_for_event"),
    /** Step that evaluates a condition. */
    CONDITION("condition"),
    /** Step that updates a contact. */
    CONTACT_UPDATE("contact_update"),
    /** Step that deletes a contact. */
    CONTACT_DELETE("contact_delete"),
    /** Step that adds a contact to a segment. */
    ADD_TO_SEGMENT("add_to_segment");

    private final String value;

    StepType(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the step type.
     *
     * @return The step type value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates a StepType from a string value.
     *
     * @param value The string value.
     * @return The corresponding StepType.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static StepType fromValue(String value) {
        for (StepType type : StepType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown step type: " + value);
    }
}
