package com.resend.services.automations.model;

/**
 * Represents parameters for listing automation runs with filtering and pagination.
 */
public class ListAutomationRunsParams {

    private final RunStatus status;
    private final Integer limit;
    private final String after;
    private final String before;

    public ListAutomationRunsParams(Builder builder) {
        this.status = builder.status;
        this.limit = builder.limit;
        this.after = builder.after;
        this.before = builder.before;
    }

    /**
     * Retrieves the status filter.
     *
     * @return The run status filter.
     */
    public RunStatus getStatus() {
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
     * Creates a new builder instance for ListAutomationRunsParams.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private RunStatus status;
        private Integer limit;
        private String after;
        private String before;

        public Builder status(RunStatus status) {
            this.status = status;
            return this;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder after(String after) {
            this.after = after;
            return this;
        }

        public Builder before(String before) {
            this.before = before;
            return this;
        }

        public ListAutomationRunsParams build() {
            return new ListAutomationRunsParams(this);
        }
    }
}
