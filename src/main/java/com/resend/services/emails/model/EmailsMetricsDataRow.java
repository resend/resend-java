package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a single row of the {@code data} array in an emails metrics response.
 *
 * <p>Which dimension fields are present depends on the {@code dimensions} that were requested
 * ({@code period}; {@code domainId}/{@code domainName}; {@code emailId};
 * {@code broadcastId}/{@code broadcastName}). Any unset dimension is {@code null}.</p>
 *
 * <p>Metric values (e.g. {@code delivered}, {@code opened}) are not modeled as fixed fields
 * since which ones are present depends on the requested {@code metrics}; they are exposed
 * through {@link #getMetrics()}.</p>
 */
public class EmailsMetricsDataRow {

    @JsonProperty("period")
    private String period;

    @JsonProperty("domain_id")
    private String domainId;

    @JsonProperty("domain_name")
    private String domainName;

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("broadcast_id")
    private String broadcastId;

    @JsonProperty("broadcast_name")
    private String broadcastName;

    private final Map<String, Object> metrics = new LinkedHashMap<>();

    /**
     * Default constructor for deserialization.
     */
    public EmailsMetricsDataRow() {
    }

    /**
     * Gets the time bucket for this row, present when {@code period} was a requested dimension.
     *
     * @return The period bucket (e.g. {@code 2026-07-01}), or {@code null}.
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Gets the sending domain ID for this row, present when {@code domain} was a requested
     * dimension.
     *
     * @return The domain ID, or {@code null}.
     */
    public String getDomainId() {
        return domainId;
    }

    /**
     * Gets the sending domain name for this row, present when {@code domain} was a requested
     * dimension.
     *
     * @return The domain name, or {@code null}.
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * Gets the email ID for this row, present when {@code email} was a requested dimension.
     *
     * @return The email ID, or {@code null}.
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * Gets the broadcast ID for this row, present when {@code broadcast} was a requested
     * dimension.
     *
     * @return The broadcast ID, or {@code null}.
     */
    public String getBroadcastId() {
        return broadcastId;
    }

    /**
     * Gets the broadcast name for this row, present when {@code broadcast} was a requested
     * dimension.
     *
     * @return The broadcast name, or {@code null}.
     */
    public String getBroadcastName() {
        return broadcastName;
    }

    /**
     * Gets the requested metric values for this row (e.g. {@code delivered}, {@code opened}),
     * keyed by metric name.
     *
     * @return The metric values for this row.
     */
    @JsonAnyGetter
    public Map<String, Object> getMetrics() {
        return metrics;
    }

    @JsonAnySetter
    void setMetric(String name, Object value) {
        metrics.put(name, value);
    }
}
