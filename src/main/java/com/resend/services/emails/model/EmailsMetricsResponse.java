package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * Represents the response from {@code GET /emails/metrics}.
 *
 * <p>{@code data} is {@code null} when no {@code dimensions} were requested; in that case only
 * {@code totals} is populated.</p>
 */
public class EmailsMetricsResponse {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("start_date")
    private @Nullable String startDate;

    @JsonProperty("end_date")
    private @Nullable String endDate;

    @JsonProperty("metrics")
    private @Nullable List<MetricName> metrics;

    @JsonProperty("dimensions")
    private @Nullable List<MetricsDimension> dimensions;

    @JsonProperty("granularity")
    private @Nullable MetricsGranularity granularity;

    @JsonProperty("totals")
    private @Nullable Map<String, @Nullable Object> totals;

    @JsonProperty("data")
    private @Nullable List<EmailsMetricsDataRow> data;

    /**
     * Default constructor for deserialization.
     */
    public EmailsMetricsResponse() {
    }

    /**
     * Gets the object type, always {@code metrics}.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Gets the resolved start of the date range that was queried.
     *
     * @return The start date.
     */
    public @Nullable String getStartDate() {
        return startDate;
    }

    /**
     * Gets the resolved end of the date range that was queried.
     *
     * @return The end date.
     */
    public @Nullable String getEndDate() {
        return endDate;
    }

    /**
     * Gets the metrics that were returned.
     *
     * @return The returned metrics.
     */
    public @Nullable List<MetricName> getMetrics() {
        return metrics;
    }

    /**
     * Gets the dimensions that were used to break the metrics down.
     *
     * @return The dimensions used, empty when the response only contains {@code totals}.
     */
    public @Nullable List<MetricsDimension> getDimensions() {
        return dimensions;
    }

    /**
     * Gets the bucket size used for the {@code period} dimension.
     *
     * @return The granularity.
     */
    public @Nullable MetricsGranularity getGranularity() {
        return granularity;
    }

    /**
     * Gets the aggregate totals for each requested metric across the whole date range.
     *
     * @return The totals, keyed by metric name.
     */
    public @Nullable Map<String, @Nullable Object> getTotals() {
        return totals;
    }

    /**
     * Gets the metrics broken down by the requested dimensions.
     *
     * @return The data rows, or {@code null} when no dimensions were requested.
     */
    public @Nullable List<EmailsMetricsDataRow> getData() {
        return data;
    }
}
