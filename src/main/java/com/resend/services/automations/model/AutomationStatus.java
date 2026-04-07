package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AutomationStatus {
    ENABLED("enabled"),
    DISABLED("disabled");

    private final String value;

    AutomationStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

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
