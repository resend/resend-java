package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.Map;

/**
 * Represents a successful response for getting a contact.
 * Extends the Contact class.
 */
public class GetContactResponseSuccess extends Contact {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("properties")
    private @Nullable Map<String, ContactPropertyValue> properties;

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
    public GetContactResponseSuccess(final @Nullable String object, final @Nullable String id, final @Nullable String email, final @Nullable String firstName, final @Nullable String lastName, final @Nullable String createdAt, final boolean unsubscribed) {
        super(id, email, firstName, lastName, createdAt, unsubscribed);
        this.object = object;
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
     * Gets the custom properties of the contact. Only available for global contacts.
     *
     * @return The custom properties of the contact.
     */
    public @Nullable Map<String, ContactPropertyValue> getProperties() {
        return properties;
    }
}
