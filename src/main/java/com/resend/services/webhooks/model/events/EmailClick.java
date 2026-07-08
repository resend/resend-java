package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Click details included in the {@code email.clicked} webhook event.
 */
public class EmailClick {

    @JsonProperty("ipAddress")
    private String ipAddress;

    @JsonProperty("link")
    private String link;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("userAgent")
    private String userAgent;

    /**
     * Default constructor.
     */
    public EmailClick() {
    }

    /**
     * Gets the IP address of the click.
     *
     * @return The IP address.
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the IP address of the click.
     *
     * @param ipAddress The IP address.
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Gets the clicked link URL.
     *
     * @return The link URL.
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the clicked link URL.
     *
     * @param link The link URL.
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Gets the click timestamp.
     *
     * @return The click timestamp.
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the click timestamp.
     *
     * @param timestamp The click timestamp.
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the user agent of the click.
     *
     * @return The user agent.
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the user agent of the click.
     *
     * @param userAgent The user agent.
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
