package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for replying to a thread email.
 */
public class ReplyInboxThreadEmailResponseSuccess {

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor
     */
    public ReplyInboxThreadEmailResponseSuccess() {

    }

    /**
     * Constructs a ReplyInboxThreadEmailResponseSuccess.
     *
     * @param id The ID of the sent email. Use it with the emails endpoints.
     */
    public ReplyInboxThreadEmailResponseSuccess(String id) {
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
