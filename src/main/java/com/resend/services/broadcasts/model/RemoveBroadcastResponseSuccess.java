package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the response for a successful broadcast removal.
 * Extends the BaseBroadcastResponse class.
 */
public class RemoveBroadcastResponseSuccess extends BaseBroadcastResponse {
    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("deleted")
    private boolean deleted;

    /**
     * Default constructor
     */
    public RemoveBroadcastResponseSuccess() {

    }

    /**
     * Constructs a successful response for deleting a broadcast.
     *
     * @param id        The ID of the broadcast.
     * @param object    The object of the broadcast.
     * @param deleted    The state of the broadcast.
     */
    public RemoveBroadcastResponseSuccess(@Nullable String id, @Nullable String object, boolean deleted) {
        super(id);
        this.object = object;
        this.deleted = deleted;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Get the state.
     *
     * @return The state of the data.
     */
    public boolean isDeleted() {
        return deleted;
    }
}
