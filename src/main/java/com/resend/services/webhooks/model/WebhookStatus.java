package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the status of a webhook.
 */
public enum WebhookStatus {
    /** The webhook is enabled and active. */
    ENABLED("enabled"),
    /** The webhook is disabled and inactive. */
    DISABLED("disabled");

    private final String value;

    WebhookStatus(String value) {
        this.value = value;
    }

    /**
     * Gets the string value of the webhook status.
     *
     * @return the string value of this status
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
