package com.resend.services.receiving.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * Represents a response for listing email attachments.
 */
public class ListAttachmentsResponse {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    @JsonProperty("data")
    private @Nullable List<AttachmentDetails> data;

    /**
     * Default constructor.
     */
    public ListAttachmentsResponse() {
    }

    /**
     * Constructor with all fields.
     *
     * @param object The object type.
     * @param hasMore Whether there are more items.
     * @param data The list of attachments.
     */
    public ListAttachmentsResponse(@Nullable String object, @Nullable Boolean hasMore, @Nullable List<AttachmentDetails> data) {
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
     * Gets the list of attachments.
     *
     * @return The list of attachments.
     */
    public @Nullable List<AttachmentDetails> getData() {
        return data;
    }

    /**
     * Sets the list of attachments.
     *
     * @param data The list of attachments.
     */
    public void setData(@Nullable List<AttachmentDetails> data) {
        this.data = data;
    }
}
