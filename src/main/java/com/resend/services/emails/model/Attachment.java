package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.resend.core.util.ByteArraySerializer;

/**
 * Represents an attachment associated with an email.
 */
public class Attachment {
    @JsonProperty("filename")
    private final String fileName;

    @JsonProperty("content")
    @JsonSerialize(using = ByteArraySerializer.class)
    private final byte[] content;

    @JsonProperty("path")
    private final String path;

    private Attachment(Builder builder) {
        this.fileName = builder.fileName;
        this.content = builder.content;
        this.path = builder.path;
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
    public byte[] getContent() {
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
        private byte[] content;
        private String path;

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
        public Builder content(byte[] content) {
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
         * Build an Attachment instance.
         * @return The built Attachment.
         */
        public Attachment build() {
            return new Attachment(this);
        }
    }
}

