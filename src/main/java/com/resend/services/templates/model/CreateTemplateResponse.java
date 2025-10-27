package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response from creating a template.
 */
public class CreateTemplateResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor.
     */
    public CreateTemplateResponse() {
    }

    /**
     * Constructs a CreateTemplateResponse with the specified attributes.
     *
     * @param id     The ID of the created template.
     * @param object The object type.
     */
    public CreateTemplateResponse(String id, String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Gets the ID of the created template.
     *
     * @return The ID of the created template.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the created template.
     *
     * @param id The ID of the created template.
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
