package com.resend.services.contactproperties.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * Represents a successful response for listing contact properties.
 */
public class ListContactPropertiesResponseSuccess {

    @JsonProperty("data")
    private @Nullable List<ContactProperty> data;

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    /**
     * Default constructor
     */
    public ListContactPropertiesResponseSuccess() {
    }

    /**
     * Constructs a successful response for listing contact properties.
     *
     * @param data      The list of contact properties.
     * @param object    The object type.
     * @param hasMore   Indicate if there are more items to be returned.
     */
    public ListContactPropertiesResponseSuccess(final @Nullable List<ContactProperty> data, final @Nullable String object, final @Nullable Boolean hasMore) {
        this.data = data;
        this.object = object;
        this.hasMore = hasMore;
    }

    /**
     * Gets the list of contact properties.
     *
     * @return The list of contact properties.
     */
    public @Nullable List<ContactProperty> getData() {
        return data;
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
     * Gets the indicator whether there are more items available for pagination.
     *
     * @return Whether there are more items available for pagination.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }

}
