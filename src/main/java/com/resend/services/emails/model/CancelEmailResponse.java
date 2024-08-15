package com.resend.services.emails.model;

/**
 * Represents the cancel email response
 */
public class CancelEmailResponse extends EmailResponse {
    /**
     * Constructs a new instance of {@code CancelEmailResponse}.
     */
    public CancelEmailResponse() {
    }

    /**
     * Constructs a CancelEmailResponse with the provided ID.
     *
     * @param id The ID associated with the sent email.
     * @param object The resource object.
     */
    public CancelEmailResponse(String id, String object) {
        super(id, object);
    }
}