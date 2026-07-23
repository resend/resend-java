package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the origin of a suppression.
 */
public enum SuppressionOrigin {
    /** Emails suppressed automatically after a bounce. */
    BOUNCE("bounce"),
    /** Emails suppressed due to a user complaint. */
    COMPLAINT("complaint"),
    /** Emails suppressed by your team manually. */
    MANUAL("manual");

    private final String value;

    SuppressionOrigin(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the origin.
     *
     * @return The origin value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates a SuppressionOrigin from a string value.
     *
     * @param value The string value.
     * @return The corresponding SuppressionOrigin.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static SuppressionOrigin fromValue(String value) {
        for (SuppressionOrigin origin : SuppressionOrigin.values()) {
            if (origin.value.equals(value)) {
                return origin;
            }
        }
        throw new IllegalArgumentException("Unknown origin: " + value);
    }
}
