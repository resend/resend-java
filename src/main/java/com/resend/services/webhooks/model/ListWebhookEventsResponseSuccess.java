package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.dto.WebhookEventDTO;
import java.util.List;

public class ListWebhookEventsResponseSuccess {
    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<WebhookEventDTO> data;

    public String getObject() {
        return object;
    }

    public Boolean hasMore() {
        return hasMore;
    }

    public List<WebhookEventDTO> getData() {
        return data;
    }
}
