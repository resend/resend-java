package com.resend.services.apikeys.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents an API key item.
 */
public class ApiKey {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("name")
    private @Nullable String name;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("last_used_at")
    private @Nullable String lastUsedAt;

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
    public ApiKey(@Nullable String id, @Nullable String name, @Nullable String createdAt) {
        this(id, name, createdAt, null);
    }

    /**
     * Creates an instance of ApiKey with the specified attributes.
     *
     * @param id         The ID of the API key item.
     * @param name       The name of the API key item.
     * @param createdAt  The creation timestamp of the API key item.
     * @param lastUsedAt The last used timestamp of the API key item.
     */
    public ApiKey(@Nullable String id, @Nullable String name, @Nullable String createdAt, @Nullable String lastUsedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.lastUsedAt = lastUsedAt;
    }

    /**
     * Gets the ID of the API key item.
     *
     * @return The ID of the API key item.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the name of the API key item.
     *
     * @return The name of the API key item.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Gets the creation timestamp of the API key item.
     *
     * @return The creation timestamp of the API key item.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the last used timestamp of the API key item.
     *
     * @return The last used timestamp of the API key item, or null if never used.
     */
    public @Nullable String getLastUsedAt() {
        return lastUsedAt;
    }
}
