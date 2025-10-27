package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response from updating a template.
 */
public class UpdateTemplateResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor.
     */
    public UpdateTemplateResponse() {
    }

    /**
     * Constructs an UpdateTemplateResponse with the specified attributes.
     *
     * @param id     The ID of the updated template.
     * @param object The object type.
     */
    public UpdateTemplateResponse(String id, String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Gets the ID of the updated template.
     *
     * @return The ID of the updated template.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the updated template.
     *
     * @param id The ID of the updated template.
     */
    public void setId(String id) {
        this.id = id;
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
}
