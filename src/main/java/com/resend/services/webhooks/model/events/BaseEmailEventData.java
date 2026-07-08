package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Base data payload for outbound email webhook events.
 */
public class BaseEmailEventData {

    @JsonProperty("broadcast_id")
    private String broadcastId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private List<String> to;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty("tags")
    private Map<String, String> tags;

    /**
     * Default constructor.
     */
    public BaseEmailEventData() {
    }

    /**
     * Gets the broadcast ID.
     *
     * @return The broadcast ID.
     */
    public String getBroadcastId() {
        return broadcastId;
    }

    /**
     * Sets the broadcast ID.
     *
     * @param broadcastId The broadcast ID.
     */
    public void setBroadcastId(String broadcastId) {
        this.broadcastId = broadcastId;
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
     * Gets the template ID.
     *
     * @return The template ID.
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * Sets the template ID.
     *
     * @param templateId The template ID.
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    /**
     * Gets the email tags.
     *
     * @return The email tags.
     */
    public Map<String, String> getTags() {
        return tags;
    }

    /**
     * Sets the email tags.
     *
     * @param tags The email tags.
     */
    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
