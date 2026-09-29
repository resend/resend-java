package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response from removing an event.
 */
public class RemoveEventResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("deleted")
    private @Nullable Boolean deleted;

    /**
     * Default constructor for deserialization.
     */
    public RemoveEventResponseSuccess() {
    }

    /**
     * Constructs a RemoveEventResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param id The event ID.
     * @param deleted Whether the event was deleted.
     */
    public RemoveEventResponseSuccess(@Nullable String object, @Nullable String id, @Nullable Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
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
     * Retrieves the removed event ID.
     *
     * @return The event ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Indicates if the event was successfully deleted.
     *
     * @return True if deleted, false otherwise.
     */
    public @Nullable Boolean getDeleted() {
        return deleted;
    }
}
