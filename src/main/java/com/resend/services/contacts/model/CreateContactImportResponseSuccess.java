package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response for creating a contact import.
 */
public class CreateContactImportResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

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
    public CreateContactImportResponseSuccess(final @Nullable String object, final @Nullable String id) {
        this.object = object;
        this.id = id;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Gets the ID of the contact import.
     *
     * @return The contact import ID.
     */
    public @Nullable String getId() {
        return id;
    }
}
