package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for creating a draft.
 */
public class CreateInboxDraftResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor
     */
    public CreateInboxDraftResponseSuccess() {

    }

    /**
     * Constructs a CreateInboxDraftResponseSuccess.
     *
     * @param object The object type. Always {@code inbox_draft}.
     * @param id     The ID of the draft.
     */
    public CreateInboxDraftResponseSuccess(String object, String id) {
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
     * Get the ID of the draft.
     *
     * @return The ID of the draft.
     */
    public String getId() {
        return id;
    }
}
