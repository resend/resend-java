package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data payload for the {@code email.bounced} webhook event.
 */
public class EmailBouncedEventData extends BaseEmailEventData {

    @JsonProperty("bounce")
    private EmailBounce bounce;

    /**
     * Default constructor.
     */
    public EmailBouncedEventData() {
    }

    /**
     * Gets the bounce details.
     *
     * @return The bounce details.
     */
    public EmailBounce getBounce() {
        return bounce;
    }

    /**
     * Sets the bounce details.
     *
     * @param bounce The bounce details.
     */
    public void setBounce(EmailBounce bounce) {
        this.bounce = bounce;
    }
}
