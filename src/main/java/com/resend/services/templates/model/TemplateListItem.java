package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a template item in a list response.
 */
public class TemplateListItem {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("name")
    private @Nullable String name;

    @JsonProperty("status")
    private @Nullable String status;

    @JsonProperty("published_at")
    private @Nullable String publishedAt;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("updated_at")
    private @Nullable String updatedAt;

    @JsonProperty("alias")
    private @Nullable String alias;

    /**
     * Default constructor.
     */
    public TemplateListItem() {
    }

    /**
     * Gets the ID of the template.
     *
     * @return The ID of the template.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the ID of the template.
     *
     * @param id The ID of the template.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }

    /**
     * Gets the name of the template.
     *
     * @return The name of the template.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Sets the name of the template.
     *
     * @param name The name of the template.
     */
    public void setName(@Nullable String name) {
        this.name = name;
    }

    /**
     * Gets the status of the template.
     *
     * @return The status of the template.
     */
    public @Nullable String getStatus() {
        return status;
    }

    /**
     * Sets the status of the template.
     *
     * @param status The status of the template.
     */
    public void setStatus(@Nullable String status) {
        this.status = status;
    }

    /**
     * Gets the publication timestamp of the template.
     *
     * @return The publication timestamp.
     */
    public @Nullable String getPublishedAt() {
        return publishedAt;
    }

    /**
     * Sets the publication timestamp of the template.
     *
     * @param publishedAt The publication timestamp.
     */
    public void setPublishedAt(@Nullable String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * Gets the creation timestamp of the template.
     *
     * @return The creation timestamp.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the template.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(@Nullable String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last update timestamp of the template.
     *
     * @return The last update timestamp.
     */
    public @Nullable String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update timestamp of the template.
     *
     * @param updatedAt The last update timestamp.
     */
    public void setUpdatedAt(@Nullable String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the alias of the template.
     *
     * @return The alias of the template.
     */
    public @Nullable String getAlias() {
        return alias;
    }

    /**
     * Sets the alias of the template.
     *
     * @param alias The alias of the template.
     */
    public void setAlias(@Nullable String alias) {
        this.alias = alias;
    }
}
