package com.resend.services.receiving.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * Represents a summary of a received inbound email (used in list responses).
 * This class omits the html, text, and headers fields which are only available
 * when fetching a specific email by ID.
 */
public class ReceivedEmailSummary {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("to")
    private @Nullable List<String> to;

    @JsonProperty("from")
    private @Nullable String from;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("subject")
    private @Nullable String subject;

    @JsonProperty("bcc")
    private @Nullable List<String> bcc;

    @JsonProperty("cc")
    private @Nullable List<String> cc;

    @JsonProperty("reply_to")
    private @Nullable List<String> replyTo;

    @JsonProperty("message_id")
    private @Nullable String messageId;

    @JsonProperty("attachments")
    private @Nullable List<ReceivedEmailAttachment> attachments;

    /**
     * Default constructor.
     */
    public ReceivedEmailSummary() {
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(@Nullable String object) {
        this.object = object;
    }

    /**
     * Gets the email ID.
     *
     * @return The email ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the email ID.
     *
     * @param id The email ID.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }

    /**
     * Gets the recipient addresses.
     *
     * @return The list of recipient addresses.
     */
    public @Nullable List<String> getTo() {
        return to;
    }

    /**
     * Sets the recipient addresses.
     *
     * @param to The list of recipient addresses.
     */
    public void setTo(@Nullable List<String> to) {
        this.to = to;
    }

    /**
     * Gets the sender address.
     *
     * @return The sender address.
     */
    public @Nullable String getFrom() {
        return from;
    }

    /**
     * Sets the sender address.
     *
     * @param from The sender address.
     */
    public void setFrom(@Nullable String from) {
        this.from = from;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(@Nullable String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the email subject.
     *
     * @return The email subject.
     */
    public @Nullable String getSubject() {
        return subject;
    }

    /**
     * Sets the email subject.
     *
     * @param subject The email subject.
     */
    public void setSubject(@Nullable String subject) {
        this.subject = subject;
    }

    /**
     * Gets the BCC addresses.
     *
     * @return The list of BCC addresses.
     */
    public @Nullable List<String> getBcc() {
        return bcc;
    }

    /**
     * Sets the BCC addresses.
     *
     * @param bcc The list of BCC addresses.
     */
    public void setBcc(@Nullable List<String> bcc) {
        this.bcc = bcc;
    }

    /**
     * Gets the CC addresses.
     *
     * @return The list of CC addresses.
     */
    public @Nullable List<String> getCc() {
        return cc;
    }

    /**
     * Sets the CC addresses.
     *
     * @param cc The list of CC addresses.
     */
    public void setCc(@Nullable List<String> cc) {
        this.cc = cc;
    }

    /**
     * Gets the reply-to addresses.
     *
     * @return The list of reply-to addresses.
     */
    public @Nullable List<String> getReplyTo() {
        return replyTo;
    }

    /**
     * Sets the reply-to addresses.
     *
     * @param replyTo The list of reply-to addresses.
     */
    public void setReplyTo(@Nullable List<String> replyTo) {
        this.replyTo = replyTo;
    }

    /**
     * Gets the message ID.
     *
     * @return The message ID.
     */
    public @Nullable String getMessageId() {
        return messageId;
    }

    /**
     * Sets the message ID.
     *
     * @param messageId The message ID.
     */
    public void setMessageId(@Nullable String messageId) {
        this.messageId = messageId;
    }

    /**
     * Gets the list of attachments.
     *
     * @return The list of attachments.
     */
    public @Nullable List<ReceivedEmailAttachment> getAttachments() {
        return attachments;
    }

    /**
     * Sets the list of attachments.
     *
     * @param attachments The list of attachments.
     */
    public void setAttachments(@Nullable List<ReceivedEmailAttachment> attachments) {
        this.attachments = attachments;
    }
}
