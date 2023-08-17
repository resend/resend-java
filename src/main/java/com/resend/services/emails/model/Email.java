package com.resend.services.emails.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.resend.core.util.DateTimeDeserializer;
import com.resend.core.util.DateTimeSerializer;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents an email.
 */
public class Email {
    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private List<String> to;

    @JsonProperty("created_at")
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private OffsetDateTime createdAt;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("html")
    private String html;

    @JsonProperty("text")
    private String text;

    @JsonProperty("bcc")
    private List<String> bcc;

    @JsonProperty("cc")
    private List<String> cc;

    @JsonProperty("reply_to")
    private List<String> replyTo;

    @JsonProperty("last_event")
    private String lastEvent;

    /**
     * Default constructor.
     */
    public Email() {
    }

    /**
     * Constructs an Email object with the provided attributes.
     *
     * @param object     The object attribute value.
     * @param id         The id attribute value.
     * @param from       The from attribute value.
     * @param to         The to attribute value.
     * @param createdAt  The created_at attribute value.
     * @param subject    The subject attribute value.
     * @param html       The html attribute value.
     * @param text       The text attribute value.
     * @param bcc        The bcc attribute value.
     * @param cc         The cc attribute value.
     * @param replyTo    The reply_to attribute value.
     * @param lastEvent  The last_event attribute value.
     */
    public Email(String object, String id, String from, List<String> to, OffsetDateTime createdAt, String subject,
                 String html, String text, List<String> bcc, List<String> cc, List<String> replyTo, String lastEvent) {
        this.object = object;
        this.id = id;
        this.from = from;
        this.to = to;
        this.createdAt = createdAt;
        this.subject = subject;
        this.html = html;
        this.text = text;
        this.bcc = bcc;
        this.cc = cc;
        this.replyTo = replyTo;
        this.lastEvent = lastEvent;
    }

    /**
     * Get the "object" attribute of the email.
     * @return The object attribute.
     */
    public String getObject() {
        return object;
    }

    /**
     * Set the "object" attribute of the email.
     * @param object The object attribute.
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Get the "id" attribute of the email.
     * @return The id attribute.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the "id" attribute of the email.
     * @param id The id attribute.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the "from" attribute of the email.
     * @return The from attribute.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Set the "from" attribute of the email.
     * @param from The from attribute.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Get the list of "to" recipients of the email.
     * @return The list of "to" recipients.
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * Set the list of "to" recipients of the email.
     * @param to The list of "to" recipients.
     */
    public void setTo(List<String> to) {
        this.to = to;
    }

    /**
     * Get the creation timestamp of the email.
     * @return The creation timestamp.
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the creation timestamp of the email.
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Get the subject of the email.
     * @return The email subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Set the subject of the email.
     * @param subject The email subject.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Get the HTML content of the email.
     * @return The HTML content.
     */
    public String getHtml() {
        return html;
    }

    /**
     * Set the HTML content of the email.
     * @param html The HTML content.
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * Get the plain text content of the email.
     * @return The plain text content.
     */
    public String getText() {
        return text;
    }

    /**
     * Set the plain text content of the email.
     * @param text The plain text content.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get the list of "bcc" recipients of the email.
     * @return The list of "bcc" recipients.
     */
    public List<String> getBcc() {
        return bcc;
    }

    /**
     * Set the list of "bcc" recipients of the email.
     * @param bcc The list of "bcc" recipients.
     */
    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    /**
     * Get the list of "cc" recipients of the email.
     * @return The list of "cc" recipients.
     */
    public List<String> getCc() {
        return cc;
    }

    /**
     * Set the list of "cc" recipients of the email.
     * @param cc The list of "cc" recipients.
     */
    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    /**
     * Get the list of "reply_to" recipients of the email.
     * @return The list of "reply_to" recipients.
     */
    public List<String> getReplyTo() {
        return replyTo;
    }

    /**
     * Set the list of "reply_to" recipients of the email.
     * @param replyTo The list of "reply_to" recipients.
     */
    public void setReplyTo(List<String> replyTo) {
        this.replyTo = replyTo;
    }


    /**
     * Get the "last_event" attribute of the email.
     * @return The last event attribute.
     */
    public String getLastEvent() {
        return lastEvent;
    }

    /**
     * Set the "last_event" attribute of the email.
     * @param lastEvent The last event attribute.
     */
    public void setLastEvent(String lastEvent) {
        this.lastEvent = lastEvent;
    }
}

