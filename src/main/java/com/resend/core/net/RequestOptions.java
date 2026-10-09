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

    /**
     * Constructs a RequestOptions object using the provided builder.
     *
     * @param builder The builder to construct the RequestOptions.
     */
    public RequestOptions(Builder builder) {
        this.idempotencyKey = builder.idempotencyKey;
        this.additionalHeaders = Collections.unmodifiableMap(new HashMap<>(builder.additionalHeaders));
        this.timeout = builder.timeout;
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
         * Set a call timeout for this request, bounding the whole call from connecting to reading the full response.
         * It overrides the client's {@code callTimeout} for this request only.
         *
         * <p>The client's connect, read and write timeouts (10 seconds each by default) still apply, so this option
         * can shorten a request but can't make it wait longer than those limits. To allow longer requests, raise
         * them with {@link com.resend.Resend.Builder}.</p>
         *
         * <p>Only the built-in {@code HttpClient} honors this option; a custom {@code IHttpClient} may ignore it.</p>
         *
         * @param timeout The call timeout; {@link Duration#ZERO} disables only the call timeout.
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
         * Build a new RequestOptions object.
         *
         * @return A new RequestOptions object.
         */
        public RequestOptions build() {
            return new RequestOptions(this);
        }
    }
}