package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the TLS settings for a domain.
 */
public enum Tls {
    /**
     *  Represents an enforced TLS setting.
     */
    ENFORCED("enforced"),
    /**
     *  Represents an opportunistic TLS setting.
     */
    OPPORTUNISTIC("opportunistic");

    /**
     * Holds the string representation of the enum value.
     */
    private final String value;

    /**
     * Constructor for the Tls enum.
     *
     * @param value The string representation of the TLS setting.
     */
    Tls(String value) {
        this.value = value;
    }

    /**
     * Retrieves the string representation of the TLS setting.
     *
     * @return The string representation of the TLS setting.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Returns the string representation of the TLS setting.
     *
     * @return The string representation of the TLS setting.
     */
    @Override
    public String toString() {
        return this.value;
    }
}
