package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single clicked link row for a broadcast.
 */
public class BroadcastClickedLink {

    @JsonProperty("id")
    private String id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("clicks")
    private Integer clicks;

    @JsonProperty("unique_clicks")
    private Integer uniqueClicks;

    /**
     * Default constructor
     */
    public BroadcastClickedLink() {

    }

    /**
     * Constructs a new BroadcastClickedLink instance.
     *
     * @param id An opaque cursor for this row, used only for pagination. It does not identify any entity in Resend.
     * @param url The URL that was clicked.
     * @param clicks Total number of clicks on this URL.
     * @param uniqueClicks Number of unique clicks on this URL.
     */
    public BroadcastClickedLink(String id, String url, Integer clicks, Integer uniqueClicks) {
        this.id = id;
        this.url = url;
        this.clicks = clicks;
        this.uniqueClicks = uniqueClicks;
    }

    /**
     * Gets the opaque pagination cursor for this row.
     *
     * @return the pagination cursor
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the URL that was clicked.
     *
     * @return the clicked URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the total number of clicks on this URL.
     *
     * @return the total click count
     */
    public Integer getClicks() {
        return clicks;
    }

    /**
     * Gets the number of unique clicks on this URL.
     *
     * @return the unique click count
     */
    public Integer getUniqueClicks() {
        return uniqueClicks;
    }
}
