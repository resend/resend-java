package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for updating a domain.
 */
public class UpdateDomainResponseSuccess {
    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor
     */
    public UpdateDomainResponseSuccess() {
    }

    /**
     * Constructs a successful response for creating a UpdateDomainResponseSuccess object.
     *
     * @param id          The ID of the domain.
     * @param object      The object of the domain.
     */
    public UpdateDomainResponseSuccess(final String id, final String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Gets the ID of the domain item.
     *
     * @return The ID of the domain item.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the object of the domain item.
     *
     * @return The object of the domain item.
     */
    public String getObject() {
        return object;
    }

}