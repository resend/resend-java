package com.resend.services.automations.model;

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

    public RunStatus getStatus() {
        return status;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getAfter() {
        return after;
    }

    public String getBefore() {
        return before;
    }

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
