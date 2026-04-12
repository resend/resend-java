package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the comparison operator for condition steps.
 */
public enum ConditionOperator {
    /** Equals operator. */
    EQ("eq"),
    /** Not equals operator. */
    NEQ("neq");

    private final String value;

    ConditionOperator(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the operator.
     *
     * @return The operator value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }
}
