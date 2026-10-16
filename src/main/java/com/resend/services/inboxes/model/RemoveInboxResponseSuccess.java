package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for removing an inbox.
 */
public class RemoveInboxResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private Boolean deleted;

    /**
     * Default constructor
     */
    public RemoveInboxResponseSuccess() {

    }

    /**
     * Constructs a RemoveInboxResponseSuccess.
     *
     * @param object  The object type. Always {@code inbox}.
     * @param id      The ID of the inbox.
     * @param deleted Whether the inbox was deleted.
     */
    public RemoveInboxResponseSuccess(String object, String id, Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
    }

    /**
     * Get the object type. Always {@code inbox}.
     *
     * @return The object type. Always {@code inbox}.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the ID of the inbox.
     *
     * @return The ID of the inbox.
     */
    public String getId() {
        return id;
    }

    /**
     * Get whether the inbox was deleted.
     *
     * @return Whether the inbox was deleted.
     */
    public Boolean getDeleted() {
        return deleted;
    }
}
