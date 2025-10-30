package com.resend.services.contactproperties.model;

/**
 * Represents a successful response for creating a contact property.
 */
public class CreateContactPropertyResponseSuccess extends BaseContactProperty {

    /**
     * Default constructor
     */
    public CreateContactPropertyResponseSuccess() {
    }

    /**
     * Constructs a successful response for creating a contact property.
     *
     * @param id        The ID of the contact property.
     * @param object    The object type of the contact property.
     */
    public CreateContactPropertyResponseSuccess(final String id, final String object) {
        super(id, object);
    }

}
