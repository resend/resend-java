package com.resend.services.inboxes.model;

import java.util.List;

/**
 * Represents a successful response for retrieving a thread.
 * Extends the InboxThreadSummary class.
 */
public class GetInboxThreadResponseSuccess extends InboxThreadSummary {

    /**
     * Default constructor
     */
    public GetInboxThreadResponseSuccess() {

    }

    /**
     * Constructs a GetInboxThreadResponseSuccess.
     *
     * @param object  The object type. Always {@code inbox_thread}.
     * @param id      The ID of the thread.
     * @param subject The subject of the thread.
     * @param folder  The folder the thread is in.
     * @param labels  The labels attached to the thread.
     * @param read    True only when every email in the thread is read.
     */
    public GetInboxThreadResponseSuccess(String object, String id, String subject, String folder, List<InboxThreadLabel> labels, Boolean read) {
        super(object, id, subject, folder, labels, read);
    }
}
