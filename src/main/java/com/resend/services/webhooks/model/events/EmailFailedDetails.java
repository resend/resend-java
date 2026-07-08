package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Failure details included in the {@code email.failed} webhook event.
 */
public class EmailFailedDetails {

    @JsonProperty("reason")
    private String reason;

    /**
     * Default constructor.
     */
    public EmailFailedDetails() {
    }

    /**
     * Gets the failure reason.
     *
     * @return The failure reason.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the failure reason.
     *
     * @param reason The failure reason.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
