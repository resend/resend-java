package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a link clicked by a broadcast recipient.
 */
public class BroadcastRecipientClickedLink {

    @JsonProperty("url")
    private String url;

    @JsonProperty("clicks")
    private Integer clicks;

    /**
     * Default constructor
     */
    public BroadcastRecipientClickedLink() {

    }

    /**
     * Constructs a new BroadcastRecipientClickedLink instance.
     *
     * @param url The clicked URL.
     * @param clicks The number of times this recipient clicked this URL.
     */
    public BroadcastRecipientClickedLink(String url, Integer clicks) {
        this.url = url;
        this.clicks = clicks;
    }

    /**
     * Gets the clicked URL.
     *
     * @return the clicked URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the number of times this recipient clicked this URL.
     *
     * @return the number of clicks
     */
    public Integer getClicks() {
        return clicks;
    }
}
