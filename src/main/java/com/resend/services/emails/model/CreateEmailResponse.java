package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a response after sending an email.
 */
public class CreateEmailResponse {

    /**
     * The unique identifier associated with the email.
     */
    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Constructs a new instance of {@code CreateEmailResponse}.
     */
    public CreateEmailResponse() {
    }

    /**
     * Constructs a CreateEmailResponse with the provided ID.
     *
     * @param id The ID associated with the sent email.
     */
    public CreateEmailResponse(@Nullable String id) {
        this.id = id;
    }

    /**
     * Retrieves the ID associated with the sent email.
     *
     * @return The ID of the sent email.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the ID for the sent email.
     *
     * @param id The ID to be set.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }
}
