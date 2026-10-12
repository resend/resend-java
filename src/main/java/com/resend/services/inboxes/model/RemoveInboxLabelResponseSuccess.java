package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for removing an inbox label.
 */
public class RemoveInboxLabelResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private Boolean deleted;

    /**
     * Default constructor
     */
    public RemoveInboxLabelResponseSuccess() {

    }

    /**
     * Constructs a RemoveInboxLabelResponseSuccess.
     *
     * @param object  The object type. Always {@code inbox_label}.
     * @param id      The ID of the removed label.
     * @param deleted Whether the label was deleted.
     */
    public RemoveInboxLabelResponseSuccess(String object, String id, Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
    }

    /**
     * Get the object type. Always {@code inbox_label}.
     *
     * @return The object type. Always {@code inbox_label}.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the ID of the removed label.
     *
     * @return The ID of the removed label.
     */
    public String getId() {
        return id;
    }

    /**
     * Get whether the label was deleted.
     *
     * @return Whether the label was deleted.
     */
    public Boolean getDeleted() {
        return deleted;
    }
}
