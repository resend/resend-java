package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the response from publishing a template.
 */
public class PublishTemplateResponseSuccess {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("object")
    private @Nullable String object;

    /**
     * Default constructor.
     */
    public PublishTemplateResponseSuccess() {
    }

    /**
     * Constructs a PublishTemplateResponse with the specified attributes.
     *
     * @param id     The ID of the published template.
     * @param object The object type.
     */
    public PublishTemplateResponseSuccess(@Nullable String id, @Nullable String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Gets the ID of the published template.
     *
     * @return The ID of the published template.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the ID of the published template.
     *
     * @param id The ID of the published template.
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
