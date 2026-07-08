package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Webhook event triggered when an email bounces.
 */
public class EmailBouncedEvent extends WebhookEventPayload {

    @JsonProperty("data")
    private EmailBouncedEventData data;

    /**
     * Default constructor.
     */
    public EmailBouncedEvent() {
    }

    /**
     * Gets the event data.
     *
     * @return The event data.
     */
    public EmailBouncedEventData getData() {
        return data;
    }

    /**
     * Sets the event data.
     *
     * @param data The event data.
     */
    public void setData(EmailBouncedEventData data) {
        this.data = data;
    }
}
