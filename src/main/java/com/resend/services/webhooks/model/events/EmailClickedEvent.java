package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Webhook event triggered when a link in an email is clicked.
 */
public class EmailClickedEvent extends WebhookEventPayload {

    @JsonProperty("data")
    private EmailClickedEventData data;

    /**
     * Default constructor.
     */
    public EmailClickedEvent() {
    }

    /**
     * Gets the event data.
     *
     * @return The event data.
     */
    public EmailClickedEventData getData() {
        return data;
    }

    /**
     * Sets the event data.
     *
     * @param data The event data.
     */
    public void setData(EmailClickedEventData data) {
        this.data = data;
    }
}
