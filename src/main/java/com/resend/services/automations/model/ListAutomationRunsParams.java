package com.resend.services.automations.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents parameters for listing automation runs with filtering and pagination.
 */
public class ListAutomationRunsParams {

    private final List<RunStatus> status;
    private final Integer limit;
    private final String after;
    private final String before;

    /**
     * Constructs ListAutomationRunsParams using the provided builder.
     *
     * @param builder The builder to construct the params.
     */
    public ListAutomationRunsParams(Builder builder) {
        this.status = builder.status;
        this.limit = builder.limit;
        this.after = builder.after;
        this.before = builder.before;
    }

    /**
     * Retrieves the status filters.
     *
     * @return The list of run status filters.
     */
    public List<RunStatus> getStatus() {
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

        if (status != null && !status.isEmpty()) {
            String statusValue = status.stream()
                    .map(RunStatus::getValue)
                    .collect(Collectors.joining(","));
            queryParams.put("status", statusValue);
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
     * Creates a new builder instance for ListAutomationRunsParams.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ListAutomationRunsParams objects.
     */
    public static class Builder {
        private List<RunStatus> status;
        private Integer limit;
        private String after;
        private String before;

        /**
         * Adds status filters.
         *
         * @param status The run statuses to filter by.
         * @return This builder instance.
         */
        public Builder status(RunStatus... status) {
            if (this.status == null) {
                this.status = new ArrayList<>();
            }
            this.status.addAll(Arrays.asList(status));
            return this;
        }

        /**
         * Adds status filters from a list.
         *
         * @param status The list of run statuses to filter by.
         * @return This builder instance.
         */
        public Builder status(List<RunStatus> status) {
            if (this.status == null) {
                this.status = new ArrayList<>();
            }
            this.status.addAll(status);
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
         * Builds a new ListAutomationRunsParams instance.
         *
         * @return A new ListAutomationRunsParams.
         */
        public ListAutomationRunsParams build() {
            return new ListAutomationRunsParams(this);
        }
    }
}
