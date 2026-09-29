package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a successful response for listing audiences.
 */
public class ListAudiencesResponseSuccess {

    @JsonProperty("data")
    private @Nullable List<Audience> data;

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    /**
     * Default constructor
     */
    public ListAudiencesResponseSuccess() {
    }

    /**
     * Constructs a successful response for listing audiences.
     *
     * @param data   The list of audiences.
     * @param object The object of the audiences.
     * @param hasMore Indicate if there are more items to be returned.
     */
    public ListAudiencesResponseSuccess(@Nullable List<Audience> data, @Nullable String object, @Nullable Boolean hasMore) {
        this.data = data;
        this.object = object;
        this.hasMore = hasMore;
    }

    /**
     * Get the list of audiences.
     *
     * @return The list of audiences.
     */
    public @Nullable List<Audience> getData() {
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