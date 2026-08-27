package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Represents a webhook event.
 */
public class GetWebhookEventResponseSuccess {
    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("status")
    private WebhookEventStatus status;

    @JsonProperty("next_attempt_at")
    private String nextAttemptAt;

    @JsonProperty("payload")
    private Map<String, Object> payload;

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
    public String getObject() {
        return object;
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

    /**
     * Gets the timestamp when the next delivery attempt is scheduled.
     *
     * @return The next attempt timestamp, or null when no attempt is scheduled.
     */
    public String getNextAttemptAt() {
        return nextAttemptAt;
    }

    /**
     * Gets the event payload sent to the webhook endpoint.
     *
     * @return The event payload.
     */
    public Map<String, Object> getPayload() {
        return payload;
    }
}
