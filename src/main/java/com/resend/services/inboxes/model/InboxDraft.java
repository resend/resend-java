package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a draft on an inbox.
 */
public class InboxDraft {

    @JsonProperty("object")
    private String object;

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

    @JsonProperty("html")
    private String html;

    @JsonProperty("text")
    private String text;

    @JsonProperty("thread_id")
    private String threadId;

    @JsonProperty("reply_to_email_id")
    private String replyToEmailId;

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * Default constructor
     */
    public InboxDraft() {

    }

    /**
     * Constructs a InboxDraft.
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
     */
    public InboxDraft(String object, String id, String type, List<String> to, List<String> cc, List<String> bcc, String subject, String html, String text, String threadId, String replyToEmailId, String emailId, String createdAt, String updatedAt) {
        this.object = object;
        this.id = id;
        this.type = type;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.html = html;
        this.text = text;
        this.threadId = threadId;
        this.replyToEmailId = replyToEmailId;
        this.emailId = emailId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
     * Get the HTML body.
     *
     * @return The HTML body.
     */
    public String getHtml() {
        return html;
    }

    /**
     * Get the plain text body.
     *
     * @return The plain text body.
     */
    public String getText() {
        return text;
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
     * Get the ID of the outbound email queued when the draft was sent. {@code null} until the draft is sent.
     *
     * @return The ID of the outbound email queued when the draft was sent. {@code null} until the draft is sent.
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * Get the timestamp when the draft was created.
     *
     * @return The timestamp when the draft was created.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Get the timestamp when the draft was last saved.
     *
     * @return The timestamp when the draft was last saved.
     */
    public String getUpdatedAt() {
        return updatedAt;
    }
}
