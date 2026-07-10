package com.resend.services.oauthgrants.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the OAuth client associated with an OAuth grant.
 */
public class OAuthGrantClient {

    @JsonProperty("name")
    private String name;

    @JsonProperty("logo_uri")
    private String logoUri;

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
    public OAuthGrantClient(String name, String logoUri) {
        this.name = name;
        this.logoUri = logoUri;
    }

    /**
     * Gets the name of the OAuth client.
     *
     * @return the client name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the logo URI of the OAuth client.
     *
     * @return the client logo URI
     */
    public String getLogoUri() {
        return logoUri;
    }
}
