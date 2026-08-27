package com.resend.services.webhooks.model;

import com.resend.core.helper.URLHelper;
import com.resend.core.net.ListParams;

/**
 * Represents the pagination parameters for listing webhook event attempts.
 */
public class ListWebhookEventAttemptsParams {
    private final Integer limit;
    private final String after;

    /**
     * Constructs the parameters from a builder.
     *
     * @param builder The builder containing the pagination parameters.
     */
    public ListWebhookEventAttemptsParams(Builder builder) {
        this.limit = builder.limit;
        this.after = builder.after;
    }

    /**
     * Gets the maximum number of attempts to return.
     *
     * @return The result limit.
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Gets the attempt ID after which to retrieve results.
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
     * Builds parameters for listing webhook event attempts.
     */
    public static class Builder {
        private Integer limit;
        private String after;

        /**
         * Constructs an empty webhook event attempt list parameters builder.
         */
        public Builder() {
        }

        /**
         * Sets the maximum number of attempts to return.
         *
         * @param limit The result limit.
         * @return This builder.
         */
        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Sets the attempt ID after which to retrieve results.
         *
         * @param after The pagination cursor.
         * @return This builder.
         */
        public Builder after(String after) {
            this.after = after;
            return this;
        }

        /**
         * Builds the webhook event attempt list parameters.
         *
         * @return The configured parameters.
         */
        public ListWebhookEventAttemptsParams build() {
            return new ListWebhookEventAttemptsParams(this);
        }
    }
}
