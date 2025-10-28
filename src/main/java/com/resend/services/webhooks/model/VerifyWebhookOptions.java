package com.resend.services.webhooks.model;

/**
 * Represents the options for verifying a webhook signature.
 */
public class VerifyWebhookOptions {

    private final String payload;
    private final WebhookHeaders headers;
    private final String secret;

    private VerifyWebhookOptions(Builder builder) {
        this.payload = builder.payload;
        this.headers = builder.headers;
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
     * @return The WebhookHeaders object.
     */
    public WebhookHeaders getHeaders() {
        return headers;
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
        private WebhookHeaders headers;
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
         * Sets the webhook headers containing signature information.
         *
         * @param headers The WebhookHeaders object.
         * @return This builder instance.
         */
        public Builder headers(WebhookHeaders headers) {
            this.headers = headers;
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
