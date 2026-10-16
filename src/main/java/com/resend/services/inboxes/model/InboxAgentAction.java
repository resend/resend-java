package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents an action the inbox agent can take.
 */
public enum InboxAgentAction {
    /** The agent drafts replies. */
    DRAFT_REPLY("draft_reply"),
    /** The agent forwards threads. */
    FORWARD_THREAD("forward_thread"),
    /** The agent adds labels to threads. */
    ADD_LABELS("add_labels"),
    /** The agent assigns threads. */
    ASSIGN_THREAD("assign_thread"),
    /** The agent marks threads as spam. */
    MARK_AS_SPAM("mark_as_spam"),
    /** The agent archives threads. */
    ARCHIVE("archive"),
    /** The agent moves threads to trash. */
    MOVE_TO_TRASH("move_to_trash");

    private final String value;

    InboxAgentAction(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the action.
     *
     * @return The action value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates an InboxAgentAction from a string value.
     *
     * @param value The string value.
     * @return The corresponding InboxAgentAction.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static InboxAgentAction fromValue(String value) {
        for (InboxAgentAction item : InboxAgentAction.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown action: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
