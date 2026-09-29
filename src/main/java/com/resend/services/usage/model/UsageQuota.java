package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a simple used/limit quota, shared by several sections of the usage response
 * ({@code contacts}, {@code segments}, {@code broadcasts} and {@code domains}).
 */
public class UsageQuota {

    @JsonProperty("used")
    private @Nullable Integer used;

    @JsonProperty("limit")
    private @Nullable Integer limit;

    /**
     * Default constructor.
     */
    public UsageQuota() {
    }

    /**
     * Constructs a UsageQuota.
     *
     * @param used  The amount currently used.
     * @param limit The maximum allowed amount, or {@code null} when unlimited.
     */
    public UsageQuota(@Nullable Integer used, @Nullable Integer limit) {
        this.used = used;
        this.limit = limit;
    }

    /**
     * Gets the amount currently used.
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
}
