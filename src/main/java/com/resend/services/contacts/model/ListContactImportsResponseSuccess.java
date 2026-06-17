package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing contact imports.
 */
public class ListContactImportsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<ContactImport> data;

    /**
     * Default constructor.
     */
    public ListContactImportsResponseSuccess() {
    }

    /**
     * Constructs a ListContactImportsResponseSuccess with the provided values.
     *
     * @param object  The object type (e.g. {@code "list"}).
     * @param hasMore Whether there are more items available for pagination.
     * @param data    The list of contact imports.
     */
    public ListContactImportsResponseSuccess(final String object, final Boolean hasMore, final List<ContactImport> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
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
     * Indicates whether there are more items available for pagination.
     *
     * @return {@code true} if more items are available, otherwise {@code false}.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Gets the list of contact imports.
     *
     * @return The list of contact imports.
     */
    public List<ContactImport> getData() {
        return data;
    }
}
