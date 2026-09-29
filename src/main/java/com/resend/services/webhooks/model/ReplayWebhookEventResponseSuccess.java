package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response from replaying a webhook event.
 */
public class ReplayWebhookEventResponseSuccess {
    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Constructs an empty webhook event replay response.
     */
    public ReplayWebhookEventResponseSuccess() {
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
}
