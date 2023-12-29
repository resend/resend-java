package com.resend.services.contacts.model;

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
    public UpdateContactResponseSuccess(final String id, final String object) {
        super(id, object);
    }

}

