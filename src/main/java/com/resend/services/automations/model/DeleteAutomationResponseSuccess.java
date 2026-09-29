package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response from deleting an automation.
 */
public class DeleteAutomationResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("deleted")
    private @Nullable Boolean deleted;

    /**
     * Default constructor for deserialization.
     */
    public DeleteAutomationResponseSuccess() {
    }

    /**
     * Constructs a DeleteAutomationResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param id The automation ID.
     * @param deleted Whether the automation was deleted.
     */
    public DeleteAutomationResponseSuccess(@Nullable String object, @Nullable String id, @Nullable Boolean deleted) {
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
     * Retrieves the deleted automation ID.
     *
     * @return The automation ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Indicates if the automation was successfully deleted.
     *
     * @return True if deleted, false otherwise.
     */
    public @Nullable Boolean getDeleted() {
        return deleted;
    }
}
