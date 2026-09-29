package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Base contact with common properties
 */
public abstract class BaseContact {
    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("object")
    private @Nullable String object;

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
    public BaseContact(final @Nullable String id, final @Nullable String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Gets the ID of the contact item.
     *
     * @return The ID of the contact item.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the object of the contact item.
     *
     * @return The object of the contact item.
     */
    public @Nullable String getObject() {
        return object;
    }

}