package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data payload for the {@code email.suppressed} webhook event.
 */
public class EmailSuppressedEventData extends BaseEmailEventData {

    @JsonProperty("suppressed")
    private EmailSuppressedDetails suppressed;

    /**
     * Default constructor.
     */
    public EmailSuppressedEventData() {
    }

    /**
     * Gets the suppression details.
     *
     * @return The suppression details.
     */
    public EmailSuppressedDetails getSuppressed() {
        return suppressed;
    }

    /**
     * Sets the suppression details.
     *
     * @param suppressed The suppression details.
     */
    public void setSuppressed(EmailSuppressedDetails suppressed) {
        this.suppressed = suppressed;
    }
}
