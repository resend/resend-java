package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String object;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("metrics")
    private List<MetricName> metrics;

    @JsonProperty("dimensions")
    private List<MetricsDimension> dimensions;

    @JsonProperty("granularity")
    private MetricsGranularity granularity;

    @JsonProperty("totals")
    private Map<String, Object> totals;

    @JsonProperty("data")
    private List<EmailsMetricsDataRow> data;

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
    public String getObject() {
        return object;
    }

    /**
     * Gets the resolved start of the date range that was queried.
     *
     * @return The start date.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Gets the resolved end of the date range that was queried.
     *
     * @return The end date.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Gets the metrics that were returned.
     *
     * @return The returned metrics.
     */
    public List<MetricName> getMetrics() {
        return metrics;
    }

    /**
     * Gets the dimensions that were used to break the metrics down.
     *
     * @return The dimensions used, empty when the response only contains {@code totals}.
     */
    public List<MetricsDimension> getDimensions() {
        return dimensions;
    }

    /**
     * Gets the bucket size used for the {@code period} dimension.
     *
     * @return The granularity.
     */
    public MetricsGranularity getGranularity() {
        return granularity;
    }

    /**
     * Gets the aggregate totals for each requested metric across the whole date range.
     *
     * @return The totals, keyed by metric name.
     */
    public Map<String, Object> getTotals() {
        return totals;
    }

    /**
     * Gets the metrics broken down by the requested dimensions.
     *
     * @return The data rows, or {@code null} when no dimensions were requested.
     */
    public List<EmailsMetricsDataRow> getData() {
        return data;
    }
}
