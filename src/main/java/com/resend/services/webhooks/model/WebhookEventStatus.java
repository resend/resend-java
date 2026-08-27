package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the delivery status of a webhook event.
 */
public enum WebhookEventStatus {
    /** The event is waiting for a delivery attempt. */
    PENDING("pending"),
    /** The event is currently being delivered. */
    ATTEMPTING("attempting"),
    /** The event was delivered successfully. */
    SUCCESS("success"),
    /** The event could not be delivered. */
    FAILED("failed");

    private final String value;

    WebhookEventStatus(String value) {
        this.value = value;
    }

    /**
     * Gets the API representation of the status.
     *
     * @return The status value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Converts an API status value to a WebhookEventStatus.
     *
     * @param value The API status value.
     * @return The matching webhook event status.
     * @throws IllegalArgumentException If the value is not a known status.
     */
    @JsonCreator
    public static WebhookEventStatus fromValue(String value) {
        for (WebhookEventStatus status : WebhookEventStatus.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
