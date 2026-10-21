package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the tone the inbox agent writes in.
 */
public enum InboxAgentTone {
    /** Short replies in a professional tone. */
    CONCISE_PROFESSIONAL("concise_professional"),
    /** Short replies in a casual tone. */
    CONCISE_CASUAL("concise_casual"),
    /** Longer replies in a professional tone. */
    EXPANDED_PROFESSIONAL("expanded_professional"),
    /** Longer replies in a casual tone. */
    EXPANDED_CASUAL("expanded_casual");

    private final String value;

    InboxAgentTone(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the tone.
     *
     * @return The tone value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates an InboxAgentTone from a string value.
     *
     * @param value The string value.
     * @return The corresponding InboxAgentTone.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static InboxAgentTone fromValue(String value) {
        for (InboxAgentTone item : InboxAgentTone.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown tone: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
