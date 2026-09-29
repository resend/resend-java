package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents an automation with its full details.
 */
public class Automation {

    @JsonProperty("object")
    private @Nullable String object;

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

    @JsonProperty("steps")
    private @Nullable List<AutomationStepResponse> steps;

    @JsonProperty("connections")
    private @Nullable List<AutomationConnection> connections;

    /**
     * Default constructor for deserialization.
     */
    public Automation() {
    }

    /**
     * Constructs an Automation with specified values.
     *
     * @param object The object type.
     * @param id The automation ID.
     * @param name The automation name.
     * @param status The automation status.
     * @param createdAt The creation timestamp.
     * @param updatedAt The last update timestamp.
     * @param steps The list of steps.
     * @param connections The list of connections.
     */
    public Automation(@Nullable String object, @Nullable String id, @Nullable String name, @Nullable AutomationStatus status,
                      @Nullable String createdAt, @Nullable String updatedAt,
                      @Nullable List<AutomationStepResponse> steps, @Nullable List<AutomationConnection> connections) {
        this.object = object;
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.steps = steps;
        this.connections = connections;
    }

    /**
     * Retrieves the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
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

    /**
     * Retrieves the list of automation steps.
     *
     * @return The list of steps.
     */
    public @Nullable List<AutomationStepResponse> getSteps() {
        return steps;
    }

    /**
     * Retrieves the list of automation connections.
     *
     * @return The list of connections.
     */
    public @Nullable List<AutomationConnection> getConnections() {
        return connections;
    }
}
