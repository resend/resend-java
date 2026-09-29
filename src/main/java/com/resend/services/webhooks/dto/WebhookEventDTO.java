package com.resend.services.webhooks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.model.WebhookEventStatus;
import org.jspecify.annotations.Nullable;

/**
 * Data Transfer Object for webhook event data in list responses.
 */
public class WebhookEventDTO {
    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("type")
    private @Nullable String type;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("status")
    private @Nullable WebhookEventStatus status;

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
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the event type.
     *
     * @return The event type.
     */
    public @Nullable String getType() {
        return type;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the delivery status.
     *
     * @return The delivery status.
     */
    public @Nullable WebhookEventStatus getStatus() {
        return status;
    }
}
