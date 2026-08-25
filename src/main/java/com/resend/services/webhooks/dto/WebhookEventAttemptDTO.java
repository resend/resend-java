package com.resend.services.webhooks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookEventAttemptDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("http_status_code")
    private Integer httpStatusCode;

    @JsonProperty("response")
    private String response;

    @JsonProperty("sent_at")
    private String sentAt;

    public String getId() {
        return id;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getResponse() {
        return response;
    }

    public String getSentAt() {
        return sentAt;
    }
}
