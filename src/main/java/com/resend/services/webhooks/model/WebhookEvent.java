package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the types of events that can trigger a webhook.
 */
public enum WebhookEvent {
    /** Triggered when an email is sent. */
    EMAIL_SENT("email.sent"),
    /** Triggered when an email is delivered. */
    EMAIL_DELIVERED("email.delivered"),
    /** Triggered when an email delivery is delayed. */
    EMAIL_DELIVERY_DELAYED("email.delivery_delayed"),
    /** Triggered when an email complaint is received. */
    EMAIL_COMPLAINED("email.complained"),
    /** Triggered when an email bounces. */
    EMAIL_BOUNCED("email.bounced"),
    /** Triggered when an email is opened. */
    EMAIL_OPENED("email.opened"),
    /** Triggered when a link in an email is clicked. */
    EMAIL_CLICKED("email.clicked"),
    /** Triggered when an inbound email is received. */
    EMAIL_RECEIVED("email.received"),
    /** Triggered when an email fails to send. */
    EMAIL_FAILED("email.failed"),
    /** Triggered when a contact is created. */
    CONTACT_CREATED("contact.created"),
    /** Triggered when a contact is updated. */
    CONTACT_UPDATED("contact.updated"),
    /** Triggered when a contact is deleted. */
    CONTACT_DELETED("contact.deleted"),
    /** Triggered when a domain is created. */
    DOMAIN_CREATED("domain.created"),
    /** Triggered when a domain is updated. */
    DOMAIN_UPDATED("domain.updated"),
    /** Triggered when a domain is deleted. */
    DOMAIN_DELETED("domain.deleted");

    private final String value;

    WebhookEvent(String value) {
        this.value = value;
    }

    /**
     * Gets the string value of the webhook event.
     *
     * @return the string value of this event
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
