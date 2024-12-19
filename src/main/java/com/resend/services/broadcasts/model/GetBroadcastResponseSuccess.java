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

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("reply_to")
    private List<String> replyTo;

    @JsonProperty("preview_text")
    private String previewText;

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
     * @param subject Subject line of the broadcast.
     * @param replyTo Reply-to address for the broadcast.
     * @param previewText Preview text of the broadcast.
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
            String subject,
            List<String> replyTo,
            String previewText
    ) {
        super(id, audienceId, status, createdAt, scheduledAt, sentAt);
        this.object = object;
        this.name = name;
        this.from = from;
        this.subject = subject;
        this.replyTo = replyTo;
        this.previewText = previewText;
    }

    /**
     * @return Type of the object (e.g., "broadcast").
     */
    public String getObject() {
        return object;
    }

    /**
     * @return Name of the broadcast.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Sender of the broadcast.
     */
    public String getFrom() {
        return from;
    }

    /**
     * @return Subject line of the broadcast.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @return Reply-to addresses for the broadcast.
     */
    public List<String> getReplyTo() {
        return replyTo;
    }

    /**
     * @return Preview text of the broadcast.
     */
    public String getPreviewText() {
        return previewText;
    }
}

