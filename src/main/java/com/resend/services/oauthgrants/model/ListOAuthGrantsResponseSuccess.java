package com.resend.services.oauthgrants.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing OAuth grants.
 */
public class ListOAuthGrantsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<OAuthGrant> data;

    /**
     * Default constructor.
     */
    public ListOAuthGrantsResponseSuccess() {
    }

    /**
     * Constructs a ListOAuthGrantsResponseSuccess.
     *
     * @param object  The object type ("list").
     * @param hasMore Whether there are more items available for pagination.
     * @param data    The list of OAuth grants.
     */
    public ListOAuthGrantsResponseSuccess(String object, Boolean hasMore, List<OAuthGrant> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    /**
     * Gets the object type.
     *
     * @return the object type ("list")
     */
    public String getObject() {
        return object;
    }

    /**
     * Checks if there are more items available for pagination.
     *
     * @return true if more items are available, false otherwise
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Gets the list of OAuth grants.
     *
     * @return the list of OAuth grants
     */
    public List<OAuthGrant> getData() {
        return data;
    }
}
