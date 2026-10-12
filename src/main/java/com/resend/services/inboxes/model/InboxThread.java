package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a thread as it appears in the list of an inbox's threads.
 */
public class InboxThread {

    @JsonProperty("id")
    private String id;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private List<String> to;

    @JsonProperty("cc")
    private List<String> cc;

    @JsonProperty("bcc")
    private List<String> bcc;

    @JsonProperty("labels")
    private List<InboxThreadLabel> labels;

    @JsonProperty("message_count")
    private Integer messageCount;

    @JsonProperty("has_attachment")
    private Boolean hasAttachment;

    @JsonProperty("has_draft")
    private Boolean hasDraft;

    @JsonProperty("read")
    private Boolean read;

    @JsonProperty("received_at")
    private String receivedAt;

    @JsonProperty("folder")
    private String folder;

    /**
     * Default constructor
     */
    public InboxThread() {

    }

    /**
     * Constructs a InboxThread.
     *
     * @param id            The ID of the thread.
     * @param subject       The subject of the thread.
     * @param from          The sender email address. Can include the display name.
     * @param to            The recipients of the most recent email. For a received email, these are the addresses it was delivered to, not the full {@code To} header.
     * @param cc            The CC recipients of the most recent email.
     * @param bcc           The BCC recipients of the most recent email. Always empty for a received email.
     * @param labels        The labels attached to the thread.
     * @param messageCount  The number of emails in the thread.
     * @param hasAttachment Whether the most recent email in the thread has an attachment.
     * @param hasDraft      Whether the thread has an unsent draft.
     * @param read          True only when every email in the thread is read.
     * @param receivedAt    The timestamp when the thread was last active.
     * @param folder        The folder the thread is in. Every thread is in exactly one folder.
     */
    public InboxThread(String id, String subject, String from, List<String> to, List<String> cc, List<String> bcc, List<InboxThreadLabel> labels, Integer messageCount, Boolean hasAttachment, Boolean hasDraft, Boolean read, String receivedAt, String folder) {
        this.id = id;
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.labels = labels;
        this.messageCount = messageCount;
        this.hasAttachment = hasAttachment;
        this.hasDraft = hasDraft;
        this.read = read;
        this.receivedAt = receivedAt;
        this.folder = folder;
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
     * Get the subject of the thread.
     *
     * @return The subject of the thread.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get the sender email address. Can include the display name.
     *
     * @return The sender email address. Can include the display name.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Get the recipients of the most recent email. For a received email, these are the addresses it was delivered to, not the full {@code To} header.
     *
     * @return The recipients of the most recent email. For a received email, these are the addresses it was delivered to, not the full {@code To} header.
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * Get the CC recipients of the most recent email.
     *
     * @return The CC recipients of the most recent email.
     */
    public List<String> getCc() {
        return cc;
    }

    /**
     * Get the BCC recipients of the most recent email. Always empty for a received email.
     *
     * @return The BCC recipients of the most recent email. Always empty for a received email.
     */
    public List<String> getBcc() {
        return bcc;
    }

    /**
     * Get the labels attached to the thread.
     *
     * @return The labels attached to the thread.
     */
    public List<InboxThreadLabel> getLabels() {
        return labels;
    }

    /**
     * Get the number of emails in the thread.
     *
     * @return The number of emails in the thread.
     */
    public Integer getMessageCount() {
        return messageCount;
    }

    /**
     * Get whether the most recent email in the thread has an attachment.
     *
     * @return Whether the most recent email in the thread has an attachment.
     */
    public Boolean getHasAttachment() {
        return hasAttachment;
    }

    /**
     * Get whether the thread has an unsent draft.
     *
     * @return Whether the thread has an unsent draft.
     */
    public Boolean getHasDraft() {
        return hasDraft;
    }

    /**
     * Get whether every email in the thread is read.
     *
     * @return True only when every email in the thread is read.
     */
    public Boolean getRead() {
        return read;
    }

    /**
     * Get the timestamp when the thread was last active.
     *
     * @return The timestamp when the thread was last active.
     */
    public String getReceivedAt() {
        return receivedAt;
    }

    /**
     * Get the folder the thread is in. Every thread is in exactly one folder.
     *
     * @return The folder the thread is in. Every thread is in exactly one folder.
     */
    public String getFolder() {
        return folder;
    }
}
