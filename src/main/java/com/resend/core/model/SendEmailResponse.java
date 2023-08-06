package com.resend.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a response after sending an email.
 */
public class SendEmailResponse {
    @JsonProperty("id")
    private String id;

    public SendEmailResponse() {
    }

    /**
     * Constructs a SendEmailResponse with the provided ID.
     *
     * @param id The ID associated with the sent email.
     */
    public SendEmailResponse(String id) {
        this.id = id;
    }

    /**
     * Retrieves the ID associated with the sent email.
     *
     * @return The ID of the sent email.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID for the sent email.
     *
     * @param id The ID to be set.
     */
    public void setId(String id) {
        this.id = id;
    }
}
