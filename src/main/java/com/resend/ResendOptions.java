package com.resend;

/**
 * Optional configuration for the {@link Resend} client.
 *
 * <p>Mirrors the Node SDK's client options so Java apps can override the API base URL
 * (useful for testing), customize the User-Agent, and tune HTTP timeouts.</p>
 *
 * <pre>{@code
 * Resend resend = new Resend("re_123", ResendOptions.builder()
 *         .baseUrl("https://api.resend.com")
 *         .connectTimeoutMs(10_000L)
 *         .readTimeoutMs(30_000L)
 *         .writeTimeoutMs(30_000L)
 *         .build());
 * }</pre>
 */
public final class ResendOptions {

    private final String baseUrl;
    private final String userAgent;
    private final Long connectTimeoutMs;
    private final Long readTimeoutMs;
    private final Long writeTimeoutMs;
    private final Long callTimeoutMs;

    private ResendOptions(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.userAgent = builder.userAgent;
        this.connectTimeoutMs = builder.connectTimeoutMs;
        this.readTimeoutMs = builder.readTimeoutMs;
        this.writeTimeoutMs = builder.writeTimeoutMs;
        this.callTimeoutMs = builder.callTimeoutMs;
    }

    /**
     * @return The API base URL, or {@code null} to use the default ({@code https://api.resend.com}).
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @return A custom User-Agent header value, or {@code null} to use the SDK default.
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @return Connect timeout in milliseconds, or {@code null} for the HTTP client default.
     */
    public Long getConnectTimeoutMs() {
        return connectTimeoutMs;
    }

    /**
     * @return Read timeout in milliseconds, or {@code null} for the HTTP client default.
     */
    public Long getReadTimeoutMs() {
        return readTimeoutMs;
    }

    /**
     * @return Write timeout in milliseconds, or {@code null} for the HTTP client default.
     */
    public Long getWriteTimeoutMs() {
        return writeTimeoutMs;
    }

    /**
     * @return Overall call timeout in milliseconds, or {@code null} for the HTTP client default.
     */
    public Long getCallTimeoutMs() {
        return callTimeoutMs;
    }

    /**
     * Creates a new builder with default (unset) options.
     *
     * @return A new {@link Builder}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns options with every value left unset (SDK defaults apply).
     *
     * @return Default options.
     */
    public static ResendOptions defaults() {
        return builder().build();
    }

    /**
     * Builder for {@link ResendOptions}.
     */
    public static final class Builder {
        private String baseUrl;
        private String userAgent;
        private Long connectTimeoutMs;
        private Long readTimeoutMs;
        private Long writeTimeoutMs;
        private Long callTimeoutMs;

        /**
         * Creates a new builder.
         */
        public Builder() {
        }

        /**
         * Sets the API base URL (for example {@code https://api.resend.com} or a mock server).
         *
         * @param baseUrl The base URL without a trailing slash preference (trailing slashes are trimmed).
         * @return This builder.
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Sets a custom User-Agent header value.
         *
         * @param userAgent The User-Agent string.
         * @return This builder.
         */
        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /**
         * Sets the connect timeout in milliseconds.
         *
         * @param connectTimeoutMs Connect timeout; must be &gt;= 0.
         * @return This builder.
         */
        public Builder connectTimeoutMs(Long connectTimeoutMs) {
            this.connectTimeoutMs = connectTimeoutMs;
            return this;
        }

        /**
         * Sets the read timeout in milliseconds.
         *
         * @param readTimeoutMs Read timeout; must be &gt;= 0.
         * @return This builder.
         */
        public Builder readTimeoutMs(Long readTimeoutMs) {
            this.readTimeoutMs = readTimeoutMs;
            return this;
        }

        /**
         * Sets the write timeout in milliseconds.
         *
         * @param writeTimeoutMs Write timeout; must be &gt;= 0.
         * @return This builder.
         */
        public Builder writeTimeoutMs(Long writeTimeoutMs) {
            this.writeTimeoutMs = writeTimeoutMs;
            return this;
        }

        /**
         * Sets the overall call timeout in milliseconds (0 means no timeout).
         *
         * @param callTimeoutMs Call timeout; must be &gt;= 0.
         * @return This builder.
         */
        public Builder callTimeoutMs(Long callTimeoutMs) {
            this.callTimeoutMs = callTimeoutMs;
            return this;
        }

        /**
         * Builds the options instance.
         *
         * @return A new {@link ResendOptions}.
         * @throws IllegalArgumentException If any timeout is negative.
         */
        public ResendOptions build() {
            validateTimeout("connectTimeoutMs", connectTimeoutMs);
            validateTimeout("readTimeoutMs", readTimeoutMs);
            validateTimeout("writeTimeoutMs", writeTimeoutMs);
            validateTimeout("callTimeoutMs", callTimeoutMs);
            return new ResendOptions(this);
        }

        private static void validateTimeout(String name, Long value) {
            if (value != null && value < 0L) {
                throw new IllegalArgumentException(name + " must be >= 0");
            }
        }
    }
}
