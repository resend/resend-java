package com.resend.services.oauthgrants.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents an OAuth grant for the authenticated team.
 */
public class OAuthGrant {

    @JsonProperty("id")
    private String id;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("scopes")
    private List<String> scopes;

    @JsonProperty("resource")
    private String resource;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("revoked_at")
    private String revokedAt;

    @JsonProperty("revoked_reason")
    private String revokedReason;

    @JsonProperty("client")
    private OAuthGrantClient client;

    /**
     * Default constructor.
     */
    public OAuthGrant() {
    }

    /**
     * Constructs an OAuthGrant.
     *
     * @param id            The unique identifier of the OAuth grant.
     * @param clientId      The unique identifier of the OAuth client.
     * @param scopes        The scopes granted to the OAuth client.
     * @param resource      The resource the grant is limited to, if any.
     * @param createdAt     The creation timestamp of the OAuth grant.
     * @param revokedAt     The revocation timestamp of the OAuth grant, if revoked.
     * @param revokedReason The reason the OAuth grant was revoked, if revoked.
     * @param client        The OAuth client associated with the grant.
     */
    public OAuthGrant(String id, String clientId, List<String> scopes, String resource, String createdAt,
                       String revokedAt, String revokedReason, OAuthGrantClient client) {
        this.id = id;
        this.clientId = clientId;
        this.scopes = scopes;
        this.resource = resource;
        this.createdAt = createdAt;
        this.revokedAt = revokedAt;
        this.revokedReason = revokedReason;
        this.client = client;
    }

    /**
     * Gets the unique identifier of the OAuth grant.
     *
     * @return the OAuth grant ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the unique identifier of the OAuth client.
     *
     * @return the OAuth client ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Gets the scopes granted to the OAuth client.
     *
     * @return the granted scopes
     */
    public List<String> getScopes() {
        return scopes;
    }

    /**
     * Gets the resource the grant is limited to, if any.
     *
     * @return the resource, or {@code null} if not limited
     */
    public String getResource() {
        return resource;
    }

    /**
     * Gets the creation timestamp of the OAuth grant.
     *
     * @return the creation timestamp
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the revocation timestamp of the OAuth grant.
     *
     * @return the revocation timestamp, or {@code null} if still active
     */
    public String getRevokedAt() {
        return revokedAt;
    }

    /**
     * Gets the reason the OAuth grant was revoked.
     *
     * @return the revocation reason, or {@code null} if still active
     */
    public String getRevokedReason() {
        return revokedReason;
    }

    /**
     * Gets the OAuth client associated with the grant.
     *
     * @return the OAuth client
     */
    public OAuthGrantClient getClient() {
        return client;
    }
}
