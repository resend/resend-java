package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the response from updating a template.
 */
public class UpdateTemplateResponseSuccess {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("object")
    private @Nullable String object;

    /**
     * Default constructor.
     */
    public UpdateTemplateResponseSuccess() {
    }

    /**
     * Constructs an UpdateTemplateResponse with the specified attributes.
     *
     * @param id     The ID of the updated template.
     * @param object The object type.
     */
    public UpdateTemplateResponseSuccess(@Nullable String id, @Nullable String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Gets the ID of the updated template.
     *
     * @return The ID of the updated template.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the ID of the updated template.
     *
     * @param id The ID of the updated template.
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
