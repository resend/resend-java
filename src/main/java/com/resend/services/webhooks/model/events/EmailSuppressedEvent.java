package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Webhook event triggered when an email is suppressed.
 */
public class EmailSuppressedEvent extends WebhookEventPayload {

    @JsonProperty("data")
    private EmailSuppressedEventData data;

    /**
     * Default constructor.
     */
    public EmailSuppressedEvent() {
    }

    /**
     * Gets the event data.
     *
     * @return The event data.
     */
    public EmailSuppressedEventData getData() {
        return data;
    }

    /**
     * Sets the event data.
     *
     * @param data The event data.
     */
    public void setData(EmailSuppressedEventData data) {
        this.data = data;
    }
}
