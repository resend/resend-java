package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for forwarding a thread email.
 */
public class ForwardInboxThreadEmailResponseSuccess {

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor
     */
    public ForwardInboxThreadEmailResponseSuccess() {

    }

    /**
     * Constructs a ForwardInboxThreadEmailResponseSuccess.
     *
     * @param id The ID of the sent email. Use it with the emails endpoints.
     */
    public ForwardInboxThreadEmailResponseSuccess(String id) {
        this.id = id;
    }

    /**
     * Get the ID of the sent email. Use it with the emails endpoints.
     *
     * @return The ID of the sent email. Use it with the emails endpoints.
     */
    public String getId() {
        return id;
    }
}
