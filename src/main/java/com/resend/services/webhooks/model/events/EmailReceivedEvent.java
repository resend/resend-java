package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Webhook event triggered when an inbound email is received.
 */
public class EmailReceivedEvent extends WebhookEventPayload {

    @JsonProperty("data")
    private ReceivedEmailEventData data;

    /**
     * Default constructor.
     */
    public EmailReceivedEvent() {
    }

    /**
     * Gets the event data.
     *
     * @return The event data.
     */
    public ReceivedEmailEventData getData() {
        return data;
    }

    /**
     * Sets the event data.
     *
     * @param data The event data.
     */
    public void setData(ReceivedEmailEventData data) {
        this.data = data;
    }
}
