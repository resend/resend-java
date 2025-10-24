package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.dto.WebhookDTO;
import java.util.List;

/**
 * Represents a response object for listing webhooks.
 */
public class ListWebhooksResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<WebhookDTO> data;

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
    public ListWebhooksResponseSuccess(String object, Boolean hasMore, List<WebhookDTO> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
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
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Gets whether there are more items available for pagination.
     *
     * @return Whether there are more items available.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Sets whether there are more items available.
     *
     * @param hasMore Whether there are more items available.
     */
    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * Gets the list of webhook data.
     *
     * @return The list of webhook data.
     */
    public List<WebhookDTO> getData() {
        return data;
    }

    /**
     * Sets the list of webhook data.
     *
     * @param data The list of webhook data.
     */
    public void setData(List<WebhookDTO> data) {
        this.data = data;
    }
}
