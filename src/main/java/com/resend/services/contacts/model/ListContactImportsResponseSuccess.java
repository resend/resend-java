package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a successful response for listing contact imports.
 */
public class ListContactImportsResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    @JsonProperty("data")
    private @Nullable List<ContactImport> data;

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
    public ListContactImportsResponseSuccess(final @Nullable String object, final @Nullable Boolean hasMore, final @Nullable List<ContactImport> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
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
     * Indicates whether there are more items available for pagination.
     *
     * @return {@code true} if more items are available, {@code false} if not, or {@code null} if the API omitted the field.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }

    /**
     * Gets the list of contact imports.
     *
     * @return The list of contact imports.
     */
    public @Nullable List<ContactImport> getData() {
        return data;
    }
}
