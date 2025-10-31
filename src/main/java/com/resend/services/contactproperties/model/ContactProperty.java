package com.resend.services.contactproperties.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a contact property item.
 */
public class ContactProperty {

    @JsonProperty("id")
    private String id;

    @JsonProperty("key")
    private String key;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("type")
    private String type;

    @JsonProperty("fallback_value")
    private String fallbackValue;

    /**
     * Default constructor
     */
    public ContactProperty() {

    }

    /**
     * Creates an instance of ContactProperty with the specified attributes.
     *
     * @param id              The ID of the contact property.
     * @param key             The key of the contact property.
     * @param createdAt       The creation timestamp of the contact property.
     * @param type            The type of the contact property.
     * @param fallbackValue   The fallback value of the contact property.
     */
    public ContactProperty(final String id, final String key, final String createdAt, final String type, final String fallbackValue) {
        this.id = id;
        this.key = key;
        this.createdAt = createdAt;
        this.type = type;
        this.fallbackValue = fallbackValue;
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
     * Gets the key of the contact property.
     *
     * @return The key of the contact property.
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the creation timestamp of the contact property.
     *
     * @return The creation timestamp of the contact property.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the type of the contact property.
     *
     * @return The type of the contact property.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the fallback value of the contact property.
     *
     * @return The fallback value of the contact property.
     */
    public String getFallbackValue() {
        return fallbackValue;
    }
}
