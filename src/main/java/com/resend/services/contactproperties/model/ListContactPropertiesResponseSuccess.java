package com.resend.services.contactproperties.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a successful response for listing contact properties.
 */
public class ListContactPropertiesResponseSuccess {

    @JsonProperty("data")
    private List<ContactProperty> data;

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

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
    public ListContactPropertiesResponseSuccess(final List<ContactProperty> data, final String object, final Boolean hasMore) {
        this.data = data;
        this.object = object;
        this.hasMore = hasMore;
    }

    /**
     * Gets the list of contact properties.
     *
     * @return The list of contact properties.
     */
    public List<ContactProperty> getData() {
        return data;
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
     * Gets the indicator whether there are more items available for pagination.
     *
     * @return Whether there are more items available for pagination.
     */
    public Boolean hasMore() {
        return hasMore;
    }

}
