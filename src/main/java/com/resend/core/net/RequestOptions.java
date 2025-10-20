package com.resend.core.net;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a request to create a request options.
 */
public class RequestOptions {
    private final String idempotencyKey;
    private final Map<String, String> additionalHeaders;

    /**
     * Constructs a RequestOptions object using the provided builder.
     *
     * @param builder The builder to construct the RequestOptions.
     */
    public RequestOptions(Builder builder) {
        this.idempotencyKey = builder.idempotencyKey;
        this.additionalHeaders = Collections.unmodifiableMap(new HashMap<>(builder.additionalHeaders));
    }

    /**
     * Get the idempotency key.
     *
     * @return The idempotency key.
     */
    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    /**
     * Get the additional headers map.
     *
     * @return An unmodifiable map of additional headers.
     */
    public Map<String, String> getAdditionalHeaders() {
        return additionalHeaders;
    }

    /**
     * Create a new builder instance for constructing RequestOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing RequestOptions objects.
     */
    public static class Builder {
        private String idempotencyKey;
        private final Map<String, String> additionalHeaders;

        /**
         * Constructs a new Builder with empty additional headers map.
         */
        public Builder() {
            this.additionalHeaders = new HashMap<>();
        }

        /**
         * Set the idempotencyKey.
         *
         * @param idempotencyKey The idempotency key.
         * @return The builder instance.
         */
        public Builder setIdempotencyKey(String idempotencyKey) {
            this.idempotencyKey = idempotencyKey;
            return this;
        }

        /**
         * Add a custom header to the additional headers map.
         *
         * @param name The header name.
         * @param value The header value.
         * @return The builder instance.
         */
        public Builder add(String name, String value) {
            this.additionalHeaders.put(name, value);
            return this;
        }

        /**
         * Add multiple custom headers to the additional headers map.
         *
         * @param headers A map of headers to add.
         * @return The builder instance.
         */
        public Builder addAll(Map<String, String> headers) {
            this.additionalHeaders.putAll(headers);
            return this;
        }

        /**
         * Build a new RequestOptions object.
         *
         * @return A new RequestOptions object.
         */
        public RequestOptions build() {
            return new RequestOptions(this);
        }
    }
}