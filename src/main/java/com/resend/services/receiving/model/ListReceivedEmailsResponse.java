package com.resend.services.receiving.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * Represents a response for listing received emails.
 */
public class ListReceivedEmailsResponse {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    @JsonProperty("data")
    private @Nullable List<ReceivedEmailSummary> data;

    /**
     * Default constructor.
     */
    public ListReceivedEmailsResponse() {
    }

    /**
     * Constructor with all fields.
     *
     * @param object The object type.
     * @param hasMore Whether there are more items.
     * @param data The list of received emails.
     */
    public ListReceivedEmailsResponse(@Nullable String object, @Nullable Boolean hasMore, @Nullable List<ReceivedEmailSummary> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(@Nullable String object) {
        this.object = object;
    }

    /**
     * Gets whether there are more items available for pagination.
     *
     * @return True if there are more items.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }

    /**
     * Sets whether there are more items.
     *
     * @param hasMore Whether there are more items.
     */
    public void setHasMore(@Nullable Boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * Gets the list of received emails.
     *
     * @return The list of received emails.
     */
    public @Nullable List<ReceivedEmailSummary> getData() {
        return data;
    }

    /**
     * Sets the list of received emails.
     *
     * @param data The list of received emails.
     */
    public void setData(@Nullable List<ReceivedEmailSummary> data) {
        this.data = data;
    }
}
