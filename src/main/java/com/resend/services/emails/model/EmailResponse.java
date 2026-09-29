package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a base response for emails.
 */
public abstract class EmailResponse {

    /**
     * The unique identifier associated with the email.
     */
    @JsonProperty("id")
    private @Nullable String id;

    /**
     * The resource object.
     */
    @JsonProperty("object")
    private @Nullable String object;

    /**
     * Constructs a new instance of {@code EmailResponse}.
     */
    public EmailResponse() {
    }

    /**
     * Constructs a EmailResponse with the provided ID.
     *
     * @param id The ID associated with the sent email.
     * @param object The resource object.
     */
    public EmailResponse(@Nullable String id, @Nullable String object) {
        this.id = id;
        this.object = object;
    }

    /**
     * Retrieves the ID associated with the sent email.
     *
     * @return The ID of the sent email.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the ID for the sent email.
     *
     * @param id The ID to be set.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }

    /**
     * Retrieves the resource object.
     *
     * @return The resource of the object.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Sets the object for the sent email.
     *
     * @param object The Object to be set.
     */
    public void setObject(@Nullable String object) {
        this.object = object;
    }
}