package com.resend.services.contactproperties.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base contact property with common properties
 */
public abstract class BaseContactProperty {
    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor
     */
    public BaseContactProperty() {
    }

    /**
     * Constructs a base contact property.
     *
     * @param id        The ID of the contact property.
     * @param object    The object type of the contact property.
     */
    public BaseContactProperty(final String id, final String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Gets the ID of the contact property.
     *
     * @return The ID of the contact property.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the object type of the contact property.
     *
     * @return The object type of the contact property.
     */
    public String getObject() {
        return object;
    }

}
