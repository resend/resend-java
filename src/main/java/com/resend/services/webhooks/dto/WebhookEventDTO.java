package com.resend.services.webhooks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.model.WebhookEventStatus;

/**
 * Data Transfer Object for webhook event data in list responses.
 */
public class WebhookEventDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("status")
    private WebhookEventStatus status;

    /**
     * Constructs an empty webhook event.
     */
    public WebhookEventDTO() {
    }

    /**
     * Gets the webhook event ID.
     *
     * @return The webhook event ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the event type.
     *
     * @return The event type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the delivery status.
     *
     * @return The delivery status.
     */
    public WebhookEventStatus getStatus() {
        return status;
    }
}
