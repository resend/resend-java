package com.resend.services.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response from {@code GET /usage}, the caller's account-level usage and quota data.
 */
public class UsageResponse {

    @JsonProperty("object")
    private String object;

    @JsonProperty("emails")
    private UsageEmails emails;

    @JsonProperty("contacts")
    private UsageQuota contacts;

    @JsonProperty("segments")
    private UsageQuota segments;

    @JsonProperty("broadcasts")
    private UsageQuota broadcasts;

    @JsonProperty("ai_credits")
    private UsageAiCredits aiCredits;

    @JsonProperty("automation_runs")
    private UsageAutomationRuns automationRuns;

    @JsonProperty("domains")
    private UsageQuota domains;

    @JsonProperty("rate_limit")
    private UsageRateLimit rateLimit;

    /**
     * Default constructor for deserialization.
     */
    public UsageResponse() {
    }

    /**
     * Gets the object type, always {@code usage}.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Gets the email usage, broken down by daily and monthly periods.
     *
     * @return The email usage.
     */
    public UsageEmails getEmails() {
        return emails;
    }

    /**
     * Gets the contacts usage and quota.
     *
     * @return The contacts usage.
     */
    public UsageQuota getContacts() {
        return contacts;
    }

    /**
     * Gets the segments usage and quota.
     *
     * @return The segments usage.
     */
    public UsageQuota getSegments() {
        return segments;
    }

    /**
     * Gets the broadcasts usage and quota.
     *
     * @return The broadcasts usage.
     */
    public UsageQuota getBroadcasts() {
        return broadcasts;
    }

    /**
     * Gets the AI credits usage.
     *
     * @return The AI credits usage.
     */
    public UsageAiCredits getAiCredits() {
        return aiCredits;
    }

    /**
     * Gets the automation runs usage.
     *
     * @return The automation runs usage.
     */
    public UsageAutomationRuns getAutomationRuns() {
        return automationRuns;
    }

    /**
     * Gets the domains usage and quota.
     *
     * @return The domains usage.
     */
    public UsageQuota getDomains() {
        return domains;
    }

    /**
     * Gets the account's current API rate limit.
     *
     * @return The rate limit.
     */
    public UsageRateLimit getRateLimit() {
        return rateLimit;
    }
}
