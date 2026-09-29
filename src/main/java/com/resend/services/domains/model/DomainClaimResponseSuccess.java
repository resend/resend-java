package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a domain claim response returned by the claim, get, and verify claim endpoints.
 */
public class DomainClaimResponseSuccess extends AbstractDomain {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("domain_id")
    private @Nullable String domainId;

    @JsonProperty("record")
    private @Nullable DomainClaimRecord record;

    @JsonProperty("blocked_reason")
    private @Nullable String blockedReason;

    @JsonProperty("failure_reason")
    private @Nullable String failureReason;

    @JsonProperty("expires_at")
    private @Nullable String expiresAt;

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
    public DomainClaimResponseSuccess(final @Nullable String object,
                                      final @Nullable String id,
                                      final @Nullable String name,
                                      final @Nullable String status,
                                      final @Nullable String domainId,
                                      final @Nullable String region,
                                      final @Nullable DomainClaimRecord record,
                                      final @Nullable String blockedReason,
                                      final @Nullable String failureReason,
                                      final @Nullable String createdAt,
                                      final @Nullable String expiresAt) {
        super(id, name, createdAt, status, region);
        this.object = object;
        this.domainId = domainId;
        this.record = record;
        this.blockedReason = blockedReason;
        this.failureReason = failureReason;
        this.expiresAt = expiresAt;
    }

    /**
     * Get the object type identifier.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Get the placeholder domain ID.
     *
     * @return The domain ID.
     */
    public @Nullable String getDomainId() {
        return domainId;
    }

    /**
     * Get the TXT DNS record for verification.
     *
     * @return The DNS record.
     */
    public @Nullable DomainClaimRecord getRecord() {
        return record;
    }

    /**
     * Get the reason the claim is blocked, if any.
     *
     * @return The blocked reason.
     */
    public @Nullable String getBlockedReason() {
        return blockedReason;
    }

    /**
     * Get the reason the claim failed, if any.
     *
     * @return The failure reason.
     */
    public @Nullable String getFailureReason() {
        return failureReason;
    }

    /**
     * Get the expiration timestamp.
     *
     * @return The expiration timestamp.
     */
    public @Nullable String getExpiresAt() {
        return expiresAt;
    }
}
