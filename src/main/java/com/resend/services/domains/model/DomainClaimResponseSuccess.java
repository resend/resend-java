package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a domain claim response returned by the claim, get, and verify claim endpoints.
 */
public class DomainClaimResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;

    @JsonProperty("domain_id")
    private String domainId;

    @JsonProperty("region")
    private String region;

    @JsonProperty("record")
    private DomainClaimRecord record;

    @JsonProperty("blocked_reason")
    private String blockedReason;

    @JsonProperty("failure_reason")
    private String failureReason;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("expires_at")
    private String expiresAt;

    /**
     * Default constructor.
     */
    public DomainClaimResponseSuccess() {
    }

    /**
     * Constructs a DomainClaimResponseSuccess with all fields.
     *
     * @param object        The object type identifier.
     * @param id            The claim ID.
     * @param name          The domain name.
     * @param status        The claim status.
     * @param domainId      The placeholder domain ID.
     * @param region        The region.
     * @param record        The TXT DNS record for verification.
     * @param blockedReason The reason the claim is blocked, if any.
     * @param failureReason The reason the claim failed, if any.
     * @param createdAt     The creation timestamp.
     * @param expiresAt     The expiration timestamp.
     */
    public DomainClaimResponseSuccess(final String object,
                                      final String id,
                                      final String name,
                                      final String status,
                                      final String domainId,
                                      final String region,
                                      final DomainClaimRecord record,
                                      final String blockedReason,
                                      final String failureReason,
                                      final String createdAt,
                                      final String expiresAt) {
        this.object = object;
        this.id = id;
        this.name = name;
        this.status = status;
        this.domainId = domainId;
        this.region = region;
        this.record = record;
        this.blockedReason = blockedReason;
        this.failureReason = failureReason;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    /**
     * Get the object type identifier.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the claim ID.
     *
     * @return The claim ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the domain name.
     *
     * @return The domain name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the claim status.
     *
     * @return The claim status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the placeholder domain ID.
     *
     * @return The domain ID.
     */
    public String getDomainId() {
        return domainId;
    }

    /**
     * Get the region.
     *
     * @return The region.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Get the TXT DNS record for verification.
     *
     * @return The DNS record.
     */
    public DomainClaimRecord getRecord() {
        return record;
    }

    /**
     * Get the reason the claim is blocked, if any.
     *
     * @return The blocked reason.
     */
    public String getBlockedReason() {
        return blockedReason;
    }

    /**
     * Get the reason the claim failed, if any.
     *
     * @return The failure reason.
     */
    public String getFailureReason() {
        return failureReason;
    }

    /**
     * Get the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Get the expiration timestamp.
     *
     * @return The expiration timestamp.
     */
    public String getExpiresAt() {
        return expiresAt;
    }
}
