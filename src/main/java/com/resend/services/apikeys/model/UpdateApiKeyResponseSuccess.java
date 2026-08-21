package com.resend.services.apikeys.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for updating an api key.
 */
public class UpdateApiKeyResponseSuccess {

    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor
     */
    public UpdateApiKeyResponseSuccess() {
    }

    /**
     * Constructs a successful response for updating an UpdateApiKeyResponseSuccess object.
     *
     * @param id     The ID of the api key.
     * @param object The object of the api key.
     */
    public UpdateApiKeyResponseSuccess(final String id, final String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Gets the ID of the api key.
     *
     * @return The ID of the api key.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the object of the api key.
     *
     * @return The object of the api key.
     */
    public String getObject() {
        return object;
    }
}
