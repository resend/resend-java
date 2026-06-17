package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for creating a contact import.
 */
public class CreateContactImportResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor.
     */
    public CreateContactImportResponseSuccess() {
    }

    /**
     * Constructs a successful response for creating a contact import.
     *
     * @param object The object type (e.g. {@code "contact_import"}).
     * @param id     The ID of the created contact import.
     */
    public CreateContactImportResponseSuccess(final String object, final String id) {
        this.object = object;
        this.id = id;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Gets the ID of the contact import.
     *
     * @return The contact import ID.
     */
    public String getId() {
        return id;
    }
}
