package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents a folder of an inbox.
 */
public enum InboxFolder {
    /** Threads with at least one received email. */
    INBOX("inbox"),
    /** Threads moved to the archive. */
    ARCHIVE("archive"),
    /** Threads moved to spam. */
    SPAM("spam"),
    /** Threads whose emails are all outbound. Assigned automatically. */
    SENT("sent"),
    /** Threads scheduled for deletion in 30 days. */
    TRASH("trash");

    private final String value;

    InboxFolder(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the folder.
     *
     * @return The folder value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates a InboxFolder from a string value.
     *
     * @param value The string value.
     * @return The corresponding InboxFolder.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static InboxFolder fromValue(String value) {
        for (InboxFolder item : InboxFolder.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown folder: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
