package com.resend.services.receiving.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an attachment in a received email (summary view).
 */
public class ReceivedEmailAttachment {

    @JsonProperty("id")
    private String id;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("content_type")
    private String contentType;

    @JsonProperty("content_disposition")
    private String contentDisposition;

    @JsonProperty("content_id")
    private String contentId;

    @JsonProperty("size")
    private Long size;

    /**
     * Default constructor.
     */
    public ReceivedEmailAttachment() {
    }

    /**
     * Gets the attachment ID.
     *
     * @return The attachment ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the attachment ID.
     *
     * @param id The attachment ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the filename.
     *
     * @return The filename.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the filename.
     *
     * @param filename The filename.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Gets the content type.
     *
     * @return The content type.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the content type.
     *
     * @param contentType The content type.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets the content disposition.
     *
     * @return The content disposition.
     */
    public String getContentDisposition() {
        return contentDisposition;
    }

    /**
     * Sets the content disposition.
     *
     * @param contentDisposition The content disposition.
     */
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    /**
     * Gets the content ID.
     *
     * @return The content ID.
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * Sets the content ID.
     *
     * @param contentId The content ID.
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * Gets the attachment size in bytes.
     *
     * @return The attachment size.
     */
    public Long getSize() {
        return size;
    }

    /**
     * Sets the attachment size.
     *
     * @param size The attachment size in bytes.
     */
    public void setSize(Long size) {
        this.size = size;
    }
}
