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
     */
    public ListContactPropertiesResponseSuccess(final List<ContactProperty> data, final String object) {
        this.data = data;
        this.object = object;
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

}
