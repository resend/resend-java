package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a successful response for listing e-mails.
 */
public class ListEmailsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("data")
    private List<Email> data;

    @JsonProperty("has_more")
    private Boolean hasMore;

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
    public ListEmailsResponseSuccess (final List<Email> data, final String object, final Boolean hasMore) {
        this.data = data;
        this.object = object;
        this.hasMore = hasMore;
    }

    /**
     * Gets the list of emails.
     *
     * @return The list of emails.
     */
    public List<Email> getData() {
        return data;
    }

    /**
     * Gets the list of emails object.
     *
     * @return The list of emails object.
     */
    public String getObject() {
        return object;
    }

    /**
     * Gets the indicator whether there are more items available for pagination.
     *
     * @return Whether there are more items available for pagination.
     */
    public Boolean hasMore() {
        return hasMore;
    }
}

