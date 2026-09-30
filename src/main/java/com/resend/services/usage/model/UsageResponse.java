package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the response from {@code GET /usage}, the caller's account-level usage and quota data.
 */
public class UsageResponse {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("emails")
    private @Nullable UsageEmails emails;

    @JsonProperty("contacts")
    private @Nullable UsageQuota contacts;

    @JsonProperty("segments")
    private @Nullable UsageQuota segments;

    @JsonProperty("broadcasts")
    private @Nullable UsageQuota broadcasts;

    @JsonProperty("ai_credits")
    private @Nullable UsageAiCredits aiCredits;

    @JsonProperty("automation_runs")
    private @Nullable UsageAutomationRuns automationRuns;

    @JsonProperty("domains")
    private @Nullable UsageQuota domains;

    @JsonProperty("rate_limit")
    private @Nullable UsageRateLimit rateLimit;

    /**
     * Default constructor for deserialization.
     */
    public UsageResponse() {
    }

    /**
     * Gets the object type ({@code usage}).
     *
     * @return The object type, or {@code null} if the API omitted the field.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Gets the email usage, broken down by daily and monthly periods.
     *
     * @return The email usage.
     */
    public @Nullable UsageEmails getEmails() {
        return emails;
    }

    /**
     * Gets the contacts usage and quota.
     *
     * @return The contacts usage.
     */
    public @Nullable UsageQuota getContacts() {
        return contacts;
    }

    /**
     * Gets the segments usage and quota.
     *
     * @return The segments usage.
     */
    public @Nullable UsageQuota getSegments() {
        return segments;
    }

    /**
     * Gets the broadcasts usage and quota.
     *
     * @return The broadcasts usage.
     */
    public @Nullable UsageQuota getBroadcasts() {
        return broadcasts;
    }

    /**
     * Gets the AI credits usage.
     *
     * @return The AI credits usage.
     */
    public @Nullable UsageAiCredits getAiCredits() {
        return aiCredits;
    }

    /**
     * Gets the automation runs usage.
     *
     * @return The automation runs usage.
     */
    public @Nullable UsageAutomationRuns getAutomationRuns() {
        return automationRuns;
    }

    /**
     * Gets the domains usage and quota.
     *
     * @return The domains usage.
     */
    public @Nullable UsageQuota getDomains() {
        return domains;
    }

    /**
     * Gets the account's current API rate limit.
     *
     * @return The rate limit.
     */
    public @Nullable UsageRateLimit getRateLimit() {
        return rateLimit;
    }
}
