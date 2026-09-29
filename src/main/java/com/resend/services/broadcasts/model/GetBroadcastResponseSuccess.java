package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents the response for a successful broadcast retrieval, extending the Broadcast class.
 */
public class GetBroadcastResponseSuccess extends Broadcast {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("name")
    private @Nullable String name;

    @JsonProperty("from")
    private @Nullable String from;

    @JsonProperty("html")
    private @Nullable String html;

    @JsonProperty("subject")
    private @Nullable String subject;

    @JsonProperty("reply_to")
    private @Nullable List<String> replyTo;

    @JsonProperty("preview_text")
    private @Nullable String previewText;

    @JsonProperty("text")
    private @Nullable String text;

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
            @Nullable String id,
            @Nullable String audienceId,
            @Nullable String status,
            @Nullable String createdAt,
            @Nullable String scheduledAt,
            @Nullable String sentAt,
            @Nullable String object,
            @Nullable String name,
            @Nullable String from,
            @Nullable String html,
            @Nullable String subject,
            @Nullable List<String> replyTo,
            @Nullable String previewText,
            @Nullable String text
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
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Gets the name of the broadcast.
     *
     * @return the broadcast name
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Gets the sender of the broadcast.
     *
     * @return the sender email address
     */
    public @Nullable String getFrom() {
        return from;
    }

    /**
     * Gets the HTML content of the broadcast.
     *
     * @return the HTML content
     */
    public @Nullable String getHtml() {
        return html;
    }

    /**
     * Gets the subject line of the broadcast.
     *
     * @return the subject line
     */
    public @Nullable String getSubject() {
        return subject;
    }

    /**
     * Gets the reply-to addresses for the broadcast.
     *
     * @return the list of reply-to addresses
     */
    public @Nullable List<String> getReplyTo() {
        return replyTo;
    }

    /**
     * Gets the preview text of the broadcast.
     *
     * @return the preview text
     */
    public @Nullable String getPreviewText() {
        return previewText;
    }

    /**
     * Gets the plain text content of the broadcast.
     *
     * @return the plain text content
     */
    public @Nullable String getText() {
        return text;
    }
}

