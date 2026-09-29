package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful webhook creation response.
 */
public class CreateWebhookResponseSuccess {

    /**
     * Creates a new CreateWebhookResponseSuccess instance.
     */
    public CreateWebhookResponseSuccess() {
    }

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

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
