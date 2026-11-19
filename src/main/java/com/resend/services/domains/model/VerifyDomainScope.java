package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the scopes a domain verification can be limited to.
 */
public enum VerifyDomainScope {
    /**
     * Verify only the Forge record.
     */
    FORGE("forge");

    /**
     * Holds the string representation of the enum value.
     */
    private final String value;

    /**
     * Constructor for the VerifyDomainScope enum.
     *
     * @param value The string representation of the verification scope.
     */
    VerifyDomainScope(String value) {
        this.value = value;
    }

    /**
     * Retrieves the string representation of the verification scope.
     *
     * @return The string representation of the verification scope.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Returns the string representation of the verification scope.
     *
     * @return The string representation of the verification scope.
     */
    @Override
    public String toString() {
        return this.value;
    }
}
