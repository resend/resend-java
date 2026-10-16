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
    /** Triggered when a contact's topic subscriptions change. */
    CONTACT_TOPICS_UPDATED("contact.topics.updated"),
    /** Triggered when a domain is created. */
    DOMAIN_CREATED("domain.created"),
    /** Triggered when a domain is updated. */
    DOMAIN_UPDATED("domain.updated"),
    /** Triggered when a domain is deleted. */
    DOMAIN_DELETED("domain.deleted"),
    /** Triggered when an email address is added to the suppression list. */
    SUPPRESSION_ADDED("suppression.added"),
    /** Triggered when an email address is removed from the suppression list. */
    SUPPRESSION_REMOVED("suppression.removed"),
    /** Triggered when a topic is created. */
    TOPIC_CREATED("topic.created"),
    /** Triggered when a topic is updated. */
    TOPIC_UPDATED("topic.updated"),
    /** Triggered when a topic is deleted. */
    TOPIC_DELETED("topic.deleted"),
    /** Triggered when an inbox is created. */
    INBOX_CREATED("inbox.created"),
    /** Triggered when the settings of an inbox change. */
    INBOX_UPDATED("inbox.updated"),
    /** Triggered when an inbox is deleted. */
    INBOX_DELETED("inbox.deleted"),
    /** Triggered when the first email of a thread lands in an inbox. */
    INBOX_THREAD_CREATED("inbox.thread.created"),
    /** Triggered when an inbound email is added to a thread. */
    INBOX_EMAIL_RECEIVED("inbox.email.received"),
    /** Triggered when an outbound email is accepted for delivery and added to a thread. */
    INBOX_EMAIL_SENT("inbox.email.sent"),
    /** Triggered when a thread moves between folders. */
    INBOX_THREAD_FOLDER_UPDATED("inbox.thread.folder.updated"),
    /** Triggered when a thread is assigned or reassigned. */
    INBOX_THREAD_ASSIGNED("inbox.thread.assigned"),
    /** Triggered when a thread is unassigned. */
    INBOX_THREAD_UNASSIGNED("inbox.thread.unassigned"),
    /** Triggered when a label is applied to or removed from a thread. */
    INBOX_THREAD_LABELS_UPDATED("inbox.thread.labels.updated"),
    /** Triggered when a draft is created. */
    INBOX_DRAFT_CREATED("inbox.draft.created"),
    /** Triggered when a draft is edited. */
    INBOX_DRAFT_UPDATED("inbox.draft.updated"),
    /** Triggered when a draft is sent. */
    INBOX_DRAFT_SENT("inbox.draft.sent"),
    /** Triggered when a draft is discarded. */
    INBOX_DRAFT_DELETED("inbox.draft.deleted");

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
