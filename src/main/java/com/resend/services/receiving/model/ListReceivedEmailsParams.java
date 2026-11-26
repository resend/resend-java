package com.resend.services.receiving.model;

import com.resend.core.helper.URLHelper;
import com.resend.core.net.ListParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents parameters for listing received emails with filtering and pagination.
 * Different parameters combine, so an email must match all of them.
 */
public class ListReceivedEmailsParams {

    private final List<String> to;
    private final String startDate;
    private final String endDate;
    private final Integer limit;
    private final String after;
    private final String before;

    private ListReceivedEmailsParams(Builder builder) {
        this.to = builder.to;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.limit = builder.limit;
        this.after = builder.after;
        this.before = builder.before;
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
        if (to != null && !to.isEmpty()) {
            extras.put("to", String.join(",", to));
        }
        extras.put("start_date", startDate);
        extras.put("end_date", endDate);

        return URLHelper.parse(base, extras);
    }

    /**
     * Creates a new builder instance for ListReceivedEmailsParams.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ListReceivedEmailsParams objects.
     */
    public static class Builder {
        private List<String> to;
        private String startDate;
        private String endDate;
        private Integer limit;
        private String after;
        private String before;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Sets the recipients. Returns emails received by any of these addresses.
         * Each value must match the recipient address exactly, with case. Up to 20 values.
         *
         * @param to The recipients.
         * @return The builder instance.
         */
        public Builder to(String... to) {
            this.to = new ArrayList<>(Arrays.asList(to));
            return this;
        }

        /**
         * Sets the recipients. Returns emails received by any of these addresses.
         * Each value must match the recipient address exactly, with case. Up to 20 values.
         *
         * @param to The recipients.
         * @return The builder instance.
         */
        public Builder to(List<String> to) {
            this.to = to == null ? null : new ArrayList<>(to);
            return this;
        }

        /**
         * Sets the start date. Matches emails received on or after this date. Accepts a date like
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
         * Sets the end date. Matches emails received on or before this date. Same formats as {@code start_date}.
         * A date covers that whole day. A timestamp is used as given. Must not be before {@code start_date}.
         *
         * @param endDate The end date.
         * @return The builder instance.
         */
        public Builder endDate(String endDate) {
            this.endDate = endDate;
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
         * Sets the after cursor for pagination. Takes a received email ID.
         *
         * @param after The after cursor.
         * @return The builder instance.
         */
        public Builder after(String after) {
            this.after = after;
            return this;
        }

        /**
         * Sets the before cursor for pagination. Takes a received email ID.
         *
         * @param before The before cursor.
         * @return The builder instance.
         */
        public Builder before(String before) {
            this.before = before;
            return this;
        }

        /**
         * Builds a new ListReceivedEmailsParams instance.
         *
         * @return A new ListReceivedEmailsParams.
         * @throws IllegalArgumentException if both {@code after} and {@code before} are set.
         */
        public ListReceivedEmailsParams build() {
            boolean hasAfter = after != null && !after.isEmpty();
            boolean hasBefore = before != null && !before.isEmpty();
            if (hasAfter && hasBefore) {
                throw new IllegalArgumentException("after and before cannot be used together");
            }
            return new ListReceivedEmailsParams(this);
        }
    }
}
