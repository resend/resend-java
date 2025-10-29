package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the status of a webhook.
 */
public enum WebhookStatus {
    ENABLED("enabled"),
    DISABLED("disabled");

    private final String value;

    WebhookStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
