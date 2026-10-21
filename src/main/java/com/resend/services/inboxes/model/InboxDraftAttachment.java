package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a file stored on a draft.
 */
public class InboxDraftAttachment {

    @JsonProperty("id")
    private String id;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("content_type")
    private String contentType;

    @JsonProperty("size")
    private Integer size;

    /**
     * Default constructor
     */
    public InboxDraftAttachment() {

    }

    /**
     * Constructs a InboxDraftAttachment.
     *
     * @param id          The ID of the attachment.
     * @param filename    The filename of the attachment.
     * @param contentType The MIME type of the attachment.
     * @param size        The size of the attachment in bytes.
     */
    public InboxDraftAttachment(String id, String filename, String contentType, Integer size) {
        this.id = id;
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
    }

    /**
     * Get the ID of the attachment.
     *
     * @return The ID of the attachment.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the filename of the attachment.
     *
     * @return The filename of the attachment.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Get the MIME type of the attachment.
     *
     * @return The MIME type of the attachment.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Get the size of the attachment in bytes.
     *
     * @return The size of the attachment in bytes.
     */
    public Integer getSize() {
        return size;
    }
}
