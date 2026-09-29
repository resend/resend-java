package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a successful response for listing contacts.
 */
public class ListContactsResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("data")
    private @Nullable List<Contact> data;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    /**
     * Default constructor
     */
    public ListContactsResponseSuccess() {
    }

    /**
     * Constructs a successful response for listing contacts.
     *
     * @param data The list of contacts.
     * @param object The object of the list contacts.
     */
    public ListContactsResponseSuccess(final @Nullable List<Contact> data, final @Nullable String object) {
        this.data = data;
        this.object = object;
        this.hasMore = hasMore;
    }

    /**
     * Gets the list of contacts.
     *
     * @return The list of contacts.
     */
    public @Nullable List<Contact> getData() {
        return data;
    }

    /**
     * Gets the list of contacts object.
     *
     * @return The list of contacts object.
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

