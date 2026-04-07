package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ConditionOperator {
    EQ("eq"),
    NEQ("neq");

    private final String value;

    ConditionOperator(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
