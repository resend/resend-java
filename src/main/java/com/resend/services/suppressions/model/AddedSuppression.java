package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a suppression added to the suppression list.
 */
public class AddedSuppression {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Default constructor
     */
    public AddedSuppression() {

    }

    /**
     * Constructs an added suppression.
     *
     * @param object The object type of the suppression.
     * @param id     The ID of the suppression.
     */
    public AddedSuppression(@Nullable String object, @Nullable String id) {
        this.object = object;
        this.id = id;
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
}
