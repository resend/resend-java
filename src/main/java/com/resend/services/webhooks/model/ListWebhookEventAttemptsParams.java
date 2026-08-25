package com.resend.services.webhooks.model;

import com.resend.core.helper.URLHelper;
import com.resend.core.net.ListParams;

public class ListWebhookEventAttemptsParams {
    private final Integer limit;
    private final String after;

    public ListWebhookEventAttemptsParams(Builder builder) {
        this.limit = builder.limit;
        this.after = builder.after;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getAfter() {
        return after;
    }

    public String toQueryString() {
        return URLHelper.parse(ListParams.builder().limit(limit).after(after).build());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer limit;
        private String after;

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder after(String after) {
            this.after = after;
            return this;
        }

        public ListWebhookEventAttemptsParams build() {
            return new ListWebhookEventAttemptsParams(this);
        }
    }
}
