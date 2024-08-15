package com.resend.services.apikeys.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an API key item.
 */
public class ApiKey {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    private String createdAt;

    /**
     * Default constructor. Creates an instance of ApiKey with default values.
     */
    public ApiKey() {
    }

    /**
     * Creates an instance of ApiKey with the specified attributes.
     *
     * @param id        The ID of the API key item.
     * @param name      The name of the API key item.
     * @param createdAt The creation timestamp of the API key item.
     */
    public ApiKey(String id, String name, String createdAt) {
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
    public String getCreatedAt() {
        return createdAt;
    }
}
