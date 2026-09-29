package com.resend.services.oauthgrants.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a successful response for revoking an OAuth grant.
 */
public class RevokeOAuthGrantResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("revoked_at")
    private @Nullable String revokedAt;

    @JsonProperty("revoked_reason")
    private @Nullable String revokedReason;

    /**
     * Default constructor.
     */
    public RevokeOAuthGrantResponseSuccess() {
    }

    /**
     * Constructs a RevokeOAuthGrantResponseSuccess.
     *
     * @param object        The object type ("oauth_grant").
     * @param id            The ID of the revoked OAuth grant.
     * @param revokedAt     The revocation timestamp of the OAuth grant.
     * @param revokedReason The reason the OAuth grant was revoked.
     */
    public RevokeOAuthGrantResponseSuccess(@Nullable String object, @Nullable String id, @Nullable String revokedAt, @Nullable String revokedReason) {
        this.object = object;
        this.id = id;
        this.revokedAt = revokedAt;
        this.revokedReason = revokedReason;
    }

    /**
     * Gets the object type.
     *
     * @return the object type ("oauth_grant")
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Gets the ID of the revoked OAuth grant.
     *
     * @return the OAuth grant ID
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the revocation timestamp of the OAuth grant.
     *
     * @return the revocation timestamp
     */
    public @Nullable String getRevokedAt() {
        return revokedAt;
    }

    /**
     * Gets the reason the OAuth grant was revoked.
     *
     * @return the revocation reason
     */
    public @Nullable String getRevokedReason() {
        return revokedReason;
    }
}
