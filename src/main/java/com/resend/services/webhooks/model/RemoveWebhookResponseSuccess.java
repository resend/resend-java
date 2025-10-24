package com.resend.services.webhooks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful webhook removal response.
 */
public class RemoveWebhookResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private Boolean deleted;

    /**
     * Default constructor.
     */
    public RemoveWebhookResponseSuccess() {
    }

    /**
     * Constructor with all fields.
     *
     * @param object The object type (should be "webhook").
     * @param id The webhook ID.
     * @param deleted Whether the webhook was deleted.
     */
    public RemoveWebhookResponseSuccess(String object, String id, Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
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
     * Gets whether the webhook was deleted.
     *
     * @return Whether the webhook was deleted.
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * Sets whether the webhook was deleted.
     *
     * @param deleted Whether the webhook was deleted.
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
