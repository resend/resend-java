package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the options for creating a contact import.
 *
 * <p>The request is sent as {@code multipart/form-data}. The file part is required; all
 * other fields are optional. Object and array fields ({@code columnMap}, {@code segments},
 * {@code topics}) are serialized to JSON strings by the SDK before being attached to the
 * multipart body.</p>
 *
 * <p>The file can be supplied via {@link Builder#file(File)},
 * {@link Builder#file(byte[], String)}, or {@link Builder#file(InputStream, String)}.</p>
 *
 * <p><strong>Note:</strong> Contact Imports is currently in beta and may change before GA.</p>
 */
public class CreateContactImportOptions {

    /**
     * Maximum file size accepted by the Resend API for a contact import (50MB).
     */
    public static final int MAX_FILE_SIZE_BYTES = 50 * 1024 * 1024;

    @JsonIgnore
    private final File file;

    @JsonIgnore
    private final byte[] fileBytes;

    @JsonIgnore
    private final String fileName;

    @JsonIgnore
    private final ContactImportColumnMap columnMap;

    @JsonIgnore
    private final String onConflict;

    @JsonIgnore
    private final List<ContactImportSegmentReference> segments;

    @JsonIgnore
    private final List<ContactImportTopicSubscription> topics;

    /**
     * Constructs a CreateContactImportOptions object using the provided builder.
     *
     * @param builder The builder to construct the options.
     */
    public CreateContactImportOptions(Builder builder) {
        this.file = builder.file;
        this.fileBytes = builder.fileBytes == null ? null : builder.fileBytes.clone();
        this.fileName = builder.fileName;
        this.columnMap = builder.columnMap;
        this.onConflict = builder.onConflict;
        this.segments = builder.segments;
        this.topics = builder.topics;
    }

    /**
     * Gets the CSV file to import, when provided as a {@link File}.
     *
     * @return The CSV file, or {@code null} if the file was supplied as bytes / stream.
     */
    public File getFile() {
        return file;
    }

    /**
     * Gets the CSV file content, when provided as bytes (or read from an {@link InputStream}).
     *
     * @return The file bytes, or {@code null} if the file was supplied as a {@link File}.
     */
    public byte[] getFileBytes() {
        return fileBytes == null ? null : fileBytes.clone();
    }

    /**
     * Gets the file name to use for the multipart part.
     *
     * <p>Falls back to {@code file.getName()} when only a {@link File} was supplied.</p>
     *
     * @return The file name.
     */
    public String getFileName() {
        if (fileName != null) {
            return fileName;
        }
        return file != null ? file.getName() : null;
    }

    /**
     * Gets the mapping between CSV columns and contact fields.
     *
     * @return The column map, or {@code null} if none.
     */
    public ContactImportColumnMap getColumnMap() {
        return columnMap;
    }

    /**
     * Gets the conflict-handling strategy ({@code "upsert"} or {@code "skip"}).
     *
     * @return The conflict-handling strategy, or {@code null} if defaulted by the API.
     */
    public String getOnConflict() {
        return onConflict;
    }

    /**
     * Gets the segments that imported contacts will be added to.
     *
     * @return The list of segment references, or {@code null} if none.
     */
    public List<ContactImportSegmentReference> getSegments() {
        return segments;
    }

    /**
     * Gets the topic subscriptions to apply to imported contacts.
     *
     * @return The list of topic subscriptions, or {@code null} if none.
     */
    public List<ContactImportTopicSubscription> getTopics() {
        return topics;
    }

    /**
     * Creates a new builder instance for constructing CreateContactImportOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateContactImportOptions objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private File file;
        private byte[] fileBytes;
        private String fileName;
        private ContactImportColumnMap columnMap;
        private String onConflict;
        private List<ContactImportSegmentReference> segments;
        private List<ContactImportTopicSubscription> topics;

        /**
         * Sets the CSV file to import. Maximum size 50MB.
         *
         * @param file The CSV file on disk.
         * @return The builder instance.
         */
        public Builder file(File file) {
            this.file = file;
            this.fileBytes = null;
            this.fileName = null;
            return this;
        }

        /**
         * Sets the CSV file content from raw bytes.
         *
         * @param fileBytes The CSV file bytes (max 50MB).
         * @param fileName  The file name to use for the multipart part (e.g. {@code "contacts.csv"}).
         * @return The builder instance.
         */
        public Builder file(byte[] fileBytes, String fileName) {
            this.fileBytes = fileBytes;
            this.fileName = fileName;
            this.file = null;
            return this;
        }

        /**
         * Sets the CSV file content by reading from an {@link InputStream}.
         *
         * <p>The stream is fully read into memory at build time; the caller retains
         * ownership and is responsible for closing the stream.</p>
         *
         * @param inputStream The stream containing the CSV content (max 50MB).
         * @param fileName    The file name to use for the multipart part.
         * @return The builder instance.
         */
        public Builder file(InputStream inputStream, String fileName) {
            if (inputStream == null) {
                throw new IllegalArgumentException("inputStream must not be null");
            }
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] chunk = new byte[8192];
            int read;
            long total = 0;
            try {
                while ((read = inputStream.read(chunk)) != -1) {
                    total += read;
                    if (total > MAX_FILE_SIZE_BYTES) {
                        throw new IllegalArgumentException(
                                "File exceeds maximum size of " + MAX_FILE_SIZE_BYTES + " bytes");
                    }
                    buffer.write(chunk, 0, read);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to read file input stream", e);
            }
            this.fileBytes = buffer.toByteArray();
            this.fileName = fileName;
            this.file = null;
            return this;
        }

        /**
         * Sets the mapping between CSV columns and contact fields.
         *
         * @param columnMap The column map.
         * @return The builder instance.
         */
        public Builder columnMap(ContactImportColumnMap columnMap) {
            this.columnMap = columnMap;
            return this;
        }

        /**
         * Sets the conflict-handling strategy for contacts that already exist. Defaults to
         * {@code "skip"} when not provided.
         *
         * @param onConflict {@code "upsert"} or {@code "skip"}.
         * @return The builder instance.
         */
        public Builder onConflict(String onConflict) {
            this.onConflict = onConflict;
            return this;
        }

        /**
         * Sets the segments that imported contacts will be added to.
         *
         * @param segments The list of segment references.
         * @return The builder instance.
         */
        public Builder segments(List<ContactImportSegmentReference> segments) {
            this.segments = segments;
            return this;
        }

        /**
         * Sets the segments using varargs of references.
         *
         * @param segments The segment references.
         * @return The builder instance.
         */
        public Builder segments(ContactImportSegmentReference... segments) {
            if (this.segments == null) {
                this.segments = new ArrayList<>();
            }
            this.segments.addAll(Arrays.asList(segments));
            return this;
        }

        /**
         * Sets the segments using varargs of segment UUIDs. Each ID is wrapped in a
         * {@link ContactImportSegmentReference} automatically.
         *
         * @param segmentIds The segment UUIDs.
         * @return The builder instance.
         */
        public Builder segments(String... segmentIds) {
            if (this.segments == null) {
                this.segments = new ArrayList<>();
            }
            for (String id : segmentIds) {
                this.segments.add(new ContactImportSegmentReference(id));
            }
            return this;
        }

        /**
         * Sets the topic subscriptions to apply to imported contacts.
         *
         * @param topics The list of topic subscriptions.
         * @return The builder instance.
         */
        public Builder topics(List<ContactImportTopicSubscription> topics) {
            this.topics = topics;
            return this;
        }

        /**
         * Sets the topic subscriptions using varargs.
         *
         * @param topics The topic subscriptions.
         * @return The builder instance.
         */
        public Builder topics(ContactImportTopicSubscription... topics) {
            if (this.topics == null) {
                this.topics = new ArrayList<>();
            }
            this.topics.addAll(Arrays.asList(topics));
            return this;
        }

        /**
         * Builds a new CreateContactImportOptions instance.
         *
         * @return A new CreateContactImportOptions instance.
         */
        public CreateContactImportOptions build() {
            return new CreateContactImportOptions(this);
        }
    }
}
