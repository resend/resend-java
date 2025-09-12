package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing audiences.
 */
public class ListAudiencesResponseSuccess {

    @JsonProperty("data")
    private List<Audience> data;

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

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
     */
    public ListAudiencesResponseSuccess(List<Audience> data, String object, Boolean hasMore) {
        this.data = data;
        this.object = object;
        this.hasMore = hasMore;
    }

    /**
     * Get the list of audiences.
     *
     * @return The list of audiences.
     */
    public List<Audience> getData() {
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