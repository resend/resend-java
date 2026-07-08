package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Webhook event triggered when a domain is deleted.
 */
public class DomainDeletedEvent extends WebhookEventPayload {

    @JsonProperty("data")
    private DomainEventData data;

    /**
     * Default constructor.
     */
    public DomainDeletedEvent() {
    }

    /**
     * Gets the event data.
     *
     * @return The event data.
     */
    public DomainEventData getData() {
        return data;
    }

    /**
     * Sets the event data.
     *
     * @param data The event data.
     */
    public void setData(DomainEventData data) {
        this.data = data;
    }
}
