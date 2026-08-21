package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the bounce type used to filter a broadcast's bounced recipients.
 * Only meaningful when the {@link BroadcastRecipientEventType} filter is {@code bounced}.
 */
public enum BroadcastRecipientBounceType {
    /** The recipient's email address is permanently invalid. */
    PERMANENT("permanent"),
    /** The bounce was temporary, e.g. a full mailbox or a temporary server issue. */
    TRANSIENT("transient"),
    /** The bounce reason could not be determined. */
    UNDETERMINED("undetermined");

    private final String value;

    BroadcastRecipientBounceType(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the bounce type.
     *
     * @return The bounce type value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates a BroadcastRecipientBounceType from a string value.
     *
     * @param value The string value.
     * @return The corresponding BroadcastRecipientBounceType.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static BroadcastRecipientBounceType fromValue(String value) {
        for (BroadcastRecipientBounceType bounceType : BroadcastRecipientBounceType.values()) {
            if (bounceType.value.equals(value)) {
                return bounceType;
            }
        }
        throw new IllegalArgumentException("Unknown bounce type: " + value);
    }
}
