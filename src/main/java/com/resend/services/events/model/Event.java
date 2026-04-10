package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Represents an event with its full details.
 */
public class Event {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("schema")
    private Map<String, String> schema;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    public Event() {
    }

    public Event(String object, String id, String name, Map<String, String> schema,
                 String createdAt, String updatedAt) {
        this.object = object;
        this.id = id;
        this.name = name;
        this.schema = schema;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
     * Retrieves the event ID.
     *
     * @return The event ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the event name.
     *
     * @return The event name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the event schema.
     *
     * @return The event schema as a map of field names to types.
     */
    public Map<String, String> getSchema() {
        return schema;
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
