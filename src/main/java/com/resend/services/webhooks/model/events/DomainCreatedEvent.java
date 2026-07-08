package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Webhook event triggered when a domain is created.
 */
public class DomainCreatedEvent extends WebhookEventPayload {

    @JsonProperty("data")
    private DomainEventData data;

    /**
     * Default constructor.
     */
    public DomainCreatedEvent() {
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
