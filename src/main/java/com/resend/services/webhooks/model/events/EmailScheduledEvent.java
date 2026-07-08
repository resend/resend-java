package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Webhook event triggered when an email is scheduled.
 */
public class EmailScheduledEvent extends WebhookEventPayload {

    @JsonProperty("data")
    private BaseEmailEventData data;

    /**
     * Default constructor.
     */
    public EmailScheduledEvent() {
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
