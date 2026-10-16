package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents every label on a thread after a change to its labels.
 */
public class InboxThreadLabelList {

    @JsonProperty("object")
    private String object;

    @JsonProperty("data")
    private List<InboxThreadLabel> data;

    /**
     * Default constructor
     */
    public InboxThreadLabelList() {

    }

    /**
     * Constructs an InboxThreadLabelList.
     *
     * @param object The object type. Always {@code list}.
     * @param data   Every label on the thread after the change.
     */
    public InboxThreadLabelList(String object, List<InboxThreadLabel> data) {
        this.object = object;
        this.data = data;
    }

    /**
     * Get the object type. Always {@code list}.
     *
     * @return The object type. Always {@code list}.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get every label on the thread after the change.
     *
     * @return Every label on the thread after the change.
     */
    public List<InboxThreadLabel> getData() {
        return data;
    }
}
