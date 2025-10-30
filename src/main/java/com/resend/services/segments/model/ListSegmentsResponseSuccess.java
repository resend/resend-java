package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing segments.
 */
public class ListSegmentsResponseSuccess {

    @JsonProperty("data")
    private List<Segment> data;

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    /**
     * Default constructor
     */
    public ListSegmentsResponseSuccess() {
    }

    /**
     * Constructs a successful response for listing segments.
     *
     * @param data   The list of segments.
     * @param object The object of the segments.
     * @param hasMore Indicate if there are more items to be returned.
     */
    public ListSegmentsResponseSuccess(List<Segment> data, String object, Boolean hasMore) {
        this.data = data;
        this.object = object;
        this.hasMore = hasMore;
    }

    /**
     * Get the list of segments.
     *
     * @return The list of segments.
     */
    public List<Segment> getData() {
        return data;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public String getObject() {
        return object;
    }

    /**
     * Gets the indicator whether there are more items available for pagination.
     *
     * @return Whether there are more items available for pagination.
     */
    public Boolean hasMore() {
        return hasMore;
    }
}
