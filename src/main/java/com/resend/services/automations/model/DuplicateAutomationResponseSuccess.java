package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response from duplicating an automation.
 */
public class DuplicateAutomationResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Default constructor for deserialization.
     */
    public DuplicateAutomationResponseSuccess() {
    }

    /**
     * Constructs a DuplicateAutomationResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param id The ID of the newly created automation.
     */
    public DuplicateAutomationResponseSuccess(@Nullable String object, @Nullable String id) {
        this.object = object;
        this.id = id;
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
     * Retrieves the ID of the newly created automation.
     *
     * @return The automation ID.
     */
    public @Nullable String getId() {
        return id;
    }
}
