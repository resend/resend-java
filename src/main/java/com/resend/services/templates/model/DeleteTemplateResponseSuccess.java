package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response from deleting a template.
 */
public class DeleteTemplateResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private Boolean deleted;

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
    public DeleteTemplateResponseSuccess(String object, String id, Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Gets the ID of the deleted template.
     *
     * @return The ID of the deleted template.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the deleted template.
     *
     * @param id The ID of the deleted template.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets whether the template was deleted.
     *
     * @return Whether the template was deleted.
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * Sets whether the template was deleted.
     *
     * @param deleted Whether the template was deleted.
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
