package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for removing a thread.
 */
public class RemoveInboxThreadResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private Boolean deleted;

    /**
     * Default constructor
     */
    public RemoveInboxThreadResponseSuccess() {

    }

    /**
     * Constructs a RemoveInboxThreadResponseSuccess.
     *
     * @param object  The object type. Always {@code inbox_thread}.
     * @param id      The ID of the thread.
     * @param deleted True when this call moved the thread to {@code trash}. False when the thread was already there.
     */
    public RemoveInboxThreadResponseSuccess(String object, String id, Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
    }

    /**
     * Get the object type. Always {@code inbox_thread}.
     *
     * @return The object type. Always {@code inbox_thread}.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the ID of the thread.
     *
     * @return The ID of the thread.
     */
    public String getId() {
        return id;
    }

    /**
     * Get whether this call moved the thread to {@code trash}. False when the thread was already there.
     *
     * @return True when this call moved the thread to {@code trash}. False when the thread was already there.
     */
    public Boolean getDeleted() {
        return deleted;
    }
}
