package com.resend.services.suppressions.model;

import com.resend.core.helper.URLHelper;
import com.resend.core.net.ListParams;

import java.util.Collections;
import java.util.Map;

/**
 * Represents parameters for listing suppressions with filtering and pagination.
 */
public class ListSuppressionsParams {

    private final Integer limit;
    private final String after;
    private final String before;
    private final SuppressionOrigin origin;

    /**
     * Constructs ListSuppressionsParams using the provided builder.
     *
     * @param builder The builder to construct the params.
     */
    public ListSuppressionsParams(Builder builder) {
        this.limit = builder.limit;
        this.after = builder.after;
        this.before = builder.before;
        this.origin = builder.origin;
    }

    /**
     * Retrieves the maximum number of results.
     *
     * @return The limit.
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Retrieves the cursor for pagination (after).
     *
     * @return The after cursor.
     */
    public String getAfter() {
        return after;
    }

    /**
     * Retrieves the cursor for pagination (before).
     *
     * @return The before cursor.
     */
    public String getBefore() {
        return before;
    }

    /**
     * Retrieves the origin filter.
     *
     * @return The suppression origin filter.
     */
    public SuppressionOrigin getOrigin() {
        return origin;
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

        Map<String, String> extras = origin == null
                ? Collections.<String, String>emptyMap()
                : Collections.singletonMap("origin", origin.getValue());

        return URLHelper.parse(base, extras);
    }

    /**
     * Creates a new builder instance for ListSuppressionsParams.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ListSuppressionsParams objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private Integer limit;
        private String after;
        private String before;
        private SuppressionOrigin origin;

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
         * Sets the after cursor for pagination.
         *
         * @param after The after cursor.
         * @return The builder instance.
         */
        public Builder after(String after) {
            this.after = after;
            return this;
        }

        /**
         * Sets the before cursor for pagination.
         *
         * @param before The before cursor.
         * @return The builder instance.
         */
        public Builder before(String before) {
            this.before = before;
            return this;
        }

        /**
         * Sets the origin filter.
         *
         * @param origin The suppression origin.
         * @return The builder instance.
         */
        public Builder origin(SuppressionOrigin origin) {
            this.origin = origin;
            return this;
        }

        /**
         * Builds a new ListSuppressionsParams instance.
         *
         * @return A new ListSuppressionsParams.
         */
        public ListSuppressionsParams build() {
            return new ListSuppressionsParams(this);
        }
    }
}
