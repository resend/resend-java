package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response from rotating a webhook signing secret.
 */
public class RotateWebhookSigningSecretResponseSuccess {
    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("signing_secret")
    private String signingSecret;

    /**
     * Constructs an empty webhook signing secret rotation response.
     */
    public RotateWebhookSigningSecretResponseSuccess() {
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
     * Gets the webhook ID.
     *
     * @return The webhook ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the new signing secret for webhook verification.
     *
     * @return The signing secret.
     */
    public String getSigningSecret() {
        return signingSecret;
    }
}
