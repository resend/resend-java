package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bounce details included in the {@code email.bounced} webhook event.
 */
public class EmailBounce {

    @JsonProperty("message")
    private String message;

    @JsonProperty("subType")
    private String subType;

    @JsonProperty("type")
    private String type;

    /**
     * Default constructor.
     */
    public EmailBounce() {
    }

    /**
     * Gets the bounce message.
     *
     * @return The bounce message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the bounce message.
     *
     * @param message The bounce message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the bounce sub-type.
     *
     * @return The bounce sub-type.
     */
    public String getSubType() {
        return subType;
    }

    /**
     * Sets the bounce sub-type.
     *
     * @param subType The bounce sub-type.
     */
    public void setSubType(String subType) {
        this.subType = subType;
    }

    /**
     * Gets the bounce type.
     *
     * @return The bounce type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the bounce type.
     *
     * @param type The bounce type.
     */
    public void setType(String type) {
        this.type = type;
    }
}
