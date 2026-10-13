package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a draft in a list of drafts.
 */
public class InboxDraftListItem {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("to")
    private List<String> to;

    @JsonProperty("cc")
    private List<String> cc;

    @JsonProperty("bcc")
    private List<String> bcc;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("snippet")
    private String snippet;

    @JsonProperty("thread_id")
    private String threadId;

    @JsonProperty("reply_to_email_id")
    private String replyToEmailId;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("attachments")
    private List<InboxDraftAttachment> attachments;

    /**
     * Default constructor
     */
    public InboxDraftListItem() {

    }

    /**
     * Constructs a InboxDraftListItem.
     *
     * @param id             The ID of the draft.
     * @param type           The draft type. {@code reply} when the draft replies to an email in an existing thread. {@code standalone} when the draft starts a new thread.
     * @param to             The recipients. {@code null} or empty when no recipients are set.
     * @param cc             The CC recipients.
     * @param bcc            The BCC recipients.
     * @param subject        The subject.
     * @param snippet        A short preview of the body.
     * @param threadId       The Thread ID when the draft is a reply. {@code null} otherwise.
     * @param replyToEmailId The Email ID the draft replies to. {@code null} otherwise.
     * @param updatedAt      The timestamp when the draft was last saved.
     * @param attachments    The files stored on the draft.
     */
    public InboxDraftListItem(String id, String type, List<String> to, List<String> cc, List<String> bcc, String subject, String snippet, String threadId, String replyToEmailId, String updatedAt, List<InboxDraftAttachment> attachments) {
        this.id = id;
        this.type = type;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.snippet = snippet;
        this.threadId = threadId;
        this.replyToEmailId = replyToEmailId;
        this.updatedAt = updatedAt;
        this.attachments = attachments;
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
     * Get the draft type. {@code reply} when the draft replies to an email in an existing thread. {@code standalone} when the draft starts a new thread.
     *
     * @return The draft type. {@code reply} when the draft replies to an email in an existing thread. {@code standalone} when the draft starts a new thread.
     */
    public String getType() {
        return type;
    }

    /**
     * Get the recipients. {@code null} or empty when no recipients are set.
     *
     * @return The recipients. {@code null} or empty when no recipients are set.
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * Get the CC recipients.
     *
     * @return The CC recipients.
     */
    public List<String> getCc() {
        return cc;
    }

    /**
     * Get the BCC recipients.
     *
     * @return The BCC recipients.
     */
    public List<String> getBcc() {
        return bcc;
    }

    /**
     * Get the subject.
     *
     * @return The subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get a short preview of the body.
     *
     * @return A short preview of the body.
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     * Get the Thread ID when the draft is a reply. {@code null} otherwise.
     *
     * @return The Thread ID when the draft is a reply. {@code null} otherwise.
     */
    public String getThreadId() {
        return threadId;
    }

    /**
     * Get the Email ID the draft replies to. {@code null} otherwise.
     *
     * @return The Email ID the draft replies to. {@code null} otherwise.
     */
    public String getReplyToEmailId() {
        return replyToEmailId;
    }

    /**
     * Get the timestamp when the draft was last saved.
     *
     * @return The timestamp when the draft was last saved.
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Get the files stored on the draft.
     *
     * @return The files stored on the draft.
     */
    public List<InboxDraftAttachment> getAttachments() {
        return attachments;
    }
}
