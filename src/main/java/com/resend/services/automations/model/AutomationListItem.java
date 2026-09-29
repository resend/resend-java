package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents an automation summary in list responses.
 */
public class AutomationListItem {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("name")
    private @Nullable String name;

    @JsonProperty("status")
    private @Nullable AutomationStatus status;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("updated_at")
    private @Nullable String updatedAt;

    /**
     * Default constructor for deserialization.
     */
    public AutomationListItem() {
    }

    /**
     * Constructs an AutomationListItem with specified values.
     *
     * @param id The automation ID.
     * @param name The automation name.
     * @param status The automation status.
     * @param createdAt The creation timestamp.
     * @param updatedAt The last update timestamp.
     */
    public AutomationListItem(@Nullable String id, @Nullable String name, @Nullable AutomationStatus status, @Nullable String createdAt, @Nullable String updatedAt) {
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
    public @Nullable String getId() {
        return id;
    }

    /**
     * Retrieves the automation name.
     *
     * @return The automation name.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Retrieves the automation status.
     *
     * @return The automation status.
     */
    public @Nullable AutomationStatus getStatus() {
        return status;
    }

    /**
     * Retrieves the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Retrieves the last update timestamp.
     *
     * @return The last update timestamp.
     */
    public @Nullable String getUpdatedAt() {
        return updatedAt;
    }
}
