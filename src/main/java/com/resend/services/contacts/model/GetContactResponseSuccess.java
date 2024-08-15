package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for getting a contact.
 * Extends the Contact class.
 */
public class GetContactResponseSuccess extends Contact {

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor
     */
    public GetContactResponseSuccess() {

    }

    /**
     * Creates an instance of Contact with the specified attributes.
     *
     * @param object        The object of the contact item.
     * @param id            The ID of the contact item.
     * @param email         The email of the contact item.
     * @param firstName     The first name of the contact item.
     * @param lastName      The last name of the contact item.
     * @param createdAt     The creation timestamp of the contact item.
     * @param unsubscribed  The subscription state contact item.
     */
    public GetContactResponseSuccess(final String object, final String id, final String email, final String firstName, final String lastName, final String createdAt, final boolean unsubscribed) {
        super(id, email, firstName, lastName, createdAt, unsubscribed);
        this.object = object;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }
}
