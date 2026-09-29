package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the response from deleting a template.
 */
public class DeleteTemplateResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("deleted")
    private @Nullable Boolean deleted;

    /**
     * Default constructor.
     */
    public DeleteTemplateResponseSuccess() {
    }

    /**
     * Constructs a DeleteTemplateResponse with the specified attributes.
     *
     * @param object  The object type.
     * @param id      The ID of the deleted template.
     * @param deleted Whether the template was deleted.
     */
    public DeleteTemplateResponseSuccess(@Nullable String object, @Nullable String id, @Nullable Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
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
     * Gets the ID of the deleted template.
     *
     * @return The ID of the deleted template.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the ID of the deleted template.
     *
     * @param id The ID of the deleted template.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }

    /**
     * Gets whether the template was deleted.
     *
     * @return Whether the template was deleted.
     */
    public @Nullable Boolean getDeleted() {
        return deleted;
    }

    /**
     * Sets whether the template was deleted.
     *
     * @param deleted Whether the template was deleted.
     */
    public void setDeleted(@Nullable Boolean deleted) {
        this.deleted = deleted;
    }
}
