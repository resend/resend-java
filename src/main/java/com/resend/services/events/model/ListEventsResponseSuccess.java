package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response from listing events.
 */
public class ListEventsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<EventSummary> data;

    public ListEventsResponseSuccess() {
    }

    public ListEventsResponseSuccess(String object, Boolean hasMore, List<EventSummary> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    /**
     * Retrieves the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Indicates if there are more events available for pagination.
     *
     * @return True if more events are available, false otherwise.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Retrieves the list of events.
     *
     * @return The list of event summaries.
     */
    public List<EventSummary> getData() {
        return data;
    }
}
