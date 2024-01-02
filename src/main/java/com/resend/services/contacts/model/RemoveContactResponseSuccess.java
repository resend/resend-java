package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for removing a contact.
 */
public class RemoveContactResponseSuccess extends BaseContact {

    @JsonProperty("deleted")
    private boolean deleted;

    @JsonProperty("contact")
    private String contact;

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
     * @param contact The contact of the removed contact.
     */
    public RemoveContactResponseSuccess(final String id, final String object, final boolean deleted, final String contact) {
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

    /**
     * Gets the contact of the removed item.
     *
     * @return The contact of the removed item.
     */
    public String getContact() {
        return contact;
    }
}
