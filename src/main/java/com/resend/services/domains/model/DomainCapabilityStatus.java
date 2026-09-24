package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the status of a domain capability.
 */
public enum DomainCapabilityStatus {
    /**
     *  Represents an enabled capability.
     */
    ENABLED("enabled"),
    /**
     *  Represents a disabled capability.
     */
    DISABLED("disabled");

    /**
     * Holds the string representation of the enum value.
     */
    private final String value;

    /**
     * Constructor for the DomainCapabilityStatus enum.
     *
     * @param value The string representation of the capability status.
     */
    DomainCapabilityStatus(String value) {
        this.value = value;
    }

    /**
     * Retrieves the string representation of the capability status.
     *
     * @return The string representation of the capability status.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Returns the string representation of the capability status.
     *
     * @return The string representation of the capability status.
     */
    @Override
    public String toString() {
        return this.value;
    }
}
