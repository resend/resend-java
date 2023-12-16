package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for removing a contact.
 */
public class RemoveContactResponseSuccess extends BaseContact {

    @JsonProperty("deleted")
    private boolean deleted;

    /**
     * Default constructor
     */
    public RemoveContactResponseSuccess() {

    }

    /**
     * Constructs a successful response for removing a contact.
     *
     * @param id The ID of the removed contact.
     * @param object The object of the removed contact.
     * @param deleted The state of the removed contact.
     */
    public RemoveContactResponseSuccess(final String id, final String object, final boolean deleted) {
        super(id, object);
        this.deleted = deleted;
    }

    /**
     * Gets the state of the removed contact.
     *
     * @return The state of the removed contact.
     */
    public boolean getRemoved() {
        return deleted;
    }
}
