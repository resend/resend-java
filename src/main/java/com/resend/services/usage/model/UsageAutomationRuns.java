package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the {@code automation_runs} section of the usage response.
 */
public class UsageAutomationRuns {

    @JsonProperty("used")
    private @Nullable Integer used;

    @JsonProperty("limit")
    private @Nullable Integer limit;

    @JsonProperty("resets_at")
    private @Nullable String resetsAt;

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
    public UsageAutomationRuns(@Nullable Integer used, @Nullable Integer limit, @Nullable String resetsAt) {
        this.used = used;
        this.limit = limit;
        this.resetsAt = resetsAt;
    }

    /**
     * Gets the number of automation runs used.
     *
     * @return the amount used
     */
    public @Nullable Integer getUsed() { return used; }

    /**
     * Gets the maximum allowed amount.
     *
     * @return the limit
     */
    public @Nullable Integer getLimit() { return limit; }

    /**
     * Gets the timestamp at which this usage resets.
     *
     * @return the reset timestamp
     */
    public @Nullable String getResetsAt() { return resetsAt; }
}
