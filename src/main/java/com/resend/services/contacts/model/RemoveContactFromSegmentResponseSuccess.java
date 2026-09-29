package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the successful response from removing a contact from a segment.
 */
public class RemoveContactFromSegmentResponseSuccess {

    /**
     * Creates a new RemoveContactFromSegmentResponseSuccess instance.
     */
    public RemoveContactFromSegmentResponseSuccess() {
    }

    /**
     * The segment ID.
     */
    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Indicates whether the contact was successfully removed from the segment.
     */
    @JsonProperty("deleted")
    private @Nullable Boolean deleted;

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

    /**
     * Gets the deleted status.
     *
     * @return True if the contact was successfully removed from the segment.
     */
    public @Nullable Boolean getDeleted() {
        return deleted;
    }

    /**
     * Sets the deleted status.
     *
     * @param deleted True if the contact was successfully removed from the segment.
     */
    public void setDeleted(@Nullable Boolean deleted) {
        this.deleted = deleted;
    }
}
