package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.dto.WebhookEventAttemptDTO;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * Represents a successful response from listing webhook event attempts.
 */
public class ListWebhookEventAttemptsResponseSuccess {
    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    @JsonProperty("data")
    private @Nullable List<WebhookEventAttemptDTO> data;

    /**
     * Constructs an empty webhook event attempt list response.
     */
    public ListWebhookEventAttemptsResponseSuccess() {
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
     * Indicates whether more attempts are available for pagination.
     *
     * @return True if more attempts are available, false otherwise.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }

    /**
     * Gets the webhook event attempts.
     *
     * @return The list of delivery attempts.
     */
    public @Nullable List<WebhookEventAttemptDTO> getData() {
        return data;
    }
}
