package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.dto.WebhookEventAttemptDTO;
import java.util.List;

public class ListWebhookEventAttemptsResponseSuccess {
    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<WebhookEventAttemptDTO> data;

    public String getObject() {
        return object;
    }

    public Boolean hasMore() {
        return hasMore;
    }

    public List<WebhookEventAttemptDTO> getData() {
        return data;
    }
}
