package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.resend.core.util.DateTimeDeserializer;
import com.resend.core.util.DateTimeSerializer;

import java.time.OffsetDateTime;

/**
 * Represents a contact item.
 */
public class Contact {

    @JsonProperty("id")
    private String id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("created_at")
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private OffsetDateTime createdAt;

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
    public Contact(final String id, final String email, final String firstName, final String lastName, final OffsetDateTime createdAt, final boolean unsubscribed) {
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
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the contact item.
     *
     * @return The name of the contact item.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the first name of the contact.
     *
     * @return The first name of the contact.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the contact.
     *
     * @return The last name of the contact.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the creation timestamp of the contact item.
     *
     * @return The creation timestamp of the contact item.
     */
    public OffsetDateTime getCreatedAt() {
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