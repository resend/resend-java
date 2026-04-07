package com.resend.services.automations.model;

public class ListAutomationsParams {

    private final AutomationStatus status;
    private final Integer limit;
    private final String after;
    private final String before;

    public ListAutomationsParams(Builder builder) {
        this.status = builder.status;
        this.limit = builder.limit;
        this.after = builder.after;
        this.before = builder.before;
    }

    public AutomationStatus getStatus() {
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
        private AutomationStatus status;
        private Integer limit;
        private String after;
        private String before;

        public Builder status(AutomationStatus status) {
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

        public ListAutomationsParams build() {
            return new ListAutomationsParams(this);
        }
    }
}
