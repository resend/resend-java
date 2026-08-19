package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the share email response.
 */
public class ShareEmailResponse extends EmailResponse {

    /**
     * The shareable link URL for the email.
     */
    @JsonProperty("url")
    private String url;

    /**
     * Constructs a new instance of {@code ShareEmailResponse}.
     */
    public ShareEmailResponse() {
    }

    /**
     * Constructs a ShareEmailResponse with the provided ID, object and URL.
     *
     * @param id The ID associated with the email.
     * @param object The resource object.
     * @param url The shareable link URL for the email.
     */
    public ShareEmailResponse(String id, String object, String url) {
        super(id, object);
        this.url = url;
    }

    /**
     * Retrieves the shareable link URL for the email.
     *
     * @return The shareable link URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the shareable link URL for the email.
     *
     * @param url The shareable link URL to be set.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
