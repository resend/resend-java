package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the type of a connection between steps in an automation workflow.
 */
public enum ConnectionType {
    DEFAULT("default"),
    CONDITION_MET("condition_met"),
    CONDITION_NOT_MET("condition_not_met"),
    TIMEOUT("timeout"),
    EVENT_RECEIVED("event_received");

    private final String value;

    ConnectionType(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the connection type.
     *
     * @return The connection type value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates a ConnectionType from a string value.
     *
     * @param value The string value.
     * @return The corresponding ConnectionType.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static ConnectionType fromValue(String value) {
        for (ConnectionType type : ConnectionType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown connection type: " + value);
    }
}
