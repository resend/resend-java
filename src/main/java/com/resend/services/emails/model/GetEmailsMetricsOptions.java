package com.resend.services.emails.model;

import com.resend.core.helper.URLHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents the query parameters for {@code GET /emails/metrics}.
 *
 * <p><strong>Note:</strong> the emails metrics endpoint is currently in beta and might change
 * before GA. The API validates that {@code email} is not combined with the {@code broadcast}
 * dimension (or {@code broadcastIds}), and that {@code broadcastIds} is not combined with the
 * {@code email} dimension (or {@code emailIds}); this SDK does not pre-validate those
 * combinations client-side.</p>
 */
public class GetEmailsMetricsOptions {

    private final String startDate;
    private final String endDate;
    private final String timezone;
    private final MetricsGranularity granularity;
    private final List<MetricName> metrics;
    private final List<MetricsDimension> dimensions;
    private final List<String> domainIds;
    private final List<String> emailIds;
    private final List<String> broadcastIds;

    /**
     * Constructs a GetEmailsMetricsOptions object using the provided builder.
     *
     * @param builder The builder to construct the GetEmailsMetricsOptions.
     */
    public GetEmailsMetricsOptions(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.timezone = builder.timezone;
        this.granularity = builder.granularity;
        this.metrics = builder.metrics;
        this.dimensions = builder.dimensions;
        this.domainIds = builder.domainIds;
        this.emailIds = builder.emailIds;
        this.broadcastIds = builder.broadcastIds;
    }

    /**
     * Gets the start of the date range, as an ISO 8601 date or datetime.
     *
     * @return The start date.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Gets the end of the date range, as an ISO 8601 date or datetime.
     *
     * @return The end date.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Gets the IANA timezone used to bucket metrics (e.g. {@code America/New_York}).
     *
     * @return The timezone.
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Gets the bucket size used when {@code period} is included in {@link #getDimensions()}.
     *
     * @return The granularity.
     */
    public MetricsGranularity getGranularity() {
        return granularity;
    }

    /**
     * Gets the metrics to return. Defaults to all metrics when not set.
     *
     * @return The requested metrics.
     */
    public List<MetricName> getMetrics() {
        return metrics;
    }

    /**
     * Gets the dimensions to break metrics down by. Defaults to none, in which case the
     * response only contains {@code totals}.
     *
     * @return The requested dimensions.
     */
    public List<MetricsDimension> getDimensions() {
        return dimensions;
    }

    /**
     * Gets the sending domain IDs to filter by (max 100).
     *
     * @return The domain ID filter.
     */
    public List<String> getDomainIds() {
        return domainIds;
    }

    /**
     * Gets the email IDs to filter by (max 100).
     *
     * @return The email ID filter.
     */
    public List<String> getEmailIds() {
        return emailIds;
    }

    /**
     * Gets the broadcast IDs to filter by (max 100).
     *
     * @return The broadcast ID filter.
     */
    public List<String> getBroadcastIds() {
        return broadcastIds;
    }

    /**
     * Converts the parameters to a query string.
     *
     * @return A query string starting with "?" if parameters exist, or an empty string otherwise.
     */
    public String toQueryString() {
        Map<String, String> extras = new LinkedHashMap<>();

        if (startDate != null && !startDate.isEmpty()) {
            extras.put("start_date", startDate);
        }
        if (endDate != null && !endDate.isEmpty()) {
            extras.put("end_date", endDate);
        }
        if (timezone != null && !timezone.isEmpty()) {
            extras.put("timezone", timezone);
        }
        if (granularity != null) {
            extras.put("granularity", granularity.getValue());
        }
        if (metrics != null && !metrics.isEmpty()) {
            extras.put("metrics", metrics.stream().map(MetricName::getValue).collect(Collectors.joining(",")));
        }
        if (dimensions != null && !dimensions.isEmpty()) {
            extras.put("dimensions", dimensions.stream().map(MetricsDimension::getValue).collect(Collectors.joining(",")));
        }
        if (domainIds != null && !domainIds.isEmpty()) {
            extras.put("domain_id", String.join(",", domainIds));
        }
        if (emailIds != null && !emailIds.isEmpty()) {
            extras.put("email_id", String.join(",", emailIds));
        }
        if (broadcastIds != null && !broadcastIds.isEmpty()) {
            extras.put("broadcast_id", String.join(",", broadcastIds));
        }

        return URLHelper.parse(null, extras);
    }

