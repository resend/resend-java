package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the {@code emails} section of the usage response, broken down by daily and monthly periods.
 */
public class UsageEmails {

    @JsonProperty("daily")
    private @Nullable UsageEmailsPeriod daily;

    @JsonProperty("monthly")
    private @Nullable UsageEmailsPeriod monthly;

    /**
     * Default constructor.
     */
    public UsageEmails() {
    }

    /**
     * Constructs a UsageEmails.
     *
     * @param daily   The daily email usage.
     * @param monthly The monthly email usage.
     */
    public UsageEmails(@Nullable UsageEmailsPeriod daily, @Nullable UsageEmailsPeriod monthly) {
        this.daily = daily;
        this.monthly = monthly;
    }

    /**
     * Gets the daily email usage.
     *
     * @return the daily usage
     */
    public @Nullable UsageEmailsPeriod getDaily() { return daily; }

    /**
     * Gets the monthly email usage.
     *
     * @return the monthly usage
     */
    public @Nullable UsageEmailsPeriod getMonthly() { return monthly; }
}
