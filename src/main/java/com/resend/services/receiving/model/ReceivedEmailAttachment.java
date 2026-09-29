package com.resend.services.receiving.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents an attachment in a received email (summary view).
 */
public class ReceivedEmailAttachment {

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

    @JsonProperty("size")
    private @Nullable Integer size;

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
     * Gets the attachment size in bytes.
     *
     * @return The attachment size.
     */
    public @Nullable Integer getSize() {
        return size;
    }

    /**
     * Sets the attachment size.
     *
     * @param size The attachment size in bytes.
     */
    public void setSize(@Nullable Integer size) {
        this.size = size;
    }
}
