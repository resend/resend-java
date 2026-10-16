package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for sending a draft.
 */
public class SendInboxDraftResponseSuccess {

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor
     */
    public SendInboxDraftResponseSuccess() {

    }

    /**
     * Constructs a SendInboxDraftResponseSuccess.
     *
     * @param id The ID of the sent email. Use it with the emails endpoints.
     */
    public SendInboxDraftResponseSuccess(String id) {
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
