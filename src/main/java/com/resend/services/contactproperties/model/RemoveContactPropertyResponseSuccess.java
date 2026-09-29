package com.resend.services.contactproperties.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response for removing a contact property.
 */
public class RemoveContactPropertyResponseSuccess extends BaseContactProperty {

    @JsonProperty("deleted")
    private boolean deleted;

    /**
     * Default constructor
     */
    public RemoveContactPropertyResponseSuccess() {
    }

    /**
     * Constructs a successful response for removing a contact property.
     *
     * @param id        The ID of the contact property.
     * @param object    The object type of the contact property.
     * @param deleted   Whether the contact property was deleted.
     */
    public RemoveContactPropertyResponseSuccess(final @Nullable String id, final @Nullable String object, final boolean deleted) {
        super(id, object);
        this.deleted = deleted;
    }

    /**
     * Gets whether the contact property was deleted.
     *
     * @return Whether the contact property was deleted.
     */
    public boolean isDeleted() {
        return deleted;
    }

}
