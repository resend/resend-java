package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents a metric that can be requested from the {@code GET /emails/metrics} endpoint.
 *
 * <p><strong>Note:</strong> the emails metrics endpoint is currently in beta and might change
 * before GA.</p>
 */
public enum MetricName {
    /** Number of emails received for processing. */
    RECEIVED("received"),
    /** Number of emails delivered. */
    DELIVERED("delivered"),
    /** Number of emails that received a spam complaint. */
    COMPLAINED("complained"),
    /** Number of emails suppressed before sending. */
    SUPPRESSED("suppressed"),
    /** Number of emails that bounced, of any kind. */
    BOUNCED("bounced"),
    /** Number of emails that bounced transiently. */
    BOUNCED_TRANSIENT("bounced_transient"),
    /** Number of emails that bounced permanently. */
    BOUNCED_PERMANENT("bounced_permanent"),
    /** Number of emails that bounced for an undetermined reason. */
    BOUNCED_UNDETERMINED("bounced_undetermined"),
    /** Number of emails opened, counting every open event. */
    OPENED("opened"),
    /** Number of link clicks, counting every click event. */
    CLICKED("clicked"),
    /** Number of unsubscribes. */
    UNSUBSCRIBED("unsubscribed"),
    /** Number of emails whose delivery was delayed. */
    DELIVERY_DELAYED("delivery_delayed"),
    /** Number of emails that failed to send. */
    FAILED("failed"),
    /** Number of emails sent. */
    SENT("sent"),
    /** Number of emails opened at least once. */
    UNIQUE_OPENED("unique_opened"),
    /** Number of emails clicked at least once. */
    UNIQUE_CLICKED("unique_clicked"),
    /** Ratio of delivered emails to sent emails. */
    DELIVERY_RATE("delivery_rate"),
    /** Ratio of emails opened to emails delivered. */
    OPEN_RATE("open_rate"),
    /** Ratio of emails clicked to emails delivered. */
    CLICK_RATE("click_rate"),
    /** Ratio of emails bounced to emails sent. */
    BOUNCE_RATE("bounce_rate"),
    /** Ratio of complaints to emails delivered. */
    COMPLAINT_RATE("complaint_rate"),
    /** Ratio of unsubscribes to emails delivered. */
    UNSUBSCRIBE_RATE("unsubscribe_rate");

    private final String value;

    MetricName(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the metric.
     *
     * @return The metric value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates a MetricName from a string value.
     *
     * @param value The string value.
     * @return The corresponding MetricName.
     * @throws IllegalArgumentException If the value is unknown.
     */
    @JsonCreator
    public static MetricName fromValue(String value) {
        for (MetricName metric : MetricName.values()) {
            if (metric.value.equals(value)) {
                return metric;
            }
        }
        throw new IllegalArgumentException("Unknown metric: " + value);
    }
}
