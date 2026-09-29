package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents the successful response from listing segments a contact belongs to.
 */
public class ListContactSegmentsResponseSuccess {

    /**
     * The object type.
     */
    @JsonProperty("object")
    private @Nullable String object;

    /**
     * The list of segments.
     */
    @JsonProperty("data")
    private @Nullable List<ContactSegment> data;

    /**
     * Indicates whether there are more segments to retrieve.
     */
    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

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
    public ListContactSegmentsResponseSuccess(@Nullable String object, @Nullable List<ContactSegment> data, @Nullable Boolean hasMore) {
        this.object = object;
        this.data = data;
        this.hasMore = hasMore;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(@Nullable String object) {
        this.object = object;
    }

    /**
     * Gets the list of segments.
     *
     * @return The list of segments.
     */
    public @Nullable List<ContactSegment> getData() {
        return data;
    }

    /**
     * Sets the list of segments.
     *
     * @param data The list of segments.
     */
    public void setData(@Nullable List<ContactSegment> data) {
        this.data = data;
    }

    /**
     * Gets whether there are more segments to retrieve.
     *
     * @return True if there are more segments, false otherwise.
     */
    public @Nullable Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Sets whether there are more segments to retrieve.
     *
     * @param hasMore True if there are more segments, false otherwise.
     */
    public void setHasMore(@Nullable Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
