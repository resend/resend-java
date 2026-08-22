package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the event type used to filter a broadcast's recipients.
 */
public enum BroadcastRecipientEventType {
    /** Recipients the broadcast was sent to. */
    SENT("sent"),
    /** Recipients whose email was delivered. */
    DELIVERED("delivered"),
    /** Recipients who opened the email. */
    OPENED("opened"),
    /** Recipients who clicked a link in the email. */
    CLICKED("clicked"),
    /** Recipients whose email bounced. */
    BOUNCED("bounced"),
    /** Recipients who marked the email as spam. */
    COMPLAINED("complained"),
    /** Recipients who unsubscribed. */
    UNSUBSCRIBED("unsubscribed"),
    /** Recipients who were suppressed and not sent the email. */
    SUPPRESSED("suppressed");

    private final String value;

    BroadcastRecipientEventType(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the event type.
     *
     * @return The event type value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates a BroadcastRecipientEventType from a string value.
     *
     * @param value The string value.
     * @return The corresponding BroadcastRecipientEventType.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static BroadcastRecipientEventType fromValue(String value) {
        for (BroadcastRecipientEventType type : BroadcastRecipientEventType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown type: " + value);
    }
}
