package com.resend.services.inboxes.model;

import java.util.List;

/**
 * Represents a successful response for setting the labels of a thread.
 * Extends the InboxThreadLabelList class.
 */
public class SetInboxThreadLabelsResponseSuccess extends InboxThreadLabelList {

    /**
     * Default constructor
     */
    public SetInboxThreadLabelsResponseSuccess() {

    }

    /**
     * Constructs a SetInboxThreadLabelsResponseSuccess.
     *
     * @param object The object type. Always {@code list}.
     * @param data   Every label on the thread after the change.
     */
    public SetInboxThreadLabelsResponseSuccess(String object, List<InboxThreadLabel> data) {
        super(object, data);
    }
}
