package com.resend.services.webhooks.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the headers required for webhook signature verification.
 * These headers are sent by Resend/Svix with each webhook request.
 */
public class WebhookHeaders {

    private final Map<String, String> headers;

    private WebhookHeaders(Builder builder) {
        this.headers = new HashMap<String, String>(builder.headers);
    }

    /**
     * Gets a header value by name.
     *
     * @param name The header name.
     * @return The header value, or null if not found.
     */
    public String get(String name) {
        return headers.get(name);
    }

    /**
     * Gets all headers.
     *
     * @return An unmodifiable map of all headers.
     */
    public Map<String, String> getAll() {
        return new HashMap<String, String>(headers);
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
     * Builder class for WebhookHeaders.
     */
    public static class Builder {
        private Map<String, String> headers = new HashMap<String, String>();

        /**
         * Adds a header to the collection.
         *
         * @param name The header name.
         * @param value The header value.
         * @return This builder instance.
         */
        public Builder add(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        /**
         * Builds the WebhookHeaders instance.
         *
         * @return A new WebhookHeaders instance.
         */
        public WebhookHeaders build() {
            return new WebhookHeaders(this);
        }
    }
}
