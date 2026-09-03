package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response from replaying a webhook event.
 */
public class ReplayWebhookEventResponseSuccess {
    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Constructs an empty webhook event replay response.
     */
    public ReplayWebhookEventResponseSuccess() {
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Gets the webhook event ID.
     *
     * @return The webhook event ID.
     */
    public String getId() {
        return id;
    }
}
