package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a suppression removed from the suppression list.
 */
public class RemovedSuppression {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("deleted")
    private @Nullable Boolean deleted;

    /**
     * Default constructor
     */
    public RemovedSuppression() {

    }

    /**
     * Constructs a removed suppression.
     *
     * @param object  The object type of the suppression.
     * @param id      The ID of the suppression.
     * @param deleted Whether the suppression was deleted.
     */
    public RemovedSuppression(@Nullable String object, @Nullable String id, @Nullable Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
    }

    /**
     * Get the object type.
     *
     * @return The object type of the suppression.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Get the ID of the suppression.
     *
     * @return The ID of the suppression.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Get whether the suppression was deleted.
     *
     * @return Whether the suppression was deleted.
     */
    public @Nullable Boolean getDeleted() {
        return deleted;
    }
}
