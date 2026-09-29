package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * Represents a successful response for listing attachments.
 */
public class ListAttachmentsResponse {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("data")
    private @Nullable List<AttachmentResponse> data;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    /**
     * Default constructor
     */
    public ListAttachmentsResponse() {
    }

    /**
     * Constructs a successful response for listing attachments.
     *
     * @param data The list of attachments.
     * @param object The object type, always "list".
     * @param hasMore Whether there are more attachments available for pagination.
     */
    public ListAttachmentsResponse(final @Nullable List<AttachmentResponse> data, final @Nullable String object, final @Nullable Boolean hasMore) {
        this.data = data;
        this.object = object;
        this.hasMore = hasMore;
    }

    /**
     * Gets the list of attachments.
     *
     * @return The list of attachments.
     */
    public @Nullable List<AttachmentResponse> getData() {
        return data;
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
     * Gets the indicator whether there are more items available for pagination.
     *
     * @return Whether there are more items available for pagination.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }
}
