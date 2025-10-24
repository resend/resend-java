package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful webhook update response.
 */
public class UpdateWebhookResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

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
}
