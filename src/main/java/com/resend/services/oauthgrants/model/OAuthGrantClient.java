package com.resend.services.oauthgrants.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the OAuth client associated with an OAuth grant.
 */
public class OAuthGrantClient {

    @JsonProperty("name")
    private @Nullable String name;

    @JsonProperty("logo_uri")
    private @Nullable String logoUri;

    /**
     * Default constructor.
     */
    public OAuthGrantClient() {
    }

    /**
     * Constructs an OAuthGrantClient.
     *
     * @param name    The name of the OAuth client.
     * @param logoUri The logo URI of the OAuth client.
     */
    public OAuthGrantClient(@Nullable String name, @Nullable String logoUri) {
        this.name = name;
        this.logoUri = logoUri;
    }

    /**
     * Gets the name of the OAuth client.
     *
     * @return the client name
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Gets the logo URI of the OAuth client.
     *
     * @return the client logo URI
     */
    public @Nullable String getLogoUri() {
        return logoUri;
    }
}
