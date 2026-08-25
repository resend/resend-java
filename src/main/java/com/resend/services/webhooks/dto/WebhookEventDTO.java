package com.resend.services.webhooks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.model.WebhookEventStatus;

public class WebhookEventDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("status")
    private WebhookEventStatus status;

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
}
