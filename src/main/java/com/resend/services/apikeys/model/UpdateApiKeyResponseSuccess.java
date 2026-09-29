package com.resend.services.apikeys.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response for updating an api key.
 */
public class UpdateApiKeyResponseSuccess {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("object")
    private @Nullable String object;

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
    public UpdateApiKeyResponseSuccess(final @Nullable String id, final @Nullable String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Gets the ID of the api key.
     *
     * @return The ID of the api key.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the object of the api key.
     *
     * @return The object of the api key.
     */
    public @Nullable String getObject() {
        return object;
    }
}
