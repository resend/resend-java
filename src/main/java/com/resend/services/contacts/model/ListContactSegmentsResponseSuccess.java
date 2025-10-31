package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents the successful response from listing segments a contact belongs to.
 */
public class ListContactSegmentsResponseSuccess {

    /**
     * The object type.
     */
    @JsonProperty("object")
    private String object;

    /**
     * The list of segments.
     */
    @JsonProperty("data")
    private List<ContactSegment> data;

    /**
     * Indicates whether there are more segments to retrieve.
     */
    @JsonProperty("has_more")
    private Boolean hasMore;

    /**
     * Default constructor.
     */
    public ListContactSegmentsResponseSuccess() {
    }

    /**
     * Constructor with all fields.
     *
     * @param object  The object type.
     * @param data    The list of segments.
     * @param hasMore Whether there are more segments to retrieve.
     */
    public ListContactSegmentsResponseSuccess(String object, List<ContactSegment> data, Boolean hasMore) {
        this.object = object;
        this.data = data;
        this.hasMore = hasMore;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Gets the list of segments.
     *
     * @return The list of segments.
     */
    public List<ContactSegment> getData() {
        return data;
    }

    /**
     * Sets the list of segments.
     *
     * @param data The list of segments.
     */
    public void setData(List<ContactSegment> data) {
        this.data = data;
    }

    /**
     * Gets whether there are more segments to retrieve.
     *
     * @return True if there are more segments, false otherwise.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Sets whether there are more segments to retrieve.
     *
     * @param hasMore True if there are more segments, false otherwise.
     */
    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
