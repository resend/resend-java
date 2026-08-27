package com.resend.services.webhooks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object for a webhook event delivery attempt.
 */
public class WebhookEventAttemptDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("http_status_code")
    private Integer httpStatusCode;

    @JsonProperty("response")
    private String response;

    @JsonProperty("sent_at")
    private String sentAt;

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
    public String getId() {
        return id;
    }

    /**
     * Gets the HTTP status code returned by the webhook endpoint.
     *
     * @return The HTTP status code.
     */
    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * Gets the response body returned by the webhook endpoint.
     *
     * @return The response body.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Gets the timestamp when the attempt was sent.
     *
     * @return The sent timestamp.
     */
    public String getSentAt() {
        return sentAt;
    }
}
