package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

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

    public String getObject() {
        return object;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public WebhookEventStatus getStatus() {
        return status;
    }

    public String getNextAttemptAt() {
        return nextAttemptAt;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }
}
