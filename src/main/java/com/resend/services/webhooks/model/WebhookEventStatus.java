package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WebhookEventStatus {
    PENDING("pending"),
    ATTEMPTING("attempting"),
    SUCCESS("success"),
    FAILED("failed");

    private final String value;

    WebhookEventStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

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
