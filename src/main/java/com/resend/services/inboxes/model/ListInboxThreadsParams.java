package com.resend.services.inboxes.model;

import com.resend.core.helper.URLHelper;
import com.resend.core.net.ListParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents parameters for listing the threads of an inbox with filtering and pagination.
 * The {@code after} and {@code before} cursors take a thread ID, and only one of them can be passed.
 */
public class ListInboxThreadsParams {

    private final List<InboxFolder> folders;
    private final List<String> labels;
    private final Boolean read;
    private final Integer limit;
    private final String after;
    private final String before;

    private ListInboxThreadsParams(Builder builder) {
        this.folders = builder.folders;
        this.labels = builder.labels;
        this.read = builder.read;
        this.limit = builder.limit;
        this.after = builder.after;
        this.before = builder.before;
    }

    /**
     * Get the folders to list.
     *
     * @return The folders filter.
     */
    public List<InboxFolder> getFolders() {
        return folders;
    }

    /**
     * Get the label IDs to filter by.
     *
     * @return The label IDs filter.
     */
    public List<String> getLabels() {
        return labels;
    }

    /**
     * Get the read filter.
     *
     * @return The read filter.
     */
    public Boolean getRead() {
        return read;
    }

    /**
     * Get the maximum number of results.
     *
     * @return The limit.
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Get the cursor for pagination (after).
     *
     * @return The after cursor.
     */
    public String getAfter() {
        return after;
    }

    /**
     * Get the cursor for pagination (before).
     *
     * @return The before cursor.
     */
    public String getBefore() {
        return before;
    }

    /**
     * Converts the parameters to a query string.
     *
     * @return A query string starting with "?" if parameters exist, or an empty string otherwise.
     */
    public String toQueryString() {
        ListParams base = ListParams.builder()
                .limit(limit)
                .after(after)
                .before(before)
                .build();
        return URLHelper.parse(base, filters(folders, labels, read));
    }

    static Map<String, String> filters(List<InboxFolder> folders, List<String> labels, Boolean read) {
        Map<String, String> extras = new LinkedHashMap<>();
        if (folders != null && !folders.isEmpty()) {
            List<String> values = new ArrayList<>();
            for (InboxFolder folder : folders) {
                values.add(folder.getValue());
            }
            extras.put("folders", String.join(",", values));
        }
        if (labels != null && !labels.isEmpty()) {
            extras.put("labels", String.join(",", labels));
        }
        if (read != null) {
            extras.put("read", read.toString());
        }
        return extras;
    }

    /**
     * Creates a new builder instance for ListInboxThreadsParams.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ListInboxThreadsParams objects.
     */
    public static class Builder {
        private List<InboxFolder> folders;
        private List<String> labels;
        private Boolean read;
        private Integer limit;
        private String after;
        private String before;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Sets the folders to list. A thread is returned when any of its emails is in one of the folders.
         * Defaults to {@code inbox}, or to {@code inbox,archive,sent} when {@code labels} is set.
         * {@code spam} and {@code trash} are only included when you name them.
         *
         * @param folders The folders.
         * @return The builder instance.
         */
        public Builder folders(InboxFolder... folders) {
            this.folders = new ArrayList<>(Arrays.asList(folders));
            return this;
        }

        /**
         * Sets the folders to list. A thread is returned when any of its emails is in one of the folders.
         * Defaults to {@code inbox}, or to {@code inbox,archive,sent} when {@code labels} is set.
         * {@code spam} and {@code trash} are only included when you name them.
         *
         * @param folders The folders.
         * @return The builder instance.
         */
        public Builder folders(List<InboxFolder> folders) {
            this.folders = folders == null ? null : new ArrayList<>(folders);
            return this;
        }

        /**
         * Sets the label IDs. Returns threads with any of these labels. Up to 50 IDs.
         * An ID that does not exist in the inbox returns a 404.
         *
         * @param labels The label IDs.
         * @return The builder instance.
         */
        public Builder labels(String... labels) {
            this.labels = new ArrayList<>(Arrays.asList(labels));
            return this;
        }

        /**
         * Sets the label IDs. Returns threads with any of these labels. Up to 50 IDs.
         * An ID that does not exist in the inbox returns a 404.
         *
         * @param labels The label IDs.
         * @return The builder instance.
         */
        public Builder labels(List<String> labels) {
            this.labels = labels == null ? null : new ArrayList<>(labels);
            return this;
        }

        /**
         * Sets the read filter. {@code true} returns threads where every email is read.
         * {@code false} returns threads with at least one unread email.
         *
         * @param read The read filter.
         * @return The builder instance.
         */
        public Builder read(Boolean read) {
            this.read = read;
            return this;
        }

        /**
         * Sets the maximum number of results.
         *
         * @param limit The limit.
         * @return The builder instance.
         */
        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Sets the after cursor for pagination. Takes a thread ID.
         *
         * @param after The after cursor.
         * @return The builder instance.
         */
        public Builder after(String after) {
            this.after = after;
            return this;
        }

        /**
         * Sets the before cursor for pagination. Takes a thread ID.
         *
         * @param before The before cursor.
         * @return The builder instance.
         */
        public Builder before(String before) {
            this.before = before;
            return this;
        }

        /**
         * Builds a new ListInboxThreadsParams instance.
         *
         * @return A new ListInboxThreadsParams.
         * @throws IllegalArgumentException if both {@code after} and {@code before} are set.
         */
        public ListInboxThreadsParams build() {
            boolean hasAfter = after != null && !after.isEmpty();
            boolean hasBefore = before != null && !before.isEmpty();
            if (hasAfter && hasBefore) {
                throw new IllegalArgumentException("after and before cannot be used together");
            }
            return new ListInboxThreadsParams(this);
        }
    }
}
