package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the email usage for a single period (daily or monthly).
 */
public class UsageEmailsPeriod {

    @JsonProperty("used")
    private @Nullable Integer used;

    @JsonProperty("limit")
    private @Nullable Integer limit;

    @JsonProperty("sent")
    private @Nullable Integer sent;

    @JsonProperty("received")
    private @Nullable Integer received;

    @JsonProperty("resets_at")
    private @Nullable String resetsAt;

    /**
     * Default constructor.
     */
    public UsageEmailsPeriod() {
    }

    /**
     * Constructs a UsageEmailsPeriod.
     *
     * @param used     The amount of emails used in this period.
     * @param limit    The maximum allowed amount, or {@code null} when unlimited.
     * @param sent     The number of emails sent in this period.
     * @param received The number of emails received in this period.
     * @param resetsAt The timestamp at which this period's usage resets.
     */
    public UsageEmailsPeriod(@Nullable Integer used, @Nullable Integer limit, @Nullable Integer sent, @Nullable Integer received, @Nullable String resetsAt) {
        this.used = used;
        this.limit = limit;
        this.sent = sent;
        this.received = received;
        this.resetsAt = resetsAt;
    }

    /**
     * Gets the amount of emails used in this period.
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
     * Gets the number of emails sent in this period.
     *
     * @return the number sent
     */
    public @Nullable Integer getSent() { return sent; }

    /**
     * Gets the number of emails received in this period.
     *
     * @return the number received
     */
    public @Nullable Integer getReceived() { return received; }

    /**
     * Gets the timestamp at which this period's usage resets.
     *
     * @return the reset timestamp
     */
    public @Nullable String getResetsAt() { return resetsAt; }
}
