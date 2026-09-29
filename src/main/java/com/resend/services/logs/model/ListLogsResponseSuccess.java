package com.resend.services.logs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a successful response for listing logs.
 */
public class ListLogsResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    @JsonProperty("data")
    private @Nullable List<LogEntry> data;

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
    public ListLogsResponseSuccess(@Nullable String object, @Nullable Boolean hasMore, @Nullable List<LogEntry> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    /**
     * Gets the object type.
     *
     * @return the object type ("list")
     */
    public @Nullable String getObject() { return object; }

    /**
     * Checks if there are more items available for pagination.
     *
     * @return true if more items are available, false otherwise
     */
    public @Nullable Boolean hasMore() { return hasMore; }

    /**
     * Gets the list of log entries.
     *
     * @return the list of log entries
     */
    public @Nullable List<LogEntry> getData() { return data; }
}
