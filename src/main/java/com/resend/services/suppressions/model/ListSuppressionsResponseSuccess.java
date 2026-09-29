package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a successful response for listing suppressions.
 */
public class ListSuppressionsResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    @JsonProperty("data")
    private @Nullable List<Suppression> data;

    /**
     * Default constructor
     */
    public ListSuppressionsResponseSuccess() {

    }

    /**
     * Constructs a successful response for listing suppressions.
     *
     * @param object  The object type of the list.
     * @param hasMore Whether there are more suppressions available for pagination.
     * @param data    The list of suppressions.
     */
    public ListSuppressionsResponseSuccess(@Nullable String object, @Nullable Boolean hasMore, @Nullable List<Suppression> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    /**
     * Get the object type.
     *
     * @return The object type of the list.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Get the indicator whether there are more suppressions available for pagination.
     *
     * @return Whether there are more suppressions available for pagination.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }

    /**
     * Get the list of suppressions.
     *
     * @return The list of suppressions.
     */
    public @Nullable List<Suppression> getData() {
        return data;
    }
}
