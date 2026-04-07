package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StepType {
    TRIGGER("trigger"),
    SEND_EMAIL("send_email"),
    DELAY("delay"),
    WAIT_FOR_EVENT("wait_for_event"),
    CONDITION("condition"),
    CONTACT_UPDATE("contact_update"),
    CONTACT_DELETE("contact_delete"),
    ADD_TO_SEGMENT("add_to_segment");

    private final String value;

    StepType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

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
