package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a successful response for listing segments.
 */
public class ListSegmentsResponseSuccess {

    @JsonProperty("data")
    private @Nullable List<Segment> data;

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

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
    public ListSegmentsResponseSuccess(@Nullable List<Segment> data, @Nullable String object, @Nullable Boolean hasMore) {
        this.data = data;
        this.object = object;
        this.hasMore = hasMore;
    }

    /**
     * Get the list of segments.
     *
     * @return The list of segments.
     */
    public @Nullable List<Segment> getData() {
        return data;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Gets the indicator whether there are more items available for pagination.
     *
     * @return Whether there are more items available for pagination.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }
}
