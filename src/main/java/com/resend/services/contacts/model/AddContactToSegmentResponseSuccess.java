package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the successful response from adding a contact to a segment.
 */
public class AddContactToSegmentResponseSuccess {

    /**
     * Creates a new AddContactToSegmentResponseSuccess instance.
     */
    public AddContactToSegmentResponseSuccess() {
    }

    /**
     * The segment ID.
     */
    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Gets the segment ID.
     *
     * @return The segment ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the segment ID.
     *
     * @param id The segment ID.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }
}
