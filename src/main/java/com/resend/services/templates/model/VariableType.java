package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the type of a template variable.
 */
public enum VariableType {
    /**
     * String type variable.
     */
    STRING("string"),

    /**
     * Number type variable.
     */
    NUMBER("number");

    private final String value;

    VariableType(String value) {
        this.value = value;
    }

    /**
     * Gets the string value of the variable type.
     *
     * @return The string value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }
}
