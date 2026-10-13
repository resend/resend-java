package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a request to update a thread. Provide at least one of {@code read}, {@code folder}, {@code add_label_ids}, or {@code remove_label_ids}. A label ID cannot be both added and removed in the same request.
 */
public class UpdateInboxThreadOptions {

    @JsonProperty("read")
    private final Boolean read;

    @JsonProperty("folder")
    private final MoveInboxThreadFolder folder;

    @JsonProperty("add_label_ids")
    private final List<String> addLabelIds;

    @JsonProperty("remove_label_ids")
    private final List<String> removeLabelIds;

    private UpdateInboxThreadOptions(Builder builder) {
        this.read = builder.read;
        this.folder = builder.folder;
        this.addLabelIds = builder.addLabelIds;
        this.removeLabelIds = builder.removeLabelIds;
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
     * Get the label IDs to apply to the thread.
     *
     * @return The label IDs to apply to the thread.
     */
    public List<String> getAddLabelIds() {
        return addLabelIds;
    }

    /**
     * Get the label IDs to remove from the thread.
     *
     * @return The label IDs to remove from the thread.
     */
    public List<String> getRemoveLabelIds() {
        return removeLabelIds;
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
        private List<String> addLabelIds;
        private List<String> removeLabelIds;

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
         * Set the label IDs to apply to the thread. Between 1 and 10 IDs. A label ID that does not exist in the inbox returns a 404.
         *
         * @param addLabelIds The label IDs to apply to the thread.
         * @return The builder instance.
         */
        public Builder addLabelIds(String... addLabelIds) {
            this.addLabelIds = new ArrayList<>(Arrays.asList(addLabelIds));
            return this;
        }

        /**
         * Set the label IDs to apply to the thread. Between 1 and 10 IDs. A label ID that does not exist in the inbox returns a 404.
         *
         * @param addLabelIds The label IDs to apply to the thread.
         * @return The builder instance.
         */
        public Builder addLabelIds(List<String> addLabelIds) {
            this.addLabelIds = addLabelIds == null ? null : new ArrayList<>(addLabelIds);
            return this;
        }

        /**
         * Set the label IDs to remove from the thread. Between 1 and 10 IDs. The labels themselves are kept. A label ID that does not exist in the inbox returns a 404.
         *
         * @param removeLabelIds The label IDs to remove from the thread.
         * @return The builder instance.
         */
        public Builder removeLabelIds(String... removeLabelIds) {
            this.removeLabelIds = new ArrayList<>(Arrays.asList(removeLabelIds));
            return this;
        }

        /**
         * Set the label IDs to remove from the thread. Between 1 and 10 IDs. The labels themselves are kept. A label ID that does not exist in the inbox returns a 404.
         *
         * @param removeLabelIds The label IDs to remove from the thread.
         * @return The builder instance.
         */
        public Builder removeLabelIds(List<String> removeLabelIds) {
            this.removeLabelIds = removeLabelIds == null ? null : new ArrayList<>(removeLabelIds);
            return this;
        }

        /**
         * Build a new UpdateInboxThreadOptions object.
         *
         * @return A new UpdateInboxThreadOptions object.
         * @throws IllegalArgumentException if no field is set, a label ID list is empty, or a label ID is both added and removed.
         */
        public UpdateInboxThreadOptions build() {
            if (read == null && folder == null && addLabelIds == null && removeLabelIds == null) {
                throw new IllegalArgumentException("At least one of read, folder, add_label_ids or remove_label_ids must be provided");
            }
            if ((addLabelIds != null && addLabelIds.isEmpty()) || (removeLabelIds != null && removeLabelIds.isEmpty())) {
                throw new IllegalArgumentException("add_label_ids and remove_label_ids must contain at least one label ID");
            }
            if (addLabelIds != null && removeLabelIds != null && !Collections.disjoint(addLabelIds, removeLabelIds)) {
                throw new IllegalArgumentException("A label ID cannot be both added and removed in the same request");
            }
            return new UpdateInboxThreadOptions(this);
        }
    }
}
