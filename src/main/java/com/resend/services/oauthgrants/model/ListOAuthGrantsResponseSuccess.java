package com.resend.services.oauthgrants.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a successful response for listing OAuth grants.
 */
public class ListOAuthGrantsResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    @JsonProperty("data")
    private @Nullable List<OAuthGrant> data;

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
    public ListOAuthGrantsResponseSuccess(@Nullable String object, @Nullable Boolean hasMore, @Nullable List<OAuthGrant> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    /**
     * Gets the object type.
     *
     * @return the object type ("list")
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Checks if there are more items available for pagination.
     *
     * @return true if more items are available, false otherwise
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }

    /**
     * Gets the list of OAuth grants.
     *
     * @return the list of OAuth grants
     */
    public @Nullable List<OAuthGrant> getData() {
        return data;
    }
}
