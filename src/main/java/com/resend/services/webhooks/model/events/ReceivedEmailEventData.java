package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.receiving.model.ReceivedEmailAttachment;
import java.util.List;

/**
 * Data payload for the {@code email.received} webhook event.
 */
public class ReceivedEmailEventData {

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private List<String> to;

    @JsonProperty("bcc")
    private List<String> bcc;

    @JsonProperty("cc")
    private List<String> cc;

    @JsonProperty("received_for")
    private List<String> receivedFor;

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("attachments")
    private List<ReceivedEmailAttachment> attachments;

    /**
     * Default constructor.
     */
    public ReceivedEmailEventData() {
    }

    /**
     * Gets the email ID.
     *
     * @return The email ID.
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * Sets the email ID.
     *
     * @param emailId The email ID.
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the sender email address.
     *
     * @return The sender email address.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the sender email address.
     *
     * @param from The sender email address.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Gets the recipient email addresses.
     *
     * @return The recipient email addresses.
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * Sets the recipient email addresses.
     *
     * @param to The recipient email addresses.
     */
    public void setTo(List<String> to) {
        this.to = to;
    }

    /**
     * Gets the BCC recipient email addresses.
     *
     * @return The BCC recipient email addresses.
     */
    public List<String> getBcc() {
        return bcc;
    }

    /**
     * Sets the BCC recipient email addresses.
     *
     * @param bcc The BCC recipient email addresses.
     */
    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    /**
     * Gets the CC recipient email addresses.
     *
     * @return The CC recipient email addresses.
     */
    public List<String> getCc() {
        return cc;
    }

    /**
     * Sets the CC recipient email addresses.
     *
     * @param cc The CC recipient email addresses.
     */
    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    /**
     * Gets the addresses the email was received for.
     *
     * @return The received-for addresses.
     */
    public List<String> getReceivedFor() {
        return receivedFor;
    }

    /**
     * Sets the addresses the email was received for.
     *
     * @param receivedFor The received-for addresses.
     */
    public void setReceivedFor(List<String> receivedFor) {
        this.receivedFor = receivedFor;
    }

    /**
     * Gets the RFC Message-ID header value.
     *
     * @return The message ID.
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Sets the RFC Message-ID header value.
     *
     * @param messageId The message ID.
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * Gets the email subject.
     *
     * @return The email subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the email subject.
     *
     * @param subject The email subject.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the email attachments.
     *
     * @return The email attachments.
     */
    public List<ReceivedEmailAttachment> getAttachments() {
        return attachments;
    }

    /**
     * Sets the email attachments.
     *
     * @param attachments The email attachments.
     */
    public void setAttachments(List<ReceivedEmailAttachment> attachments) {
        this.attachments = attachments;
    }
}
