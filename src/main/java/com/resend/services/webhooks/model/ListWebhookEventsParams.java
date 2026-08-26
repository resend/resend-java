package com.resend.services.webhooks.model;

import com.resend.core.helper.URLHelper;
import com.resend.core.net.ListParams;

/**
 * Represents the pagination parameters for listing webhook events.
 */
public class ListWebhookEventsParams {
    private final Integer limit;
    private final String after;

    /**
     * Constructs the parameters from a builder.
     *
     * @param builder The builder containing the pagination parameters.
     */
    public ListWebhookEventsParams(Builder builder) {
        this.limit = builder.limit;
        this.after = builder.after;
    }

    /**
     * Gets the maximum number of events to return.
     *
     * @return The result limit.
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Gets the event ID after which to retrieve results.
     *
     * @return The pagination cursor.
     */
    public String getAfter() {
        return after;
    }

    /**
     * Converts the parameters to a URL query string.
     *
     * @return The URL query string.
     */
    public String toQueryString() {
        return URLHelper.parse(ListParams.builder().limit(limit).after(after).build());
    }

    /**
     * Creates a new parameters builder.
     *
     * @return A new builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builds parameters for listing webhook events.
     */
    public static class Builder {
        private Integer limit;
        private String after;

        /**
         * Constructs an empty webhook event list parameters builder.
         */
        public Builder() {
        }

        /**
         * Sets the maximum number of events to return.
         *
         * @param limit The result limit.
         * @return This builder.
         */
        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Sets the event ID after which to retrieve results.
         *
         * @param after The pagination cursor.
         * @return This builder.
         */
        public Builder after(String after) {
            this.after = after;
            return this;
        }

        /**
         * Builds the webhook event list parameters.
         *
         * @return The configured parameters.
         */
        public ListWebhookEventsParams build() {
            return new ListWebhookEventsParams(this);
        }
    }
}
