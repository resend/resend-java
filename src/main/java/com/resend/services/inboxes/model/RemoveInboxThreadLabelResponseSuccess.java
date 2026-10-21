package com.resend.services.inboxes.model;

import java.util.List;

/**
 * Represents a successful response for removing a label from a thread.
 * Extends the InboxThreadLabelList class.
 */
public class RemoveInboxThreadLabelResponseSuccess extends InboxThreadLabelList {

    /**
     * Default constructor
     */
    public RemoveInboxThreadLabelResponseSuccess() {

    }

    /**
     * Constructs a RemoveInboxThreadLabelResponseSuccess.
     *
     * @param object The object type. Always {@code list}.
     * @param data   Every label on the thread after the change.
     */
    public RemoveInboxThreadLabelResponseSuccess(String object, List<InboxThreadLabel> data) {
        super(object, data);
    }
}
