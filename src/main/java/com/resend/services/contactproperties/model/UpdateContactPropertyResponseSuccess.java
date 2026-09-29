package com.resend.services.contactproperties.model;

import org.jspecify.annotations.Nullable;


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
    public UpdateContactPropertyResponseSuccess(final @Nullable String id, final @Nullable String object) {
        super(id, object);
    }

}
