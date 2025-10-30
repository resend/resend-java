package com.resend.services.contactproperties.model;

/**
 * Represents a successful response for updating a contact property.
 */
public class UpdateContactPropertyResponseSuccess extends BaseContactProperty {

    /**
     * Default constructor
     */
    public UpdateContactPropertyResponseSuccess() {
    }

    /**
     * Constructs a successful response for updating a contact property.
     *
     * @param id        The ID of the contact property.
     * @param object    The object type of the contact property.
     */
    public UpdateContactPropertyResponseSuccess(final String id, final String object) {
        super(id, object);
    }

}
