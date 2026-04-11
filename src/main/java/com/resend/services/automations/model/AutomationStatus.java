package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the status of an automation.
 */
public enum AutomationStatus {
    /** Automation is enabled and active. */
    ENABLED("enabled"),
    /** Automation is disabled and inactive. */
    DISABLED("disabled");

    private final String value;

    AutomationStatus(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the status.
     *
     * @return The status value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates an AutomationStatus from a string value.
     *
     * @param value The string value.
     * @return The corresponding AutomationStatus.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static AutomationStatus fromValue(String value) {
        for (AutomationStatus status : AutomationStatus.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
