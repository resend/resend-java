package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the {@code rate_limit} section of the usage response.
 */
public class UsageRateLimit {

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("duration")
    private String duration;

    /**
     * Default constructor.
     */
    public UsageRateLimit() {
    }

    /**
     * Constructs a UsageRateLimit.
     *
     * @param limit    The maximum number of requests allowed per duration window.
     * @param duration The duration of the rate limit window (e.g. {@code "1000ms"}).
     */
    public UsageRateLimit(Integer limit, String duration) {
        this.limit = limit;
        this.duration = duration;
    }

    /**
     * Gets the maximum number of requests allowed per duration window.
     *
     * @return the rate limit
     */
    public Integer getLimit() { return limit; }

    /**
     * Gets the duration of the rate limit window.
     *
     * @return the duration (e.g. {@code "1000ms"})
     */
    public String getDuration() { return duration; }
}
