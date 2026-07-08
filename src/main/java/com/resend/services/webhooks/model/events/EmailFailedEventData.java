package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data payload for the {@code email.failed} webhook event.
 */
public class EmailFailedEventData extends BaseEmailEventData {

    @JsonProperty("failed")
    private EmailFailedDetails failed;

    /**
     * Default constructor.
     */
    public EmailFailedEventData() {
    }

    /**
     * Gets the failure details.
     *
     * @return The failure details.
     */
    public EmailFailedDetails getFailed() {
        return failed;
    }

    /**
     * Sets the failure details.
     *
     * @param failed The failure details.
     */
    public void setFailed(EmailFailedDetails failed) {
        this.failed = failed;
    }
}
