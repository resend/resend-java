package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.dto.WebhookEventDTO;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * Represents a successful response from listing webhook events.
 */
public class ListWebhookEventsResponseSuccess {
    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    @JsonProperty("data")
    private @Nullable List<WebhookEventDTO> data;

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
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Indicates whether more events are available for pagination.
     *
     * @return True if more events are available, false if not, or {@code null} if the API omitted the field.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }

    /**
     * Gets the webhook events.
     *
     * @return The list of webhook events.
     */
    public @Nullable List<WebhookEventDTO> getData() {
        return data;
    }
}
