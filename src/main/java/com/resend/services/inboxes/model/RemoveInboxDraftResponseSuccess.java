package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for removing a draft.
 */
public class RemoveInboxDraftResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private Boolean deleted;

    /**
     * Default constructor
     */
    public RemoveInboxDraftResponseSuccess() {

    }

    /**
     * Constructs a RemoveInboxDraftResponseSuccess.
     *
     * @param object  The object type. Always {@code inbox_draft}.
     * @param id      The ID of the draft.
     * @param deleted Whether the draft was deleted.
     */
    public RemoveInboxDraftResponseSuccess(String object, String id, Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
    }

    /**
     * Get the object type. Always {@code inbox_draft}.
     *
     * @return The object type. Always {@code inbox_draft}.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the ID of the draft.
     *
     * @return The ID of the draft.
     */
    public String getId() {
        return id;
    }

    /**
     * Get whether the draft was deleted.
     *
     * @return Whether the draft was deleted.
     */
    public Boolean getDeleted() {
        return deleted;
    }
}
