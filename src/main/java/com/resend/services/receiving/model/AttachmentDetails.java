package com.resend.services.receiving.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents detailed information about an attachment including download URL.
 */
public class AttachmentDetails {

    @JsonProperty("object")
    private String object;

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

    @JsonProperty("download_url")
    private String downloadUrl;

    @JsonProperty("expires_at")
    private String expiresAt;

    /**
     * Default constructor.
     */
    public AttachmentDetails() {
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(String object) {
        this.object = object;
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
     * Gets the download URL.
     *
     * @return The download URL.
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * Sets the download URL.
     *
     * @param downloadUrl The download URL.
     */
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    /**
     * Gets the expiration timestamp.
     *
     * @return The expiration timestamp.
     */
    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * Sets the expiration timestamp.
     *
     * @param expiresAt The expiration timestamp.
     */
    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }
}
