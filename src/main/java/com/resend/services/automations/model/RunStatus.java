package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the status of an automation run.
 */
public enum RunStatus {
    /** Run is currently in progress. */
    RUNNING("running"),
    /** Run completed successfully. */
    COMPLETED("completed"),
    /** Run failed with an error. */
    FAILED("failed"),
    /** Run was cancelled. */
    CANCELLED("cancelled");

    private final String value;

    RunStatus(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the run status.
     *
     * @return The run status value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates a RunStatus from a string value.
     *
     * @param value The string value.
     * @return The corresponding RunStatus.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static RunStatus fromValue(String value) {
        for (RunStatus status : RunStatus.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
