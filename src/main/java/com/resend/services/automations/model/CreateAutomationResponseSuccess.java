package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response from creating an automation.
 */
public class CreateAutomationResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Default constructor for deserialization.
     */
    public CreateAutomationResponseSuccess() {
    }

    /**
     * Constructs a CreateAutomationResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param id The automation ID.
     */
    public CreateAutomationResponseSuccess(@Nullable String object, @Nullable String id) {
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
     * Retrieves the created automation ID.
     *
     * @return The automation ID.
     */
    public @Nullable String getId() {
        return id;
    }
}
