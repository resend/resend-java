package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.dto.WebhookEventAttemptDTO;
import java.util.List;

/**
 * Represents a successful response from listing webhook event attempts.
 */
public class ListWebhookEventAttemptsResponseSuccess {
    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<WebhookEventAttemptDTO> data;

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
    public String getObject() {
        return object;
    }

    /**
     * Indicates whether more attempts are available for pagination.
     *
     * @return True if more attempts are available, false otherwise.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Gets the webhook event attempts.
     *
     * @return The list of delivery attempts.
     */
    public List<WebhookEventAttemptDTO> getData() {
        return data;
    }
}
