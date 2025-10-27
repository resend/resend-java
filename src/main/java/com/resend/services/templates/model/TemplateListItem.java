package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a template item in a list response.
 */
public class TemplateListItem {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;

    @JsonProperty("published_at")
    private String publishedAt;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("alias")
    private String alias;

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
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the template.
     *
     * @param id The ID of the template.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the template.
     *
     * @return The name of the template.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the template.
     *
     * @param name The name of the template.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the status of the template.
     *
     * @return The status of the template.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the template.
     *
     * @param status The status of the template.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the publication timestamp of the template.
     *
     * @return The publication timestamp.
     */
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * Sets the publication timestamp of the template.
     *
     * @param publishedAt The publication timestamp.
     */
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * Gets the creation timestamp of the template.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the template.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last update timestamp of the template.
     *
     * @return The last update timestamp.
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update timestamp of the template.
     *
     * @param updatedAt The last update timestamp.
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the alias of the template.
     *
     * @return The alias of the template.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the alias of the template.
     *
     * @param alias The alias of the template.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
}
