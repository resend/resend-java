package com.resend.services.apikeys.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.resend.core.util.DateTimeDeserializer;
import com.resend.core.util.DateTimeSerializer;
import java.time.OffsetDateTime;

/**
 * Represents an API key item.
 */
public class ApiKeyItem {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private OffsetDateTime createdAt;

    /**
     * Default constructor. Creates an instance of ApiKeyItem with default values.
     */
    public ApiKeyItem() {
    }

    /**
     * Creates an instance of ApiKeyItem with the specified attributes.
     *
     * @param id        The ID of the API key item.
     * @param name      The name of the API key item.
     * @param createdAt The creation timestamp of the API key item.
     */
    public ApiKeyItem(String id, String name, OffsetDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    /**
     * Gets the ID of the API key item.
     *
     * @return The ID of the API key item.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the API key item.
     *
     * @return The name of the API key item.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the creation timestamp of the API key item.
     *
     * @return The creation timestamp of the API key item.
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
