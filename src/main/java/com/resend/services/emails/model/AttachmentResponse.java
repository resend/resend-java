package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response when retrieving an attachment from a sent email.
 */
public class AttachmentResponse {

    /**
     * The object type, always "attachment".
     */
    @JsonProperty("object")
    private String object;

    /**
     * The unique identifier of the attachment.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The filename of the attachment.
     */
    @JsonProperty("filename")
    private String filename;

    /**
     * The size of the attachment in bytes.
     */
    @JsonProperty("size")
    private Integer size;

    /**
     * The MIME content type of the attachment.
     */
    @JsonProperty("content_type")
    private String contentType;

    /**
     * The content disposition (inline or attachment).
     */
    @JsonProperty("content_disposition")
    private String contentDisposition;

    /**
     * The content ID for inline attachments.
     */
    @JsonProperty("content_id")
    private String contentId;

    /**
     * The download URL for the attachment.
     */
    @JsonProperty("download_url")
    private String downloadUrl;

    /**
     * The expiration timestamp of the download URL.
     */
    @JsonProperty("expires_at")
    private String expiresAt;

    /**
     * Get the object type.
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Set the object type.
     * @param object The object type.
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Get the attachment ID.
     * @return The attachment ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the attachment ID.
     * @param id The attachment ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the filename.
     * @return The filename.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Set the filename.
     * @param filename The filename.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Get the size in bytes.
     * @return The size.
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Set the size.
     * @param size The size in bytes.
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Get the content type.
     * @return The content type.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Set the content type.
     * @param contentType The content type.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Get the content disposition.
     * @return The content disposition.
     */
    public String getContentDisposition() {
        return contentDisposition;
    }

    /**
     * Set the content disposition.
     * @param contentDisposition The content disposition.
     */
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    /**
     * Get the content ID.
     * @return The content ID.
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * Set the content ID.
     * @param contentId The content ID.
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * Get the download URL.
     * @return The download URL.
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * Set the download URL.
     * @param downloadUrl The download URL.
     */
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    /**
     * Get the expiration timestamp.
     * @return The expiration timestamp.
     */
    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * Set the expiration timestamp.
     * @param expiresAt The expiration timestamp.
     */
    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }
}
