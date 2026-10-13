package com.resend.services.inboxes.model;

import java.util.List;

/**
 * Represents a successful response for updating a draft.
 * Extends the InboxDraft class.
 */
public class UpdateInboxDraftResponseSuccess extends InboxDraft {

    /**
     * Default constructor
     */
    public UpdateInboxDraftResponseSuccess() {

    }

    /**
     * Constructs a UpdateInboxDraftResponseSuccess.
     *
     * @param object         The object type. Always {@code inbox_draft}.
     * @param id             The ID of the draft.
     * @param type           The draft type. {@code reply} when the draft replies to an email in an existing thread. {@code standalone} when the draft starts a new thread.
     * @param to             The recipients. {@code null} or empty when no recipients are set.
     * @param cc             The CC recipients.
     * @param bcc            The BCC recipients.
     * @param subject        The subject.
     * @param html           The HTML body.
     * @param text           The plain text body.
     * @param threadId       The Thread ID when the draft is a reply. {@code null} otherwise.
     * @param replyToEmailId The Email ID the draft replies to. {@code null} otherwise.
     * @param emailId        The ID of the outbound email queued when the draft was sent. {@code null} until the draft is sent.
     * @param createdAt      The timestamp when the draft was created.
     * @param updatedAt      The timestamp when the draft was last saved.
     * @param attachments    The files stored on the draft.
     */
    public UpdateInboxDraftResponseSuccess(String object, String id, String type, List<String> to, List<String> cc, List<String> bcc, String subject, String html, String text, String threadId, String replyToEmailId, String emailId, String createdAt, String updatedAt, List<InboxDraftAttachment> attachments) {
        super(object, id, type, to, cc, bcc, subject, html, text, threadId, replyToEmailId, emailId, createdAt, updatedAt, attachments);
    }
}
