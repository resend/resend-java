package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the time bucket size used for the {@code period} dimension of emails metrics.
 */
public enum MetricsGranularity {
    /** Buckets metrics by hour. */
    HOURLY("hourly"),
    /** Buckets metrics by day. */
    DAILY("daily"),
    /** Buckets metrics by week. */
    WEEKLY("weekly"),
    /** Buckets metrics by month. */
    MONTHLY("monthly");

    private final String value;

    MetricsGranularity(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the granularity.
     *
     * @return The granularity value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates a MetricsGranularity from a string value.
     *
     * @param value The string value.
     * @return The corresponding MetricsGranularity.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static MetricsGranularity fromValue(String value) {
        for (MetricsGranularity granularity : MetricsGranularity.values()) {
            if (granularity.value.equals(value)) {
                return granularity;
            }
        }
        throw new IllegalArgumentException("Unknown granularity: " + value);
    }
}
