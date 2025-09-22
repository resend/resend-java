package com.resend.core.net;

import com.resend.services.broadcasts.model.SendBroadcastOptions;

/**
 * Represents a class that wraps the parameters for the list method.
 */
public class ListParams {

    /**
     *  The maximum number of emails to return. Defaults to 10, maximum 100.
     */
    private final Integer limit;

    /**
     * Return emails after this cursor for pagination.
     */
    private final String after;

    /**
     * Return emails before this cursor for pagination.
     */
    private final String before;

    /**
     * Constructs a ListParams object using the provided builder.
     *
     * @param builder The builder to construct the ListParams.
     */
    public ListParams(Builder builder) {
        this.limit =  builder.limit;
        this.after =  builder.after;
        this.before =  builder.before;
    }

    /**
     * Get the pagination limit.
     *
     * @return pagination limit.
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Get emails after this cursor for pagination.
     * @return email after this cursor.
     */
    public String getAfter() {
        return after;
    }

    /**
     * Get emails before this cursor for pagination.
     * @return email before this cursor.
     */
    public String getBefore() {
        return before;
    }

    /**
     * Builder class for constructing ListParams objects.
     */
    public static class Builder {
        private Integer limit;

        private String before;

        private String after;


        /**
         * Set the maximum number of emails to return.
         *
         * @param limit The maximum number of emails.
         * @return The builder instance.
         */
        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Set the emails before this cursor for pagination.
         *
         * @param before The emails after this cursor for pagination.
         * @return The builder instance.
         */
        public Builder before(String before) {
            this.before = before;
            return this;
        }

        /**
         * Set the emails after this cursor for pagination.
         *
         * @param after The emails after this cursor for pagination.
         * @return The builder instance.
         */
        public Builder after(String after) {
            this.after = after;
            return this;
        }

        /**
         * Builds an instance of {@link ListParams} with the specified options.
         *
         * @return A new {@link ListParams} instance.
         */
        public ListParams build() {
            return new ListParams(this);
        }
    }

    /**
     * Create a new builder instance for constructing ListParams objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }
}
