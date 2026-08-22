package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents a dimension that emails metrics can be broken down by.
 *
 * <p>{@code EMAIL} and {@code BROADCAST} cannot be combined; {@link GetEmailsMetricsOptions.Builder#build()}
 * validates this client-side.</p>
 */
public enum MetricsDimension {
    /** Breaks metrics down by time bucket, sized by the requested granularity. */
    PERIOD("period"),
    /** Breaks metrics down by sending domain. */
    DOMAIN("domain"),
    /** Breaks metrics down by individual email. */
    EMAIL("email"),
    /** Breaks metrics down by broadcast. */
    BROADCAST("broadcast");

    private final String value;

    MetricsDimension(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the dimension.
     *
     * @return The dimension value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates a MetricsDimension from a string value.
     *
     * @param value The string value.
     * @return The corresponding MetricsDimension.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static MetricsDimension fromValue(String value) {
        for (MetricsDimension dimension : MetricsDimension.values()) {
            if (dimension.value.equals(value)) {
                return dimension;
            }
        }
        throw new IllegalArgumentException("Unknown dimension: " + value);
    }
}
