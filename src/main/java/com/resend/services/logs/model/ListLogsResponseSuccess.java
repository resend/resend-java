package com.resend.services.logs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing logs.
 */
public class ListLogsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<LogEntry> data;

    /**
     * Default constructor.
     */
    public ListLogsResponseSuccess() {
    }

    /**
     * Constructs a ListLogsResponseSuccess.
     *
     * @param object  The object type ("list").
     * @param hasMore Whether there are more items available for pagination.
     * @param data    The list of log entries.
     */
    public ListLogsResponseSuccess(String object, Boolean hasMore, List<LogEntry> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    /**
     * Gets the object type.
     *
     * @return the object type ("list")
     */
    public String getObject() { return object; }

    /**
     * Checks if there are more items available for pagination.
     *
     * @return true if more items are available, false otherwise
     */
    public Boolean hasMore() { return hasMore; }

    /**
     * Gets the list of log entries.
     *
     * @return the list of log entries
     */
    public List<LogEntry> getData() { return data; }
}