    /**
     * Creates a new builder instance for constructing GetEmailsMetricsOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing GetEmailsMetricsOptions objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String startDate;
        private String endDate;
        private String timezone;
        private MetricsGranularity granularity;
        private List<MetricName> metrics;
        private List<MetricsDimension> dimensions;
        private List<String> domainIds;
        private List<String> emailIds;
        private List<String> broadcastIds;

        /**
         * Sets the start of the date range (ISO 8601 date or datetime). Defaults server-side
         * to 6 days before {@code endDate}.
         *
         * @param startDate The start date.
         * @return The builder instance.
         */
        public Builder startDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        /**
         * Sets the end of the date range (ISO 8601 date or datetime). Defaults server-side to
         * now.
         *
         * @param endDate The end date.
         * @return The builder instance.
         */
        public Builder endDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        /**
         * Sets the IANA timezone used to bucket metrics (e.g. {@code America/New_York}).
         * Defaults server-side to {@code UTC}.
         *
         * @param timezone The timezone.
         * @return The builder instance.
         */
        public Builder timezone(String timezone) {
            this.timezone = timezone;
            return this;
        }

        /**
         * Sets the bucket size used when {@code period} is included in the requested
         * dimensions. Defaults server-side to {@code daily}.
         *
         * @param granularity The granularity.
         * @return The builder instance.
         */
        public Builder granularity(MetricsGranularity granularity) {
            this.granularity = granularity;
            return this;
        }

        /**
         * Sets the metrics to return, replacing any previously set metrics.
         *
         * @param metrics The metrics to return.
         * @return The builder instance.
         */
        public Builder metrics(List<MetricName> metrics) {
            this.metrics = metrics;
            return this;
        }

        /**
         * Sets the metrics to return, replacing any previously set metrics.
         *
         * @param metrics The metrics to return.
         * @return The builder instance.
         */
        public Builder metrics(MetricName... metrics) {
            this.metrics = new ArrayList<>(Arrays.asList(metrics));
            return this;
        }

        /**
         * Sets the dimensions to break metrics down by, replacing any previously set
         * dimensions.
         *
         * @param dimensions The dimensions.
         * @return The builder instance.
         */
        public Builder dimensions(List<MetricsDimension> dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        /**
         * Sets the dimensions to break metrics down by, replacing any previously set
         * dimensions.
         *
         * @param dimensions The dimensions.
         * @return The builder instance.
         */
        public Builder dimensions(MetricsDimension... dimensions) {
            this.dimensions = new ArrayList<>(Arrays.asList(dimensions));
            return this;
        }

        /**
         * Sets the sending domain IDs to filter by (max 100), replacing any previously set
         * domain IDs.
         *
         * @param domainIds The domain ID filter.
         * @return The builder instance.
         */
        public Builder domainIds(List<String> domainIds) {
            this.domainIds = domainIds;
            return this;
        }

        /**
         * Sets the sending domain IDs to filter by (max 100), replacing any previously set
         * domain IDs.
         *
         * @param domainIds The domain ID filter.
         * @return The builder instance.
         */
        public Builder domainIds(String... domainIds) {
            this.domainIds = new ArrayList<>(Arrays.asList(domainIds));
            return this;
        }

        /**
         * Sets the email IDs to filter by (max 100), replacing any previously set email IDs.
         * Cannot be combined with the {@code broadcast} dimension or {@code broadcastIds}.
         *
         * @param emailIds The email ID filter.
         * @return The builder instance.
         */
        public Builder emailIds(List<String> emailIds) {
            this.emailIds = emailIds;
            return this;
        }

        /**
         * Sets the email IDs to filter by (max 100), replacing any previously set email IDs.
         * Cannot be combined with the {@code broadcast} dimension or {@code broadcastIds}.
         *
         * @param emailIds The email ID filter.
         * @return The builder instance.
         */
        public Builder emailIds(String... emailIds) {
            this.emailIds = new ArrayList<>(Arrays.asList(emailIds));
            return this;
        }

        /**
         * Sets the broadcast IDs to filter by (max 100), replacing any previously set
         * broadcast IDs. Cannot be combined with the {@code email} dimension or
         * {@code emailIds}.
         *
         * @param broadcastIds The broadcast ID filter.
         * @return The builder instance.
         */
        public Builder broadcastIds(List<String> broadcastIds) {
            this.broadcastIds = broadcastIds;
            return this;
        }

        /**
         * Sets the broadcast IDs to filter by (max 100), replacing any previously set
         * broadcast IDs. Cannot be combined with the {@code email} dimension or
         * {@code emailIds}.
         *
         * @param broadcastIds The broadcast ID filter.
         * @return The builder instance.
         */
        public Builder broadcastIds(String... broadcastIds) {
            this.broadcastIds = new ArrayList<>(Arrays.asList(broadcastIds));
            return this;
        }

        /**
         * Builds a new GetEmailsMetricsOptions instance.
         *
         * @return A new GetEmailsMetricsOptions instance.
         */
        public GetEmailsMetricsOptions build() {
            return new GetEmailsMetricsOptions(this);
        }
    }
}
