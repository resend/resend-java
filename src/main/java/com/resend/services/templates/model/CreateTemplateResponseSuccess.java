package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the response from creating a template.
 */
public class CreateTemplateResponseSuccess {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("object")
    private @Nullable String object;

    /**
     * Default constructor.
     */
    public CreateTemplateResponseSuccess() {
    }

    /**
     * Constructs a CreateTemplateResponse with the specified attributes.
     *
     * @param id     The ID of the created template.
     * @param object The object type.
     */
    public CreateTemplateResponseSuccess(@Nullable String id, @Nullable String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Gets the ID of the created template.
     *
     * @return The ID of the created template.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the ID of the created template.
     *
     * @param id The ID of the created template.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(@Nullable String object) {
        this.object = object;
    }
}
