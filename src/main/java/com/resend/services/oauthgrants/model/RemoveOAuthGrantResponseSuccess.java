package com.resend.services.oauthgrants.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for revoking an OAuth grant.
 */
public class RemoveOAuthGrantResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("revoked_at")
    private String revokedAt;

    @JsonProperty("revoked_reason")
    private String revokedReason;

    /**
     * Default constructor.
     */
    public RemoveOAuthGrantResponseSuccess() {
    }

    /**
     * Constructs a RemoveOAuthGrantResponseSuccess.
     *
     * @param object        The object type ("oauth_grant").
     * @param id            The ID of the revoked OAuth grant.
     * @param revokedAt     The revocation timestamp of the OAuth grant.
     * @param revokedReason The reason the OAuth grant was revoked.
     */
    public RemoveOAuthGrantResponseSuccess(String object, String id, String revokedAt, String revokedReason) {
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
    public String getObject() {
        return object;
    }

    /**
     * Gets the ID of the revoked OAuth grant.
     *
     * @return the OAuth grant ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the revocation timestamp of the OAuth grant.
     *
     * @return the revocation timestamp
     */
    public String getRevokedAt() {
        return revokedAt;
    }

    /**
     * Gets the reason the OAuth grant was revoked.
     *
     * @return the revocation reason
     */
    public String getRevokedReason() {
        return revokedReason;
    }
}
