package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for updating contact topics.
 */
public class UpdateContactTopicsResponse {

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor
     */
    public UpdateContactTopicsResponse() {
    }

    /**
     * Constructs an UpdateContactTopicsResponse with the specified contact ID.
     *
     * @param id The contact ID.
     */
    public UpdateContactTopicsResponse(final String id) {
        this.id = id;
    }

    /**
     * Gets the contact ID.
     *
     * @return The contact ID.
     */
    public String getId() {
        return id;
    }
}
