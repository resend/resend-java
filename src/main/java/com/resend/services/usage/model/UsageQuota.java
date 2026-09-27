package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a simple used/limit quota, shared by several sections of the usage response
 * ({@code contacts}, {@code segments}, {@code broadcasts} and {@code domains}).
 */
public class UsageQuota {

    @JsonProperty("used")
    private Integer used;

    @JsonProperty("limit")
    private Integer limit;

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
    public UsageQuota(Integer used, Integer limit) {
        this.used = used;
        this.limit = limit;
    }

    /**
     * Gets the amount currently used.
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
}
