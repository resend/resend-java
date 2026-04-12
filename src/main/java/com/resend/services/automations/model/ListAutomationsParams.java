package com.resend.services.automations.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents parameters for listing automations with filtering and pagination.
 */
public class ListAutomationsParams {

    private final AutomationStatus status;
    private final Integer limit;
    private final String after;
    private final String before;

    /**
     * Constructs ListAutomationsParams using the provided builder.
     *
     * @param builder The builder to construct the params.
     */
    public ListAutomationsParams(Builder builder) {
        this.status = builder.status;
        this.limit = builder.limit;
        this.after = builder.after;
        this.before = builder.before;
    }

    /**
     * Retrieves the status filter.
     *
     * @return The automation status filter.
     */
    public AutomationStatus getStatus() {
        return status;
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
     * Converts the parameters to a query string.
     *
     * @return A query string starting with "?" if parameters exist, or an empty string otherwise.
     */
    public String toQueryString() {
        Map<String, String> queryParams = new LinkedHashMap<>();

        if (status != null) {
            queryParams.put("status", status.getValue());
        }
        if (limit != null) {
            queryParams.put("limit", limit.toString());
        }
        if (after != null && !after.isEmpty()) {
            queryParams.put("after", after);
        }
        if (before != null && !before.isEmpty()) {
            queryParams.put("before", before);
        }

        if (queryParams.isEmpty()) {
            return "";
        }

        return "?" + queryParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }

    /**
     * Creates a new builder instance for ListAutomationsParams.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ListAutomationsParams objects.
     */
    public static class Builder {
        /**
         * Constructs a new Builder instance.
         */
        public Builder() {}

        private AutomationStatus status;
        private Integer limit;
        private String after;
        private String before;

        /**
         * Sets the status filter.
         *
         * @param status The automation status.
         * @return The builder instance.
         */
        public Builder status(AutomationStatus status) {
            this.status = status;
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
         * Builds a new ListAutomationsParams instance.
         *
         * @return A new ListAutomationsParams.
         */
        public ListAutomationsParams build() {
            return new ListAutomationsParams(this);
        }
    }
}
