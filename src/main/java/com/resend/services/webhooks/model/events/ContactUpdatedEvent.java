package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Webhook event triggered when a contact is updated.
 */
public class ContactUpdatedEvent extends WebhookEventPayload {

    @JsonProperty("data")
    private ContactEventData data;

    /**
     * Default constructor.
     */
    public ContactUpdatedEvent() {
    }

    /**
     * Gets the event data.
     *
     * @return The event data.
     */
    public ContactEventData getData() {
        return data;
    }

    /**
     * Sets the event data.
     *
     * @param data The event data.
     */
    public void setData(ContactEventData data) {
        this.data = data;
    }
}
