package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

    @JsonValue
    public String getValue() {
        return value;
    }

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
