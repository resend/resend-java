package com.resend.services.receiving.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents detailed information about an attachment including download URL.
 */
public class AttachmentDetails {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("filename")
    private @Nullable String filename;

    @JsonProperty("content_type")
    private @Nullable String contentType;

    @JsonProperty("content_disposition")
    private @Nullable String contentDisposition;

    @JsonProperty("content_id")
    private @Nullable String contentId;

    @JsonProperty("download_url")
    private @Nullable String downloadUrl;

    @JsonProperty("expires_at")
    private @Nullable String expiresAt;

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
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(@Nullable String object) {
        this.object = object;
    }

    /**
     * Gets the attachment ID.
     *
     * @return The attachment ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the attachment ID.
     *
     * @param id The attachment ID.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }

    /**
     * Gets the filename.
     *
     * @return The filename.
     */
    public @Nullable String getFilename() {
        return filename;
    }

    /**
     * Sets the filename.
     *
     * @param filename The filename.
     */
    public void setFilename(@Nullable String filename) {
        this.filename = filename;
    }

    /**
     * Gets the content type.
     *
     * @return The content type.
     */
    public @Nullable String getContentType() {
        return contentType;
    }

    /**
     * Sets the content type.
     *
     * @param contentType The content type.
     */
    public void setContentType(@Nullable String contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets the content disposition.
     *
     * @return The content disposition.
     */
    public @Nullable String getContentDisposition() {
        return contentDisposition;
    }

    /**
     * Sets the content disposition.
     *
     * @param contentDisposition The content disposition.
     */
    public void setContentDisposition(@Nullable String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    /**
     * Gets the content ID.
     *
     * @return The content ID.
     */
    public @Nullable String getContentId() {
        return contentId;
    }

    /**
     * Sets the content ID.
     *
     * @param contentId The content ID.
     */
    public void setContentId(@Nullable String contentId) {
        this.contentId = contentId;
    }

    /**
     * Gets the download URL.
     *
     * @return The download URL.
     */
    public @Nullable String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * Sets the download URL.
     *
     * @param downloadUrl The download URL.
     */
    public void setDownloadUrl(@Nullable String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    /**
     * Gets the expiration timestamp.
     *
     * @return The expiration timestamp.
     */
    public @Nullable String getExpiresAt() {
        return expiresAt;
    }

    /**
     * Sets the expiration timestamp.
     *
     * @param expiresAt The expiration timestamp.
     */
    public void setExpiresAt(@Nullable String expiresAt) {
        this.expiresAt = expiresAt;
    }
}
