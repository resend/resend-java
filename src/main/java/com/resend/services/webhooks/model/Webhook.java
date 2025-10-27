package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a webhook.
 */
public class Webhook {

    @JsonProperty("object")
    private String object;

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

    @JsonProperty("signing_secret")
    private String signingSecret;

    /**
     * Gets the object type (should be "webhook").
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

    /**
     * Gets the signing secret for webhook verification.
     *
     * @return The signing secret.
     */
    public String getSigningSecret() {
        return signingSecret;
    }

    /**
     * Sets the signing secret.
     *
     * @param signingSecret The signing secret.
     */
    public void setSigningSecret(String signingSecret) {
        this.signingSecret = signingSecret;
    }
}
