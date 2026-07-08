package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data payload for the {@code email.clicked} webhook event.
 */
public class EmailClickedEventData extends BaseEmailEventData {

    @JsonProperty("click")
    private EmailClick click;

    /**
     * Default constructor.
     */
    public EmailClickedEventData() {
    }

    /**
     * Gets the click details.
     *
     * @return The click details.
     */
    public EmailClick getClick() {
        return click;
    }

    /**
     * Sets the click details.
     *
     * @param click The click details.
     */
    public void setClick(EmailClick click) {
        this.click = click;
    }
}
