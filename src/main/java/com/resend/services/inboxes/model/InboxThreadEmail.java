package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a message in a thread.
 */
public class InboxThreadEmail {

    @JsonProperty("id")
    private String id;

    @JsonProperty("direction")
    private String direction;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private List<String> to;

    @JsonProperty("cc")
    private List<String> cc;

    @JsonProperty("bcc")
    private List<String> bcc;

    @JsonProperty("reply_to")
    private List<String> replyTo;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("html")
    private String html;

    @JsonProperty("text")
    private String text;

    @JsonProperty("attachments")
    private List<InboxThreadEmailAttachment> attachments;

    @JsonProperty("read")
    private Boolean read;

    @JsonProperty("received_at")
    private String receivedAt;

    /**
     * Default constructor
     */
    public InboxThreadEmail() {

    }

    /**
     * Constructs a InboxThreadEmail.
     *
     * @param id          The ID of the message.
     * @param direction   Whether the inbox received the message ({@code inbound}) or sent it ({@code outbound}). A message the inbox sent to itself is {@code inbound}.
     * @param from        The sender email address. Includes the display name when the sender has one.
     * @param to          The recipients of the message. For a received message, these are the addresses it was delivered to, not the full {@code To} header.
     * @param cc          The CC recipients of the message.
     * @param bcc         The BCC recipients of the message. Always empty for a received message.
     * @param replyTo     The Reply-To addresses of the message.
     * @param subject     The subject of the message.
     * @param messageId   The Message-ID header of the message.
     * @param html        The HTML body.
     * @param text        The plain-text body.
     * @param attachments The attachments of the message.
     * @param read        Whether the message has been read.
     * @param receivedAt  The timestamp when the inbox received the message, or when the inbox sent it.
     */
    public InboxThreadEmail(String id, String direction, String from, List<String> to, List<String> cc, List<String> bcc, List<String> replyTo, String subject, String messageId, String html, String text, List<InboxThreadEmailAttachment> attachments, Boolean read, String receivedAt) {
        this.id = id;
        this.direction = direction;
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.replyTo = replyTo;
        this.subject = subject;
        this.messageId = messageId;
        this.html = html;
        this.text = text;
        this.attachments = attachments;
        this.read = read;
        this.receivedAt = receivedAt;
    }

    /**
     * Get the ID of the message.
     *
     * @return The ID of the message.
     */
    public String getId() {
        return id;
    }

    /**
     * Get whether the inbox received the message ({@code inbound}) or sent it ({@code outbound}). A message the inbox sent to itself is {@code inbound}.
     *
     * @return Whether the inbox received the message ({@code inbound}) or sent it ({@code outbound}). A message the inbox sent to itself is {@code inbound}.
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Get the sender email address. Includes the display name when the sender has one.
     *
     * @return The sender email address. Includes the display name when the sender has one.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Get the recipients of the message. For a received message, these are the addresses it was delivered to, not the full {@code To} header.
     *
     * @return The recipients of the message. For a received message, these are the addresses it was delivered to, not the full {@code To} header.
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * Get the CC recipients of the message.
     *
     * @return The CC recipients of the message.
     */
    public List<String> getCc() {
        return cc;
    }

    /**
     * Get the BCC recipients of the message. Always empty for a received message.
     *
     * @return The BCC recipients of the message. Always empty for a received message.
     */
    public List<String> getBcc() {
        return bcc;
    }

    /**
     * Get the Reply-To addresses of the message.
     *
     * @return The Reply-To addresses of the message.
     */
    public List<String> getReplyTo() {
        return replyTo;
    }

    /**
     * Get the subject of the message.
     *
     * @return The subject of the message.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get the Message-ID header of the message.
     *
     * @return The Message-ID header of the message.
     */
    public String getMessageId() {
        return messageId;
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
     * Get the plain-text body.
     *
     * @return The plain-text body.
     */
    public String getText() {
        return text;
    }

    /**
     * Get the attachments of the message.
     *
     * @return The attachments of the message.
     */
    public List<InboxThreadEmailAttachment> getAttachments() {
        return attachments;
    }

    /**
     * Get whether the message has been read.
     *
     * @return Whether the message has been read.
     */
    public Boolean getRead() {
        return read;
    }

    /**
     * Get the timestamp when the inbox received the message, or when the inbox sent it.
     *
     * @return The timestamp when the inbox received the message, or when the inbox sent it.
     */
    public String getReceivedAt() {
        return receivedAt;
    }
}
