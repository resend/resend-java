package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an attachment associated with an email.
 */
public class Attachment {
    @JsonProperty("filename")
    private final String fileName;

    @JsonProperty("content")
    private final String content;

    @JsonProperty("path")
    private final String path;

    @JsonProperty("inline_content_id")
    private final String inlineContentId;

    private Attachment(Builder builder) {
        this.fileName = builder.fileName;
        this.content = builder.content;
        this.path = builder.path;
        this.inlineContentId = builder.inlineContentId;
    }

    /**
     * Get the filename of the attachment.
     * @return The filename.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Get the content of the attachment as a byte array.
     * @return The content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Get the path of the attachment.
     * @return The path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Get the content ID for inline attachments used in HTML content with cid: references.
     * @return The content ID for inline attachments.
     */
    public String getInlineContentId() {
        return inlineContentId;
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
        private String fileName;
        private String content;
        private String path;
        private String inlineContentId;

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
         * Set the content ID for inline attachments used in HTML content with cid: references.
         * @param inlineContentId The content ID for inline attachments.
         * @return The Builder instance.
         */
        public Builder inlineContentId(String inlineContentId) {
            this.inlineContentId = inlineContentId;
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

