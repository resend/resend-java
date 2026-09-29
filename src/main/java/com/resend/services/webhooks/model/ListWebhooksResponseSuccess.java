package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.dto.WebhookDTO;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * Represents a response object for listing webhooks.
 */
public class ListWebhooksResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    @JsonProperty("data")
    private @Nullable List<WebhookDTO> data;

    /**
     * Default constructor.
     */
    public ListWebhooksResponseSuccess() {
    }

    /**
     * Constructor with all fields.
     *
     * @param object The object type (should be "list").
     * @param hasMore Indicates if there are more items to be returned.
     * @param data The list of webhook data.
     */
    public ListWebhooksResponseSuccess(@Nullable String object, @Nullable Boolean hasMore, @Nullable List<WebhookDTO> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
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
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(@Nullable String object) {
        this.object = object;
    }

    /**
     * Gets whether there are more items available for pagination.
     *
     * @return Whether there are more items available.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }

    /**
     * Sets whether there are more items available.
     *
     * @param hasMore Whether there are more items available.
     */
    public void setHasMore(@Nullable Boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * Gets the list of webhook data.
     *
     * @return The list of webhook data.
     */
    public @Nullable List<WebhookDTO> getData() {
        return data;
    }

    /**
     * Sets the list of webhook data.
     *
     * @param data The list of webhook data.
     */
    public void setData(@Nullable List<WebhookDTO> data) {
        this.data = data;
    }
}
