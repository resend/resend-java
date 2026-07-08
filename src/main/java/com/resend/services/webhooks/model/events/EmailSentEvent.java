package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Webhook event triggered when an email is sent.
 */
public class EmailSentEvent extends WebhookEventPayload {

    @JsonProperty("data")
    private BaseEmailEventData data;

    /**
     * Default constructor.
     */
    public EmailSentEvent() {
    }

    /**
     * Gets the event data.
     *
     * @return The event data.
     */
    public BaseEmailEventData getData() {
        return data;
    }

    /**
     * Sets the event data.
     *
     * @param data The event data.
     */
    public void setData(BaseEmailEventData data) {
        this.data = data;
    }
}
