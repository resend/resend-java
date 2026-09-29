package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response for creating an audience.
 * Extends the Audiences class.
 */
public class CreateAudienceResponseSuccess extends BaseAudience {

    @JsonProperty("object")
    private @Nullable String object;

    /**
     * Default constructor
     */
    public CreateAudienceResponseSuccess() {

    }

    /**
     * Constructs a successful response for creating an audience.
     *
     * @param id        The ID of the audience.
     * @param name      The name of the audience.
     * @param object    The object of the audience.
     */
    public CreateAudienceResponseSuccess(@Nullable String id, @Nullable String name, @Nullable String object) {
        super(id, name);
        this.object = object;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public @Nullable String getObject() {
        return object;
    }
}
