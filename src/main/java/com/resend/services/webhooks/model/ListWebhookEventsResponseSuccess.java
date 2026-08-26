package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.dto.WebhookEventDTO;
import java.util.List;

/**
 * Represents a successful response from listing webhook events.
 */
public class ListWebhookEventsResponseSuccess {
    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<WebhookEventDTO> data;

    /**
     * Constructs an empty webhook event list response.
     */
    public ListWebhookEventsResponseSuccess() {
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Indicates whether more events are available for pagination.
     *
     * @return True if more events are available, false otherwise.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Gets the webhook events.
     *
     * @return The list of webhook events.
     */
    public List<WebhookEventDTO> getData() {
        return data;
    }
}
