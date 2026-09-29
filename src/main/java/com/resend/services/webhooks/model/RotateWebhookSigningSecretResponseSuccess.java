package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response from rotating a webhook signing secret.
 */
public class RotateWebhookSigningSecretResponseSuccess {
    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("signing_secret")
    private @Nullable String signingSecret;

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
    public @Nullable String getObject() {
        return object;
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
     * Gets the new signing secret for webhook verification.
     *
     * @return The signing secret.
     */
    public @Nullable String getSigningSecret() {
        return signingSecret;
    }
}
