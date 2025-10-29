package com.resend.services.webhooks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.webhooks.model.WebhookStatus;
import java.util.List;

/**
 * Data Transfer Object for webhook data in list responses.
 */
public class WebhookDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("status")
    private WebhookStatus status;

    @JsonProperty("endpoint")
    private String endpoint;

    @JsonProperty("events")
    private List<String> events;

    /**
     * Default constructor.
     */
    public WebhookDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id The webhook ID.
     * @param createdAt The creation timestamp.
     * @param status The webhook status.
     * @param endpoint The webhook endpoint URL.
     * @param events The list of event names.
     */
    public WebhookDTO(String id, String createdAt, WebhookStatus status, String endpoint, List<String> events) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.endpoint = endpoint;
        this.events = events;
    }

    /**
     * Gets the webhook ID.
     *
     * @return The webhook ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the webhook ID.
     *
     * @param id The webhook ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the webhook status.
     *
     * @return The webhook status.
     */
    public WebhookStatus getStatus() {
        return status;
    }

    /**
     * Sets the webhook status.
     *
     * @param status The webhook status.
     */
    public void setStatus(WebhookStatus status) {
        this.status = status;
    }

    /**
     * Gets the webhook endpoint URL.
     *
     * @return The endpoint URL.
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Sets the webhook endpoint URL.
     *
     * @param endpoint The endpoint URL.
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Gets the list of events that trigger the webhook.
     *
     * @return The list of event names.
     */
    public List<String> getEvents() {
        return events;
    }

    /**
     * Sets the list of events that trigger the webhook.
     *
     * @param events The list of event names.
     */
    public void setEvents(List<String> events) {
        this.events = events;
    }
}
