package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a response after updating an email.
 */
public class UpdateEmailResponse extends EmailResponse {
    /**
     * Constructs a new instance of {@code UpdateEmailResponse}.
     */
    public UpdateEmailResponse() {
    }

    /**
     * Constructs a CreateEmailResponse with the provided ID.
     *
     * @param id The ID associated with the sent email.
     * @param object The resource object.
     */
    public UpdateEmailResponse(String id, String object) {
        super(id, object);
    }
}

