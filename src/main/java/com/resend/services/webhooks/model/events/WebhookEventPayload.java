package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base class for verified webhook event payloads.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailSentEvent.class, name = "email.sent"),
        @JsonSubTypes.Type(value = EmailScheduledEvent.class, name = "email.scheduled"),
        @JsonSubTypes.Type(value = EmailDeliveredEvent.class, name = "email.delivered"),
        @JsonSubTypes.Type(value = EmailDeliveryDelayedEvent.class, name = "email.delivery_delayed"),
        @JsonSubTypes.Type(value = EmailComplainedEvent.class, name = "email.complained"),
        @JsonSubTypes.Type(value = EmailBouncedEvent.class, name = "email.bounced"),
        @JsonSubTypes.Type(value = EmailOpenedEvent.class, name = "email.opened"),
        @JsonSubTypes.Type(value = EmailClickedEvent.class, name = "email.clicked"),
        @JsonSubTypes.Type(value = EmailReceivedEvent.class, name = "email.received"),
        @JsonSubTypes.Type(value = EmailFailedEvent.class, name = "email.failed"),
        @JsonSubTypes.Type(value = EmailSuppressedEvent.class, name = "email.suppressed"),
        @JsonSubTypes.Type(value = ContactCreatedEvent.class, name = "contact.created"),
        @JsonSubTypes.Type(value = ContactUpdatedEvent.class, name = "contact.updated"),
        @JsonSubTypes.Type(value = ContactDeletedEvent.class, name = "contact.deleted"),
        @JsonSubTypes.Type(value = DomainCreatedEvent.class, name = "domain.created"),
        @JsonSubTypes.Type(value = DomainUpdatedEvent.class, name = "domain.updated"),
        @JsonSubTypes.Type(value = DomainDeletedEvent.class, name = "domain.deleted")
})
public abstract class WebhookEventPayload {

    @JsonProperty("type")
    private String type;

    @JsonProperty("created_at")
    private String createdAt;

    /**
     * Default constructor.
     */
    protected WebhookEventPayload() {
    }

    /**
     * Gets the webhook event type.
     *
     * @return The event type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the webhook event type.
     *
     * @param type The event type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the event creation timestamp.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the event creation timestamp.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
