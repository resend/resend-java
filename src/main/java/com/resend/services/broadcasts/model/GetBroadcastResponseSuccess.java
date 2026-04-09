package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents the response for a successful broadcast retrieval, extending the Broadcast class.
 */
public class GetBroadcastResponseSuccess extends Broadcast {

    @JsonProperty("object")
    private String object;

    @JsonProperty("name")
    private String name;

    @JsonProperty("from")
    private String from;

    @JsonProperty("html")
    private String html;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("reply_to")
    private List<String> replyTo;

    @JsonProperty("preview_text")
    private String previewText;

    @JsonProperty("text")
    private String text;

    /**
     * Default constructor
     */
    public GetBroadcastResponseSuccess() {
    }

    /**
     * Constructs a new GetBroadcastResponseSuccess instance.
     *
     * @param id Unique identifier for the broadcast.
     * @param audienceId Identifier for the associated audience.
     * @param status Current status of the broadcast.
     * @param createdAt Timestamp when the broadcast was created.
     * @param scheduledAt Timestamp when the broadcast is scheduled, if any.
     * @param sentAt Timestamp when the broadcast was sent, if any.
     * @param object Type of the object (e.g., "broadcast").
     * @param name Name of the broadcast.
     * @param from Sender of the broadcast.
     * @param html The HTML content of the broadcast.
     * @param subject Subject line of the broadcast.
     * @param replyTo Reply-to address for the broadcast.
     * @param previewText Preview text of the broadcast.
     * @param text The plain text content of the broadcast.
     */
    public GetBroadcastResponseSuccess(
            String id,
            String audienceId,
            String status,
            String createdAt,
            String scheduledAt,
            String sentAt,
            String object,
            String name,
            String from,
            String html,
            String subject,
            List<String> replyTo,
            String previewText,
            String text
    ) {
        super(id, audienceId, status, createdAt, scheduledAt, sentAt);
        this.object = object;
        this.name = name;
        this.from = from;
        this.html = html;
        this.subject = subject;
        this.replyTo = replyTo;
        this.previewText = previewText;
        this.text = text;
    }

    /**
     * Gets the type of the object.
     *
     * @return the object type (e.g., "broadcast")
     */
    public String getObject() {
        return object;
    }

    /**
     * Gets the name of the broadcast.
     *
     * @return the broadcast name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the sender of the broadcast.
     *
     * @return the sender email address
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the HTML content of the broadcast.
     *
     * @return the HTML content
     */
    public String getHtml() {
        return html;
    }

    /**
     * Gets the subject line of the broadcast.
     *
     * @return the subject line
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Gets the reply-to addresses for the broadcast.
     *
     * @return the list of reply-to addresses
     */
    public List<String> getReplyTo() {
        return replyTo;
    }

    /**
     * Gets the preview text of the broadcast.
     *
     * @return the preview text
     */
    public String getPreviewText() {
        return previewText;
    }

    /**
     * Gets the plain text content of the broadcast.
     *
     * @return the plain text content
     */
    public String getText() {
        return text;
    }
}

