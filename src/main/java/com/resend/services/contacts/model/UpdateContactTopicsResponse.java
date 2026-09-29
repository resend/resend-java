package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response for updating contact topics.
 */
public class UpdateContactTopicsResponse {

    @JsonProperty("id")
    private @Nullable String id;

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
    public UpdateContactTopicsResponse(final @Nullable String id) {
        this.id = id;
    }

    /**
     * Gets the contact ID.
     *
     * @return The contact ID.
     */
    public @Nullable String getId() {
        return id;
    }
}
