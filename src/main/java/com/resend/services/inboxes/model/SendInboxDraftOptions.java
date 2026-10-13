package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the optional body of a request to send a draft. Its only field is {@code headers}.
 */
public class SendInboxDraftOptions {

    @JsonProperty("headers")
    private final Map<String, String> headers;

    private SendInboxDraftOptions(Builder builder) {
        this.headers = builder.headers;
    }

    /**
     * Retrieves the custom headers of the email.
     *
     * @return The custom headers of the email.
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Create a new builder instance for constructing SendInboxDraftOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing SendInboxDraftOptions objects.
     */
    public static class Builder {
        private Map<String, String> headers;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set the custom headers of the email. The {@code In-Reply-To}, {@code References} and {@code Reply-To} headers are set by Resend and cannot be set here. Sending one of them, in any letter case, returns a 422.
         *
         * @param headers The map of custom headers for the email.
         * @return This builder instance for method chaining.
         */
        public Builder headers(Map<String, String> headers) {
            this.headers = headers == null ? null : new LinkedHashMap<>(headers);
            return this;
        }

        /**
         * Add a single custom header to the email headers. The {@code In-Reply-To}, {@code References} and {@code Reply-To} headers are set by Resend and cannot be set here. Sending one of them, in any letter case, returns a 422.
         *
         * @param key The key of the custom header.
         * @param value The value of the custom header.
         * @return This builder instance for method chaining.
         */
        public Builder addHeader(String key, String value) {
            if (this.headers == null) {
                this.headers = new LinkedHashMap<>();
            }
            this.headers.put(key, value);
            return this;
        }

        /**
         * Build a new SendInboxDraftOptions object.
         *
         * @return A new SendInboxDraftOptions object.
         */
        public SendInboxDraftOptions build() {
            return new SendInboxDraftOptions(this);
        }
    }
}
