package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * Represents a webhook.
 */
public class GetWebhookResponseSuccess {

    /**
     * Creates a new GetWebhookResponseSuccess instance.
     */
    public GetWebhookResponseSuccess() {
    }

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("status")
    private @Nullable WebhookStatus status;

    @JsonProperty("endpoint")
    private @Nullable String endpoint;

    @JsonProperty("events")
    private @Nullable List<String> events;

    @JsonProperty("signing_secret")
    private @Nullable String signingSecret;

    /**
     * Gets the object type (should be "webhook").
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
     * Gets the webhook ID.
     *
     * @return The webhook ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the webhook ID.
     *
     * @param id The webhook ID.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(@Nullable String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the webhook status.
     *
     * @return The webhook status.
     */
    public @Nullable WebhookStatus getStatus() {
        return status;
    }

    /**
     * Sets the webhook status.
     *
     * @param status The webhook status.
     */
    public void setStatus(@Nullable WebhookStatus status) {
        this.status = status;
    }

    /**
     * Gets the webhook endpoint URL.
     *
     * @return The endpoint URL.
     */
    public @Nullable String getEndpoint() {
        return endpoint;
    }

    /**
     * Sets the webhook endpoint URL.
     *
     * @param endpoint The endpoint URL.
     */
    public void setEndpoint(@Nullable String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Gets the list of events that trigger the webhook.
     *
     * @return The list of event names.
     */
    public @Nullable List<String> getEvents() {
        return events;
    }

    /**
     * Sets the list of events that trigger the webhook.
     *
     * @param events The list of event names.
     */
    public void setEvents(@Nullable List<String> events) {
        this.events = events;
    }

    /**
     * Gets the signing secret for webhook verification.
     *
     * @return The signing secret.
     */
    public @Nullable String getSigningSecret() {
        return signingSecret;
    }

    /**
     * Sets the signing secret.
     *
     * @param signingSecret The signing secret.
     */
    public void setSigningSecret(@Nullable String signingSecret) {
        this.signingSecret = signingSecret;
    }
}
