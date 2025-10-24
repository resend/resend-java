package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents the options for updating a webhook.
 */
public class UpdateWebhookOptions {

    @JsonProperty("endpoint")
    private final String endpoint;

    @JsonProperty("events")
    private final List<WebhookEvent> events;

    @JsonProperty("status")
    private final WebhookStatus status;

    private UpdateWebhookOptions(Builder builder) {
        this.endpoint = builder.endpoint;
        this.events = builder.events;
        this.status = builder.status;
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
     * Gets the list of events that will trigger the webhook.
     *
     * @return The list of webhook events.
     */
    public List<WebhookEvent> getEvents() {
        return events;
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
     * Creates a new builder instance.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for UpdateWebhookOptions.
     */
    public static class Builder {
        private String endpoint;
        private List<WebhookEvent> events;
        private WebhookStatus status;

        /**
         * Sets the webhook endpoint URL.
         *
         * @param endpoint The endpoint URL.
         * @return This builder instance.
         */
        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        /**
         * Sets the list of events that will trigger the webhook.
         *
         * @param events The list of webhook events.
         * @return This builder instance.
         */
        public Builder events(List<WebhookEvent> events) {
            this.events = events;
            return this;
        }

        /**
         * Sets the events that will trigger the webhook (varargs).
         *
         * @param events The webhook events.
         * @return This builder instance.
         */
        public Builder events(WebhookEvent... events) {
            this.events = java.util.Arrays.asList(events);
            return this;
        }

        /**
         * Sets the webhook status.
         *
         * @param status The webhook status.
         * @return This builder instance.
         */
        public Builder status(WebhookStatus status) {
            this.status = status;
            return this;
        }

        /**
         * Builds the UpdateWebhookOptions instance.
         *
         * @return A new UpdateWebhookOptions instance.
         */
        public UpdateWebhookOptions build() {
            return new UpdateWebhookOptions(this);
        }
    }
}
