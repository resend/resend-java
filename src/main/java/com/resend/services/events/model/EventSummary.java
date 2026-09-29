package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.Map;

/**
 * Represents a summary of an event in list responses.
 */
public class EventSummary {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("name")
    private @Nullable String name;

    @JsonProperty("schema")
    private @Nullable Map<String, String> schema;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("updated_at")
    private @Nullable String updatedAt;

    /**
     * Default constructor for deserialization.
     */
    public EventSummary() {
    }

    /**
     * Constructs an EventSummary with specified values.
     *
     * @param id The event ID.
     * @param name The event name.
     * @param schema The event schema.
     * @param createdAt The creation timestamp.
     * @param updatedAt The last update timestamp.
     */
    public EventSummary(@Nullable String id, @Nullable String name, @Nullable Map<String, String> schema,
                        @Nullable String createdAt, @Nullable String updatedAt) {
        this.id = id;
        this.name = name;
        this.schema = schema;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Retrieves the event ID.
     *
     * @return The event ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Retrieves the event name.
     *
     * @return The event name.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Retrieves the event schema.
     *
     * @return The event schema as a map of field names to types.
     */
    public @Nullable Map<String, String> getSchema() {
        return schema;
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
