package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import java.util.Map;

/**
 * Represents a webhook event.
 */
public class GetWebhookEventResponseSuccess {
    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("type")
    private @Nullable String type;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("status")
    private @Nullable WebhookEventStatus status;

    @JsonProperty("next_attempt_at")
    private @Nullable String nextAttemptAt;

    @JsonProperty("payload")
    private @Nullable Map<String, @Nullable Object> payload;

    /**
     * Constructs an empty webhook event response.
     */
    public GetWebhookEventResponseSuccess() {
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
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

    /**
     * Gets the timestamp when the next delivery attempt is scheduled.
     *
     * @return The next attempt timestamp, or null when no attempt is scheduled.
     */
    public @Nullable String getNextAttemptAt() {
        return nextAttemptAt;
    }

    /**
     * Gets the event payload sent to the webhook endpoint.
     *
     * @return The event payload.
     */
    public @Nullable Map<String, @Nullable Object> getPayload() {
        return payload;
    }
}
