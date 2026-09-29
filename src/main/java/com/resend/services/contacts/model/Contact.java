package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a contact item.
 */
public class Contact {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("email")
    private @Nullable String email;

    @JsonProperty("first_name")
    private @Nullable String firstName;

    @JsonProperty("last_name")
    private @Nullable String lastName;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("unsubscribed")
    private boolean unsubscribed;

    /**
     * Default constructor
     */
    public Contact() {

    }

    /**
     * Creates an instance of Contact with the specified attributes.
     *
     * @param id            The ID of the contact item.
     * @param email         The email of the contact item.
     * @param firstName     The first name of the contact item.
     * @param lastName      The last name of the contact item.
     * @param createdAt     The creation timestamp of the contact item.
     * @param unsubscribed  The subscription state contact item.
     */
    public Contact(final @Nullable String id, final @Nullable String email, final @Nullable String firstName, final @Nullable String lastName, final @Nullable String createdAt, final boolean unsubscribed) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.unsubscribed = unsubscribed;
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
     * Gets the name of the contact item.
     *
     * @return The name of the contact item.
     */
    public @Nullable String getEmail() {
        return email;
    }

    /**
     * Gets the first name of the contact.
     *
     * @return The first name of the contact.
     */
    public @Nullable String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the contact.
     *
     * @return The last name of the contact.
     */
    public @Nullable String getLastName() {
        return lastName;
    }

    /**
     * Gets the creation timestamp of the contact item.
     *
     * @return The creation timestamp of the contact item.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the subscription state of the contact.
     *
     * @return The subscription state of the contact.
     */
    public boolean getUnsubscribed() {
        return unsubscribed;
    }
}