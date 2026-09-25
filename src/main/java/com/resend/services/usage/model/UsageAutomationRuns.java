package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the {@code automation_runs} section of the usage response.
 */
public class UsageAutomationRuns {

    @JsonProperty("used")
    private Integer used;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("resets_at")
    private String resetsAt;

    /**
     * Default constructor.
     */
    public UsageAutomationRuns() {
    }

    /**
     * Constructs a UsageAutomationRuns.
     *
     * @param used     The number of automation runs used.
     * @param limit    The maximum allowed amount.
     * @param resetsAt The timestamp at which this usage resets.
     */
    public UsageAutomationRuns(Integer used, Integer limit, String resetsAt) {
        this.used = used;
        this.limit = limit;
        this.resetsAt = resetsAt;
    }

    /**
     * Gets the number of automation runs used.
     *
     * @return the amount used
     */
    public Integer getUsed() { return used; }

    /**
     * Gets the maximum allowed amount.
     *
     * @return the limit
     */
    public Integer getLimit() { return limit; }

    /**
     * Gets the timestamp at which this usage resets.
     *
     * @return the reset timestamp
     */
    public String getResetsAt() { return resetsAt; }
}
