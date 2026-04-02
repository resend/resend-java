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

    public String getObject() { return object; }
    public Boolean hasMore() { return hasMore; }
    public List<LogEntry> getData() { return data; }
}
