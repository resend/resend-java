package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for updating a draft.
 * The response carries only the draft ID. Retrieve the draft to read its updated content.
 */
public class UpdateInboxDraftResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor
     */
    public UpdateInboxDraftResponseSuccess() {

    }

    /**
     * Constructs a UpdateInboxDraftResponseSuccess.
     *
     * @param object The object type. Always {@code inbox_draft}.
     * @param id     The ID of the updated draft.
     */
    public UpdateInboxDraftResponseSuccess(String object, String id) {
        this.object = object;
        this.id = id;
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
     * Get the ID of the updated draft.
     *
     * @return The ID of the updated draft.
     */
    public String getId() {
        return id;
    }
}
