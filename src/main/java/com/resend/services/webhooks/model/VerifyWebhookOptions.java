package com.resend.services.webhooks.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the options for verifying a webhook signature.
 */
public class VerifyWebhookOptions {

    private final String payload;
    private final Map<String, String> headers;
    private final String secret;

    private VerifyWebhookOptions(Builder builder) {
        this.payload = builder.payload;
        this.headers = new HashMap<String, String>(builder.headers);
        this.secret = builder.secret;
    }

    /**
     * Gets the raw webhook payload (request body).
     *
     * @return The payload string.
     */
    public String getPayload() {
        return payload;
    }

    /**
     * Gets the webhook headers containing signature information.
     *
     * @return A map of header names to values.
     */
    public Map<String, String> getHeaders() {
        return new HashMap<String, String>(headers);
    }

    /**
     * Gets the webhook signing secret.
     *
     * @return The secret string (including whsec_ prefix).
     */
    public String getSecret() {
        return secret;
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
     * Builder class for VerifyWebhookOptions.
     */
    public static class Builder {
        private String payload;
        private Map<String, String> headers = new HashMap<String, String>();
        private String secret;

        /**
         * Sets the raw webhook payload (request body).
         *
         * @param payload The payload string.
         * @return This builder instance.
         */
        public Builder payload(String payload) {
            this.payload = payload;
            return this;
        }

        /**
         * Adds a single header to the webhook headers.
         *
         * @param name The header name.
         * @param value The header value.
         * @return This builder instance.
         */
        public Builder addHeader(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        /**
         * Adds multiple headers to the webhook headers.
         *
         * @param headers A map of header names to values.
         * @return This builder instance.
         */
        public Builder addHeaders(Map<String, String> headers) {
            if (headers != null) {
                this.headers.putAll(headers);
            }
            return this;
        }

        /**
         * Sets the webhook signing secret.
         *
         * @param secret The secret string (including whsec_ prefix).
         * @return This builder instance.
         */
        public Builder secret(String secret) {
            this.secret = secret;
            return this;
        }

        /**
         * Builds the VerifyWebhookOptions instance.
         *
         * @return A new VerifyWebhookOptions instance.
         */
        public VerifyWebhookOptions build() {
            return new VerifyWebhookOptions(this);
        }
    }
}
