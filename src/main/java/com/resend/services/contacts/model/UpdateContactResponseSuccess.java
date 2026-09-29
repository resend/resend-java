package com.resend.services.contacts.model;

import org.jspecify.annotations.Nullable;


/**
 * Represents a UpdateContactResponseSuccess class.
 */
public class UpdateContactResponseSuccess extends BaseContact {

    /**
     * Default constructor
     */
    public UpdateContactResponseSuccess() {
    }

    /**
     * Constructs a successful response for updating a contact.
     *
     * @param id        The ID of the contact.
     * @param object      The object of the contact.
     */
    public UpdateContactResponseSuccess(final @Nullable String id, final @Nullable String object) {
        super(id, object);
    }

}

