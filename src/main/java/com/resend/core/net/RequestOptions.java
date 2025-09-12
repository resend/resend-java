package com.resend.core.net;

/**
 * Represents a request to create a request options.
 */
public class RequestOptions {
    private final String idempotencyKey;

    /**
     * Constructs a RequestOptions object using the provided builder.
     *
     * @param builder The builder to construct the RequestOptions.
     */
    public RequestOptions(Builder builder) {
        this.idempotencyKey = builder.idempotencyKey;
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
         * Build a new RequestOptions object.
         *
         * @return A new RequestOptions object.
         */
        public RequestOptions build() {
            return new RequestOptions(this);
        }
    }
}
