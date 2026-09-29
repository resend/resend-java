package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a response object for a domain action. This class is typically used to
 * deserialize responses from verification and deletion operation.
 */
public abstract class AbstractDomainAction {
    /**
     * The object type of the response.
     */
    @JsonProperty("object")
    public @Nullable String object;

    /**
     * The unique identifier associated with the verified domain.
     */
    @JsonProperty("id")
    public @Nullable String id;

    /**
     * Default constructor for creating an empty AbstractDomainAction object.
     */
    public AbstractDomainAction() {
    }

    /**
     * Constructs a AbstractDomainAction object with the provided object type and identifier.
     *
     * @param object The object type of the response.
     * @param id     The unique identifier associated with the verified domain.
     */
    public AbstractDomainAction(@Nullable String object, @Nullable String id) {
        this.object = object;
        this.id = id;
    }

    /**
     * Get the object type of the response.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Get the unique identifier associated with the verified domain.
     *
     * @return The unique identifier.
     */
    public @Nullable String getId() {
        return id;
    }
}
