package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a custom property value on a contact, returned as an object
 * with the value and its type.
 */
public class ContactPropertyValue {

    @JsonProperty("value")
    private @Nullable Object value;

    @JsonProperty("type")
    private @Nullable String type;

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
    public ContactPropertyValue(final @Nullable Object value, final @Nullable String type) {
        this.value = value;
        this.type = type;
    }

    /**
     * Gets the property value.
     *
     * @return The property value, a String, Number, or Boolean depending on type.
     */
    public @Nullable Object getValue() {
        return value;
    }

    /**
     * Gets the property type.
     *
     * @return The property type ("string", "number", or "boolean").
     */
    public @Nullable String getType() {
        return type;
    }
}
