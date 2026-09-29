package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents an attachment associated with an email.
 */
public class Attachment {
    @JsonProperty("filename")
    private final @Nullable String fileName;

    @JsonProperty("content")
    private final @Nullable String content;

    @JsonProperty("path")
    private final @Nullable String path;

    @JsonProperty("content_type")
    private final @Nullable String contentType;

    @JsonProperty("content_id")
    private final @Nullable String contentId;

    private Attachment(Builder builder) {
        this.fileName = builder.fileName;
        this.content = builder.content;
        this.path = builder.path;
        this.contentType = builder.contentType;
        this.contentId = builder.contentId;
    }

    /**
     * Get the filename of the attachment.
     * @return The filename.
     */
    public @Nullable String getFileName() {
        return fileName;
    }

    /**
     * Get the content of the attachment as a byte array.
     * @return The content.
     */
    public @Nullable String getContent() {
        return content;
    }

    /**
     * Get the path of the attachment.
     * @return The path.
     */
    public @Nullable String getPath() {
        return path;
    }

    /**
     * Get the content type of the attachment.
     * @return The content type.
     */
    public @Nullable String getContentType() {
        return contentType;
    }

    /**
     * Get the content ID for inline attachments used in HTML content with cid: references.
     * @return The content ID for inline attachments.
     */
    public @Nullable String getContentId() {
        return contentId;
    }

    /**
     * Create a new Attachment builder.
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for creating Attachment instances.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private @Nullable String fileName;
        private @Nullable String content;
        private @Nullable String path;

        private @Nullable String contentType;
        private @Nullable String contentId;

        /**
         * Set the filename of the attachment.
         * @param fileName The filename.
         * @return The Builder instance.
         */
        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        /**
         * Set the content of the attachment.
         * @param content The content as a byte array.
         * @return The Builder instance.
         */
        public Builder content(String content) {
            this.content = content;
            return this;
        }

        /**
         * Set the path of the attachment.
         * @param path The path.
         * @return The Builder instance.
         */
        public Builder path(String path) {
            this.path = path;
            return this;
        }

        /**
         * Set the content type of the attachment.
         * @param contentType The content type.
         * @return The Builder instance.
         */
        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        /**
         * Set the content ID for inline attachments used in HTML content with cid: references.
         * @param contentId The content ID for inline attachments.
         * @return The Builder instance.
         */
        public Builder contentId(String contentId) {
            this.contentId = contentId;
            return this;
        }

        /**
         * Build an Attachment instance.
         * @return The built Attachment.
         */
        public Attachment build() {
            return new Attachment(this);
        }
    }
}

