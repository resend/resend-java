package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the type of an edge connecting steps in an automation workflow.
 */
public enum EdgeType {
    DEFAULT("default"),
    CONDITION_MET("condition_met"),
    CONDITION_NOT_MET("condition_not_met"),
    TIMEOUT("timeout"),
    EVENT_RECEIVED("event_received");

    private final String value;

    EdgeType(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the edge type.
     *
     * @return The edge type value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates an EdgeType from a string value.
     *
     * @param value The string value.
     * @return The corresponding EdgeType.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static EdgeType fromValue(String value) {
        for (EdgeType type : EdgeType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown edge type: " + value);
    }
}
