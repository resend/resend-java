package com.resend.services.inboxes.model;

import com.resend.core.helper.URLHelper;
import com.resend.core.net.ListParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents parameters for searching the threads of an inbox.
 * Different parameters combine, so a thread must match all of them. {@code folders}, {@code labels}, and
 * {@code read} apply to the thread. {@code query}, {@code from}, {@code to}, {@code cc}, {@code bcc},
 * {@code has_attachment}, {@code start_date}, and {@code end_date} must all match the same email in the thread.
 * When any of these email filters is set, the matching email must itself be in one of {@code folders}.
 * For {@code sent}, that means an email the inbox sent.
 */
public class SearchInboxThreadsParams {

    private final String query;
    private final List<String> from;
    private final List<String> to;
    private final List<String> cc;
    private final List<String> bcc;
    private final Boolean hasAttachment;
    private final String startDate;
    private final String endDate;
    private final List<InboxFolder> folders;
    private final List<String> labels;
    private final Boolean read;
    private final Integer limit;
    private final String after;
    private final String before;

    private SearchInboxThreadsParams(Builder builder) {
        this.query = builder.query;
        this.from = builder.from;
        this.to = builder.to;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.hasAttachment = builder.hasAttachment;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.folders = builder.folders;
        this.labels = builder.labels;
        this.read = builder.read;
        this.limit = builder.limit;
        this.after = builder.after;
        this.before = builder.before;
    }

    /**
     * Get the free text to search for.
     *
     * @return The query.
     */
    public String getQuery() {
        return query;
    }

    /**
     * Get the senders to match.
     *
     * @return The senders filter.
     */
    public List<String> getFrom() {
        return from;
    }

    /**
     * Get the recipients to match.
     *
     * @return The recipients filter.
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * Get the CC recipients to match.
     *
     * @return The CC recipients filter.
     */
    public List<String> getCc() {
        return cc;
    }

    /**
     * Get the BCC recipients to match.
     *
     * @return The BCC recipients filter.
     */
    public List<String> getBcc() {
        return bcc;
    }

    /**
     * Get the attachment filter.
     *
     * @return The attachment filter.
     */
    public Boolean getHasAttachment() {
        return hasAttachment;
    }

    /**
     * Get the start date filter.
     *
     * @return The start date.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Get the end date filter.
     *
     * @return The end date.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Get the folders to search.
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

        Map<String, String> extras = new LinkedHashMap<>();
        extras.put("query", query);
        putList(extras, "from", from);
        putList(extras, "to", to);
        putList(extras, "cc", cc);
        putList(extras, "bcc", bcc);
        if (hasAttachment != null) {
            extras.put("has_attachment", hasAttachment.toString());
        }
        extras.put("start_date", startDate);
        extras.put("end_date", endDate);
        extras.putAll(ListInboxThreadsParams.filters(folders, labels, read));

        return URLHelper.parse(base, extras);
    }

    private static void putList(Map<String, String> extras, String key, List<String> values) {
        if (values != null && !values.isEmpty()) {
            extras.put(key, String.join(",", values));
        }
    }

    /**
     * Creates a new builder instance for SearchInboxThreadsParams.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing SearchInboxThreadsParams objects.
     */
    public static class Builder {
        private String query;
        private List<String> from;
        private List<String> to;
        private List<String> cc;
        private List<String> bcc;
        private Boolean hasAttachment;
        private String startDate;
        private String endDate;
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
         * Sets the free text to search for in the subject, body, sender, recipients, and attachment names.
         * Every word must match. Wrap words in double quotes to match an exact phrase. Start a word or quoted
         * phrase with {@code -} to exclude it. Max 256 characters and 32 words.
         *
         * @param query The query.
         * @return The builder instance.
         */
        public Builder query(String query) {
            this.query = query;
            return this;
        }

