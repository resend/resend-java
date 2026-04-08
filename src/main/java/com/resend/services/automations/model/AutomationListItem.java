package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an automation summary in list responses.
 */
public class AutomationListItem {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private AutomationStatus status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    public AutomationListItem() {
    }

    public AutomationListItem(String id, String name, AutomationStatus status, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Retrieves the automation ID.
     *
     * @return The automation ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the automation name.
     *
     * @return The automation name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the automation status.
     *
     * @return The automation status.
     */
    public AutomationStatus getStatus() {
        return status;
    }

    /**
     * Retrieves the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Retrieves the last update timestamp.
     *
     * @return The last update timestamp.
     */
    public String getUpdatedAt() {
        return updatedAt;
    }
}
