package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the types of events that can trigger a webhook.
 */
public enum WebhookEvent {
    EMAIL_SENT("email.sent"),
    EMAIL_DELIVERED("email.delivered"),
    EMAIL_DELIVERY_DELAYED("email.delivery_delayed"),
    EMAIL_COMPLAINED("email.complained"),
    EMAIL_BOUNCED("email.bounced"),
    EMAIL_OPENED("email.opened"),
    EMAIL_CLICKED("email.clicked"),
    EMAIL_RECEIVED("email.received"),
    EMAIL_FAILED("email.failed"),
    CONTACT_CREATED("contact.created"),
    CONTACT_UPDATED("contact.updated"),
    CONTACT_DELETED("contact.deleted"),
    DOMAIN_CREATED("domain.created"),
    DOMAIN_UPDATED("domain.updated"),
    DOMAIN_DELETED("domain.deleted");

    private final String value;

    WebhookEvent(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
