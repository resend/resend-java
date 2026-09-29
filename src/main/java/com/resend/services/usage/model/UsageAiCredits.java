package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the {@code ai_credits} section of the usage response.
 */
public class UsageAiCredits {

    @JsonProperty("used")
    private @Nullable Integer used;

    @JsonProperty("limit")
    private @Nullable Integer limit;

    @JsonProperty("next_increase_at")
    private @Nullable String nextIncreaseAt;

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
    public UsageAiCredits(@Nullable Integer used, @Nullable Integer limit, @Nullable String nextIncreaseAt) {
        this.used = used;
        this.limit = limit;
        this.nextIncreaseAt = nextIncreaseAt;
    }

    /**
     * Gets the amount of AI credits used.
     *
     * @return the amount used
     */
    public @Nullable Integer getUsed() { return used; }

    /**
     * Gets the maximum allowed amount.
     *
     * @return the limit, or {@code null} when unlimited
     */
    public @Nullable Integer getLimit() { return limit; }

    /**
     * Gets the timestamp of the next scheduled credit increase.
     *
     * @return the next increase timestamp, or {@code null} if none is scheduled
     */
    public @Nullable String getNextIncreaseAt() { return nextIncreaseAt; }
}
