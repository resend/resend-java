package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for creating a contact.
 * Extends the Contact class.
 */
public class CreateContactResponseSuccess extends BaseContact {

    /**
     * Default constructor
     */
    public CreateContactResponseSuccess() {
    }

    /**
     * Constructs a successful response for creating a contact.
     *
     * @param id        The ID of the contact.
     * @param object      The object of the contact.
     */
    public CreateContactResponseSuccess(final String id, final String object) {
        super(id, object);
    }

}

