package com.resend.services.inboxes.model;

import java.util.List;

/**
 * Represents a successful response for adding labels to a thread.
 * Extends the InboxThreadLabelList class.
 */
public class AddInboxThreadLabelsResponseSuccess extends InboxThreadLabelList {

    /**
     * Default constructor
     */
    public AddInboxThreadLabelsResponseSuccess() {

    }

    /**
     * Constructs a AddInboxThreadLabelsResponseSuccess.
     *
     * @param object The object type. Always {@code list}.
     * @param data   Every label on the thread after the change.
     */
    public AddInboxThreadLabelsResponseSuccess(String object, List<InboxThreadLabel> data) {
        super(object, data);
    }
}
