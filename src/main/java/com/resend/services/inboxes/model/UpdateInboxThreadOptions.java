package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update a thread. Provide at least one of {@code read} or {@code folder}.
 * To change the labels of a thread, use {@link com.resend.services.inboxes.InboxThreads#addLabels},
 * {@link com.resend.services.inboxes.InboxThreads#setLabels} or {@link com.resend.services.inboxes.InboxThreads#removeLabel}.
 */
public class UpdateInboxThreadOptions {

    @JsonProperty("read")
    private final Boolean read;

    @JsonProperty("folder")
    private final MoveInboxThreadFolder folder;

    private UpdateInboxThreadOptions(Builder builder) {
        this.read = builder.read;
        this.folder = builder.folder;
    }

    /**
     * Get whether every email in the thread is marked read or unread.
     *
     * @return Whether every email in the thread is marked read or unread.
     */
    public Boolean getRead() {
        return read;
    }

    /**
     * Get the folder to move the thread to.
     *
     * @return The folder to move the thread to.
     */
    public MoveInboxThreadFolder getFolder() {
        return folder;
    }


    /**
     * Create a new builder instance for constructing UpdateInboxThreadOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateInboxThreadOptions objects.
     */
    public static class Builder {
        private Boolean read;
        private MoveInboxThreadFolder folder;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set whether every email in the thread is marked read or unread.
         *
         * @param read Whether every email in the thread is marked read or unread.
         * @return The builder instance.
         */
        public Builder read(Boolean read) {
            this.read = read;
            return this;
        }

        /**
         * Set the folder to move the thread to. Moving a thread out of {@code trash} restores it and clears its deletion deadline.
         *
         * @param folder The folder to move the thread to.
         * @return The builder instance.
         */
        public Builder folder(MoveInboxThreadFolder folder) {
            this.folder = folder;
            return this;
        }


        /**
         * Build a new UpdateInboxThreadOptions object.
         *
         * @return A new UpdateInboxThreadOptions object.
         * @throws IllegalArgumentException if neither {@code read} nor {@code folder} is set.
         */
        public UpdateInboxThreadOptions build() {
            if (read == null && folder == null) {
                throw new IllegalArgumentException("At least one of read or folder must be provided");
            }
            return new UpdateInboxThreadOptions(this);
        }
    }
}
