package com.resend.services.inboxes.model;

import java.util.List;

/**
 * Represents a successful response for retrieving a thread email.
 * Extends the InboxThreadEmail class.
 */
public class GetInboxThreadEmailResponseSuccess extends InboxThreadEmail {

    /**
     * Default constructor
     */
    public GetInboxThreadEmailResponseSuccess() {

    }

    /**
     * Constructs a GetInboxThreadEmailResponseSuccess.
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
    public GetInboxThreadEmailResponseSuccess(String id, String direction, String from, List<String> to, List<String> cc, List<String> bcc, List<String> replyTo, String subject, String messageId, String html, String text, List<InboxThreadEmailAttachment> attachments, Boolean read, String receivedAt) {
        super(id, direction, from, to, cc, bcc, replyTo, subject, messageId, html, text, attachments, read, receivedAt);
    }
}
