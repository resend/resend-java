package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an attachment of a thread email.
 */
public class InboxThreadEmailAttachment {

    @JsonProperty("id")
    private String id;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("size")
    private Integer size;

    /**
     * Default constructor
     */
    public InboxThreadEmailAttachment() {

    }

    /**
     * Constructs a InboxThreadEmailAttachment.
     *
     * @param id       The ID of the attachment.
     * @param filename The filename of the attachment.
     * @param size     The size of the attachment in bytes.
     */
    public InboxThreadEmailAttachment(String id, String filename, Integer size) {
        this.id = id;
        this.filename = filename;
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
     * Get the size of the attachment in bytes.
     *
     * @return The size of the attachment in bytes.
     */
    public Integer getSize() {
        return size;
    }
}
