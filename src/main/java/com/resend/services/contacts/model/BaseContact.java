package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base contact with common properties
 */
public abstract class BaseContact {
    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor
     */
    public BaseContact() {
    }

    /**
     * Constructs a successful response for creating a contact.
     *
     * @param id        The ID of the contact.
     * @param object      The object of the contact.
     */
    public BaseContact(final String id, final String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Gets the ID of the contact item.
     *
     * @return The ID of the contact item.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the object of the contact item.
     *
     * @return The object of the contact item.
     */
    public String getObject() {
        return object;
    }

}