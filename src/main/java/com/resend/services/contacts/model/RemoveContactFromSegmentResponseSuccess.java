package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the successful response from removing a contact from a segment.
 */
public class RemoveContactFromSegmentResponseSuccess {

    /**
     * The segment ID.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Indicates whether the contact was successfully removed from the segment.
     */
    @JsonProperty("deleted")
    private Boolean deleted;

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

    /**
     * Gets the deleted status.
     *
     * @return True if the contact was successfully removed from the segment.
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * Sets the deleted status.
     *
     * @param deleted True if the contact was successfully removed from the segment.
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
