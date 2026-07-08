package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Suppression details included in the {@code email.suppressed} webhook event.
 */
public class EmailSuppressedDetails {

    @JsonProperty("message")
    private String message;

    @JsonProperty("type")
    private String type;

    /**
     * Default constructor.
     */
    public EmailSuppressedDetails() {
    }

    /**
     * Gets the suppression message.
     *
     * @return The suppression message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the suppression message.
     *
     * @param message The suppression message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the suppression type.
     *
     * @return The suppression type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the suppression type.
     *
     * @param type The suppression type.
     */
    public void setType(String type) {
        this.type = type;
    }
}
