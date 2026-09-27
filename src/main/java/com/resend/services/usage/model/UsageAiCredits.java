package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the {@code ai_credits} section of the usage response.
 */
public class UsageAiCredits {

    @JsonProperty("used")
    private Integer used;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("next_increase_at")
    private String nextIncreaseAt;

    /**
     * Default constructor.
     */
    public UsageAiCredits() {
    }

    /**
     * Constructs a UsageAiCredits.
     *
     * @param used           The amount of AI credits used.
     * @param limit          The maximum allowed amount, or {@code null} when unlimited.
     * @param nextIncreaseAt The timestamp of the next scheduled credit increase, or {@code null} if none is scheduled.
     */
    public UsageAiCredits(Integer used, Integer limit, String nextIncreaseAt) {
        this.used = used;
        this.limit = limit;
        this.nextIncreaseAt = nextIncreaseAt;
    }

    /**
     * Gets the amount of AI credits used.
     *
     * @return the amount used
     */
    public Integer getUsed() { return used; }

    /**
     * Gets the maximum allowed amount.
     *
     * @return the limit, or {@code null} when unlimited
     */
    public Integer getLimit() { return limit; }

    /**
     * Gets the timestamp of the next scheduled credit increase.
     *
     * @return the next increase timestamp, or {@code null} if none is scheduled
     */
    public String getNextIncreaseAt() { return nextIncreaseAt; }
}
