package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a successful response for listing attachments.
 */
public class ListAttachmentsResponse {

    @JsonProperty("object")
    private String object;

    @JsonProperty("data")
    private List<AttachmentResponse> data;

    @JsonProperty("has_more")
    private Boolean hasMore;

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
    public ListAttachmentsResponse(final List<AttachmentResponse> data, final String object, final Boolean hasMore) {
        this.data = data;
        this.object = object;
        this.hasMore = hasMore;
    }

    /**
     * Gets the list of attachments.
     *
     * @return The list of attachments.
     */
    public List<AttachmentResponse> getData() {
        return data;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
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
