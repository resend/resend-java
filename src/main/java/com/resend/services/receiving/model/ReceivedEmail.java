package com.resend.services.receiving.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import java.util.List;
import java.util.Map;

/**
 * Represents a received inbound email.
 */
public class ReceivedEmail {

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

    @JsonProperty("html")
    private @Nullable String html;

    @JsonProperty("text")
    private @Nullable String text;

    @JsonProperty("headers")
    private @Nullable Map<String, String> headers;

    @JsonProperty("bcc")
    private @Nullable List<String> bcc;

    @JsonProperty("cc")
    private @Nullable List<String> cc;

    @JsonProperty("reply_to")
    private @Nullable List<String> replyTo;

    @JsonProperty("received_for")
    private @Nullable List<String> receivedFor;

    @JsonProperty("message_id")
    private @Nullable String messageId;

    @JsonProperty("attachments")
    private @Nullable List<ReceivedEmailAttachment> attachments;

    /**
     * Default constructor.
     */
    public ReceivedEmail() {
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
     * Gets the HTML content.
     *
     * @return The HTML content.
     */
    public @Nullable String getHtml() {
        return html;
    }

    /**
     * Sets the HTML content.
     *
     * @param html The HTML content.
     */
    public void setHtml(@Nullable String html) {
        this.html = html;
    }

    /**
     * Gets the plain text content.
     *
     * @return The plain text content.
     */
    public @Nullable String getText() {
        return text;
    }

    /**
     * Sets the plain text content.
     *
     * @param text The plain text content.
     */
    public void setText(@Nullable String text) {
        this.text = text;
    }

    /**
     * Gets the email headers.
     *
     * @return The map of email headers.
     */
    public @Nullable Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets the email headers.
     *
     * @param headers The map of email headers.
     */
    public void setHeaders(@Nullable Map<String, String> headers) {
        this.headers = headers;
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
     * Gets the addresses the email was received for (forwarding recipients).
     *
     * @return The list of addresses the email was received for.
     */
    public @Nullable List<String> getReceivedFor() {
        return receivedFor;
    }

    /**
     * Sets the addresses the email was received for (forwarding recipients).
     *
     * @param receivedFor The list of addresses the email was received for.
     */
    public void setReceivedFor(@Nullable List<String> receivedFor) {
        this.receivedFor = receivedFor;
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
