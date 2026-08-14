package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a custom property value on a contact, returned as an object
 * with the value and its type.
 */
public class ContactPropertyValue {

    @JsonProperty("value")
    private Object value;

    @JsonProperty("type")
    private String type;

    /**
     * Default constructor
     */
    public ContactPropertyValue() {

    }

    /**
     * Creates an instance of ContactPropertyValue with the specified attributes.
     *
     * @param value The property value, a String, Number, or Boolean depending on type.
     * @param type  The property type ("string", "number", or "boolean").
     */
    public ContactPropertyValue(final Object value, final String type) {
        this.value = value;
        this.type = type;
    }

    /**
     * Gets the property value.
     *
     * @return The property value, a String, Number, or Boolean depending on type.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Gets the property type.
     *
     * @return The property type ("string", "number", or "boolean").
     */
    public String getType() {
        return type;
    }
}
