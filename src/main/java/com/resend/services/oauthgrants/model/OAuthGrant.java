package com.resend.services.oauthgrants.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents an OAuth grant for the authenticated team.
 */
public class OAuthGrant {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("client_id")
    private @Nullable String clientId;

    @JsonProperty("scopes")
    private @Nullable List<String> scopes;

    @JsonProperty("resource")
    private @Nullable String resource;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("revoked_at")
    private @Nullable String revokedAt;

    @JsonProperty("revoked_reason")
    private @Nullable String revokedReason;

    @JsonProperty("client")
    private @Nullable OAuthGrantClient client;

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
    public OAuthGrant(@Nullable String id, @Nullable String clientId, @Nullable List<String> scopes, @Nullable String resource, @Nullable String createdAt,
                       @Nullable String revokedAt, @Nullable String revokedReason, @Nullable OAuthGrantClient client) {
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
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the unique identifier of the OAuth client.
     *
     * @return the OAuth client ID
     */
    public @Nullable String getClientId() {
        return clientId;
    }

    /**
     * Gets the scopes granted to the OAuth client.
     *
     * @return the granted scopes
     */
    public @Nullable List<String> getScopes() {
        return scopes;
    }

    /**
     * Gets the resource the grant is limited to, if any.
     *
     * @return the resource, or {@code null} if not limited
     */
    public @Nullable String getResource() {
        return resource;
    }

    /**
     * Gets the creation timestamp of the OAuth grant.
     *
     * @return the creation timestamp
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the revocation timestamp of the OAuth grant.
     *
     * @return the revocation timestamp, or {@code null} if still active
     */
    public @Nullable String getRevokedAt() {
        return revokedAt;
    }

    /**
     * Gets the reason the OAuth grant was revoked.
     *
     * @return the revocation reason, or {@code null} if still active
     */
    public @Nullable String getRevokedReason() {
        return revokedReason;
    }

    /**
     * Gets the OAuth client associated with the grant.
     *
     * @return the OAuth client
     */
    public @Nullable OAuthGrantClient getClient() {
        return client;
    }
}
