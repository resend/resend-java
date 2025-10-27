package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response from duplicating a template.
 */
public class DuplicateTemplateResponse {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor.
     */
    public DuplicateTemplateResponse() {
    }

    /**
     * Constructs a DuplicateTemplateResponse with the specified attributes.
     *
     * @param object The object type.
     * @param id     The ID of the duplicated template.
     */
    public DuplicateTemplateResponse(String object, String id) {
        this.object = object;
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

    /**
     * Gets the ID of the duplicated template.
     *
     * @return The ID of the duplicated template.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the duplicated template.
     *
     * @param id The ID of the duplicated template.
     */
    public void setId(String id) {
        this.id = id;
    }
}
