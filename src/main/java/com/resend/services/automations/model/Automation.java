package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents an automation with its full details.
 */
public class Automation {

    @JsonProperty("object")
    private String object;

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

    @JsonProperty("steps")
    private List<AutomationStepResponse> steps;

    @JsonProperty("connections")
    private List<AutomationConnection> connections;

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
    public Automation(String object, String id, String name, AutomationStatus status,
                      String createdAt, String updatedAt,
                      List<AutomationStepResponse> steps, List<AutomationConnection> connections) {
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
    public String getObject() {
        return object;
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

    /**
     * Retrieves the list of automation steps.
     *
     * @return The list of steps.
     */
    public List<AutomationStepResponse> getSteps() {
        return steps;
    }

    /**
     * Retrieves the list of automation connections.
     *
     * @return The list of connections.
     */
    public List<AutomationConnection> getConnections() {
        return connections;
    }
}
