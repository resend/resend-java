package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the response from duplicating a template.
 */
public class DuplicateTemplateResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    /**
     * Default constructor.
     */
    public DuplicateTemplateResponseSuccess() {
    }

    /**
     * Constructs a DuplicateTemplateResponse with the specified attributes.
     *
     * @param object The object type.
     * @param id     The ID of the duplicated template.
     */
    public DuplicateTemplateResponseSuccess(@Nullable String object, @Nullable String id) {
        this.object = object;
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

    /**
     * Gets the ID of the duplicated template.
     *
     * @return The ID of the duplicated template.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the ID of the duplicated template.
     *
     * @param id The ID of the duplicated template.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }
}