        /**
         * Sets the senders. Each value matches part of the sender's address or name, without case.
         * Returns threads that match any of them. Up to 20 values.
         *
         * @param from The senders.
         * @return The builder instance.
         */
        public Builder from(String... from) {
            this.from = new ArrayList<>(Arrays.asList(from));
            return this;
        }

        /**
         * Sets the senders. Each value matches part of the sender's address or name, without case.
         * Returns threads that match any of them. Up to 20 values.
         *
         * @param from The senders.
         * @return The builder instance.
         */
        public Builder from(List<String> from) {
            this.from = from == null ? null : new ArrayList<>(from);
            return this;
        }

        /**
         * Sets the recipients, matched the same way as {@code from}. Up to 20 values.
         *
         * @param to The recipients.
         * @return The builder instance.
         */
        public Builder to(String... to) {
            this.to = new ArrayList<>(Arrays.asList(to));
            return this;
        }

        /**
         * Sets the recipients, matched the same way as {@code from}. Up to 20 values.
         *
         * @param to The recipients.
         * @return The builder instance.
         */
        public Builder to(List<String> to) {
            this.to = to == null ? null : new ArrayList<>(to);
            return this;
        }

        /**
         * Sets the CC recipients, matched the same way as {@code from}. Up to 20 values.
         *
         * @param cc The CC recipients.
         * @return The builder instance.
         */
        public Builder cc(String... cc) {
            this.cc = new ArrayList<>(Arrays.asList(cc));
            return this;
        }

        /**
         * Sets the CC recipients, matched the same way as {@code from}. Up to 20 values.
         *
         * @param cc The CC recipients.
         * @return The builder instance.
         */
        public Builder cc(List<String> cc) {
            this.cc = cc == null ? null : new ArrayList<>(cc);
            return this;
        }

        /**
         * Sets the BCC recipients, matched the same way as {@code from}. Up to 20 values.
         *
         * @param bcc The BCC recipients.
         * @return The builder instance.
         */
        public Builder bcc(String... bcc) {
            this.bcc = new ArrayList<>(Arrays.asList(bcc));
            return this;
        }

        /**
         * Sets the BCC recipients, matched the same way as {@code from}. Up to 20 values.
         *
         * @param bcc The BCC recipients.
         * @return The builder instance.
         */
        public Builder bcc(List<String> bcc) {
            this.bcc = bcc == null ? null : new ArrayList<>(bcc);
            return this;
        }

        /**
         * Sets the attachment filter. {@code true} matches emails with an attachment.
         * {@code false} matches emails without one.
         *
         * @param hasAttachment The attachment filter.
         * @return The builder instance.
         */
        public Builder hasAttachment(Boolean hasAttachment) {
            this.hasAttachment = hasAttachment;
            return this;
        }

        /**
         * Sets the start date. Matches emails sent on or after this date. Accepts a date like
         * {@code 2026-09-01} or an ISO 8601 timestamp, in UTC unless the timestamp has an offset.
         *
         * @param startDate The start date.
         * @return The builder instance.
         */
        public Builder startDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        /**
         * Sets the end date. Matches emails sent on or before this date. Same formats as {@code start_date}.
         * A date covers that whole day. A timestamp is used as given.
         *
         * @param endDate The end date.
         * @return The builder instance.
         */
        public Builder endDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        /**
         * Sets the folders to search. A thread is returned when any of its emails is in one of the folders.
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
         * Sets the folders to search. A thread is returned when any of its emails is in one of the folders.
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
         * Builds a new SearchInboxThreadsParams instance.
         *
         * @return A new SearchInboxThreadsParams.
         * @throws IllegalArgumentException if both {@code after} and {@code before} are set.
         */
        public SearchInboxThreadsParams build() {
            boolean hasAfter = after != null && !after.isEmpty();
            boolean hasBefore = before != null && !before.isEmpty();
            if (hasAfter && hasBefore) {
                throw new IllegalArgumentException("after and before cannot be used together");
            }
            return new SearchInboxThreadsParams(this);
        }
    }
}
