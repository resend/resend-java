package com.resend.services.webhooks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Data Transfer Object for a webhook event delivery attempt.
 */
public class WebhookEventAttemptDTO {
    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("http_status_code")
    private @Nullable Integer httpStatusCode;

    @JsonProperty("response")
    private @Nullable String response;

    @JsonProperty("sent_at")
    private @Nullable String sentAt;

    /**
     * Constructs an empty webhook event attempt.
     */
    public WebhookEventAttemptDTO() {
    }

    /**
     * Gets the attempt ID.
     *
     * @return The attempt ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the HTTP status code returned by the webhook endpoint.
     *
     * @return The HTTP status code.
     */
    public @Nullable Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * Gets the response body returned by the webhook endpoint.
     *
     * @return The response body.
     */
    public @Nullable String getResponse() {
        return response;
    }

    /**
     * Gets the timestamp when the attempt was sent.
     *
     * @return The sent timestamp.
     */
    public @Nullable String getSentAt() {
        return sentAt;
    }
}
