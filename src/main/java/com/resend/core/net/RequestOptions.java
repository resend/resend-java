package com.resend.core.net;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a request to create a request options.
 */
public class RequestOptions {
    private final String idempotencyKey;
    private final Map<String, String> additionalHeaders;
    private final Duration timeout;
    private final Integer maxRetries;

    /**
     * Constructs a RequestOptions object using the provided builder.
     *
     * @param builder The builder to construct the RequestOptions.
     */
    public RequestOptions(Builder builder) {
        this.idempotencyKey = builder.idempotencyKey;
        this.additionalHeaders = Collections.unmodifiableMap(new HashMap<>(builder.additionalHeaders));
        this.timeout = builder.timeout;
        this.maxRetries = builder.maxRetries;
    }

    /**
     * Get the maximum number of retries for this request.
     *
     * @return The maximum number of retries, or {@code null} to use the client's configured default.
     */
    public Integer getMaxRetries() {
        return maxRetries;
    }

    /**
     * Get the timeout applied to this request.
     *
     * @return The timeout, or {@code null} to use the client's configured timeouts.
     */
    public Duration getTimeout() {
        return timeout;
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
        private static final Duration MAX_TIMEOUT = Duration.ofNanos(Long.MAX_VALUE);

        private String idempotencyKey;
        private final Map<String, String> additionalHeaders;
        private Duration timeout;
        private Integer maxRetries;

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
         * Set the timeout for this request, covering the whole call from connecting to reading the full response.
         * It overrides the client's {@code callTimeout} for this request only.
         *
         * <p>Only the built-in {@code HttpClient} honors this option; a custom {@code IHttpClient} may ignore it.</p>
         *
         * @param timeout The timeout; {@link Duration#ZERO} means no timeout.
         * @return The builder instance.
         * @throws IllegalArgumentException If the timeout is negative, or too large to be expressed in nanoseconds
         *                                  (more than {@code Long.MAX_VALUE} nanoseconds, about 292 years).
         */
        public Builder timeout(Duration timeout) {
            if (timeout != null && timeout.isNegative()) {
                throw new IllegalArgumentException("timeout must not be negative, got: " + timeout);
            }
            if (timeout != null && timeout.compareTo(MAX_TIMEOUT) > 0) {
                throw new IllegalArgumentException("timeout must not exceed " + MAX_TIMEOUT + ", got: " + timeout);
            }
            this.timeout = timeout;
            return this;
        }

        /**
         * Set the maximum number of times this request is retried after a retryable failure, overriding the
         * client's default. Zero disables retries for this request.
         *
         * <p>A request is retried on HTTP 429, on HTTP 5xx and on connection failures (a refused or reset
         * connection, or one closed mid-response), waiting between attempts with exponential backoff (or the
         * {@code Retry-After} header when the server sends one). Timeouts and failures that a retry can't fix, such
         * as an unknown host or a TLS error, are not retried. A {@code POST} is retried on HTTP 429 always, but on
         * HTTP 5xx or a connection failure only when it carries an idempotency key, because the request may already
         * have been processed.</p>
         *
         * <p>Only the built-in {@code HttpClient} honors this option; a custom {@code IHttpClient} may ignore it.</p>
         *
         * @param maxRetries The maximum number of retries.
         * @return The builder instance.
         * @throws IllegalArgumentException If the value is negative.
         */
        public Builder maxRetries(int maxRetries) {
            if (maxRetries < 0) {
                throw new IllegalArgumentException("maxRetries must not be negative, got: " + maxRetries);
            }
            this.maxRetries = maxRetries;
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