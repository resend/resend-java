package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the {@code emails} section of the usage response, broken down by daily and monthly periods.
 */
public class UsageEmails {

    @JsonProperty("daily")
    private UsageEmailsPeriod daily;

    @JsonProperty("monthly")
    private UsageEmailsPeriod monthly;

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
    public UsageEmails(UsageEmailsPeriod daily, UsageEmailsPeriod monthly) {
        this.daily = daily;
        this.monthly = monthly;
    }

    /**
     * Gets the daily email usage.
     *
     * @return the daily usage
     */
    public UsageEmailsPeriod getDaily() { return daily; }

    /**
     * Gets the monthly email usage.
     *
     * @return the monthly usage
     */
    public UsageEmailsPeriod getMonthly() { return monthly; }
}
