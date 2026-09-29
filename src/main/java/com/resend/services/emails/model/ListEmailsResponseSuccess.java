package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * Represents a successful response for listing e-mails.
 */
public class ListEmailsResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("data")
    private @Nullable List<Email> data;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    /**
     * Default constructor
     */
    public ListEmailsResponseSuccess () {
    }

    /**
     * Constructs a successful response for listing e-mails.
     *
     * @param data The list of emails.
     * @param object The object of the list emails.
     * @param hasMore Whether there are more emails available for pagination.
     */
    public ListEmailsResponseSuccess (final @Nullable List<Email> data, final @Nullable String object, final @Nullable Boolean hasMore) {
        this.data = data;
        this.object = object;
        this.hasMore = hasMore;
    }

    /**
     * Gets the list of emails.
     *
     * @return The list of emails.
     */
    public @Nullable List<Email> getData() {
        return data;
    }

    /**
     * Gets the list of emails object.
     *
     * @return The list of emails object.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Gets the indicator whether there are more items available for pagination.
     *
     * @return Whether there are more items available for pagination.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }
}

