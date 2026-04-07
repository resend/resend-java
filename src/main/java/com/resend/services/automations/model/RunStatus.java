package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RunStatus {
    RUNNING("running"),
    COMPLETED("completed"),
    FAILED("failed"),
    CANCELLED("cancelled");

    private final String value;

    RunStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

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
