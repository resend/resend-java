package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents a folder a thread can be moved to. {@code sent} is assigned automatically and cannot be set.
 */
public enum MoveInboxThreadFolder {
    /** Moves the thread to the inbox. */
    INBOX("inbox"),
    /** Moves the thread to the archive. */
    ARCHIVE("archive"),
    /** Moves the thread to spam. */
    SPAM("spam"),
    /** Moves the thread to trash and schedules it for deletion in 30 days. */
    TRASH("trash");

    private final String value;

    MoveInboxThreadFolder(String value) {
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
     * Creates a MoveInboxThreadFolder from a string value.
     *
     * @param value The string value.
     * @return The corresponding MoveInboxThreadFolder.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static MoveInboxThreadFolder fromValue(String value) {
        for (MoveInboxThreadFolder item : MoveInboxThreadFolder.values()) {
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
