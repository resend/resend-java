package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the successful response from adding a contact to a segment.
 */
public class AddContactToSegmentResponseSuccess {

    /**
     * The segment ID.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Gets the segment ID.
     *
     * @return The segment ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the segment ID.
     *
     * @param id The segment ID.
     */
    public void setId(String id) {
        this.id = id;
    }
}
