package com.resend.services.contacts.model;

/**
 * Options for listing contacts, optionally scoped to a segment.
 *
 * <p>To list contacts within a segment, provide either {@code segmentId} or the deprecated
 * {@code audienceId}. When both are provided, {@code segmentId} takes precedence.</p>
 *
 * <p>If neither is provided, all global contacts are listed.</p>
 */
public class ListContactsOptions {

    private final String segmentId;

    /**
     * @deprecated Use {@link #segmentId} instead. Kept for backward compatibility.
     */
    @Deprecated
    private final String audienceId;

    private ListContactsOptions(Builder builder) {
        this.segmentId = builder.segmentId;
        this.audienceId = builder.audienceId;
    }

    /**
     * Returns the segment ID, or falls back to {@code audienceId} if {@code segmentId} is not set.
     *
     * @return The resolved segment/audience ID, or {@code null} if neither is set.
     */
    public String resolvedSegmentId() {
        return segmentId != null ? segmentId : audienceId;
    }

    /**
     * Gets the segment ID.
     *
     * @return The segment ID.
     */
    public String getSegmentId() {
        return segmentId;
    }

    /**
     * Gets the audience ID.
     *
     * @return The audience ID.
     * @deprecated Use {@link #getSegmentId()} instead.
     */
    @Deprecated
    public String getAudienceId() {
        return audienceId;
    }

    /**
     * Creates a new builder for {@code ListContactsOptions}.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@code ListContactsOptions}.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String segmentId;
        private String audienceId;

        /**
         * Sets the segment ID to scope the contact list.
         *
         * @param segmentId The segment ID.
         * @return This builder.
         */
        public Builder segmentId(String segmentId) {
            this.segmentId = segmentId;
            return this;
        }

        /**
         * Sets the audience ID to scope the contact list.
         *
         * @param audienceId The audience ID.
         * @return This builder.
         * @deprecated Use {@link #segmentId(String)} instead.
         */
        @Deprecated
        public Builder audienceId(String audienceId) {
            this.audienceId = audienceId;
            return this;
        }

        /**
         * Builds a new {@code ListContactsOptions} instance.
         *
         * @return A new {@code ListContactsOptions}.
         */
        public ListContactsOptions build() {
            return new ListContactsOptions(this);
        }
    }
}
