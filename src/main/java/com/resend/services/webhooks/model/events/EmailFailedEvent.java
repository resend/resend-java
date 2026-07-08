package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Webhook event triggered when an email fails to send.
 */
public class EmailFailedEvent extends WebhookEventPayload {

    @JsonProperty("data")
    private EmailFailedEventData data;

    /**
     * Default constructor.
     */
    public EmailFailedEvent() {
    }

    /**
     * Gets the event data.
     *
     * @return The event data.
     */
    public EmailFailedEventData getData() {
        return data;
    }

    /**
     * Sets the event data.
     *
     * @param data The event data.
     */
    public void setData(EmailFailedEventData data) {
        this.data = data;
    }
}
