package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Common superclass for contact options.
 *
 * <p><strong>Note:</strong> These option classes are for global contact operations only.
 * Segment-related fields are ignored.</p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ContactOptions {

    /**
     * The id of the contact options
     */
    @JsonProperty("id")
    protected final String id;

    /**
     * The audience_id of the contact options
     * @deprecated This field is ignored. Use ContactSegments service for segment operations.
     */
    @Deprecated
    @JsonProperty("audience_id")
    protected final String audienceId;

    /**
     * The segment_id of the contact options
     * @deprecated This field is ignored. Use ContactSegments service for segment operations.
     */
    @Deprecated
    @JsonProperty("segment_id")
    protected final String segmentId;

    /**
     * The email of the contact options
     */
    @JsonProperty("email")
    protected final String email;

    /**
     * Constructs a ContactOptions object using the provided builder.
     *
     * @param builder The builder to construct the ContactOptions.
     */
    protected ContactOptions(Builder builder) {
        this.id = builder.id;
        this.audienceId = builder.audienceId;
        this.segmentId = builder.segmentId;
        this.email = builder.email;
    }

    /**
     * Get the id of the ContactOptions.
     *
     * @return The id of the ContactOptions.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the audienceId of the ContactOptions.
     *
     * @return The audienceId of the ContactOptions.
     * @deprecated This field is ignored. Use ContactSegments service for segment operations.
     */
    @Deprecated
    public String getAudienceId() {
        return audienceId;
    }

    /**
     * Get the segmentId of the ContactOptions.
     *
     * @return The segmentId of the ContactOptions.
     * @deprecated This field is ignored. Use ContactSegments service for segment operations.
     */
    @Deprecated
    public String getSegmentId() {
        return segmentId;
    }

    /**
     * Get the email of the ContactOptions.
     *
     * @return The email of the ContactOptions.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Common builder class for constructing ContactOptions objects.
     */
    protected static abstract class Builder<T extends ContactOptions, B extends Builder<T, B>> {
        /**
         * The id of the contact options builder
         */
        protected String id;

        /**
         * The audienceId of the contact options builder
         * @deprecated Use {@link #segmentId} instead.
         */
        @Deprecated
        protected String audienceId;

        /**
         * The segmentId of the contact options builder
         */
        protected String segmentId;

        /**
         * The email of the contact options builder
         */
        protected String email;

        /**
         * Set the id of the ContactOptions.
         *
         * @param id The id of the ContactOptions.
         * @return The builder instance.
         */
        public B id(String id) {
            this.id = id;
            return self();
        }

        /**
         * Set the audienceId of the ContactOptions.
         *
         * @param audienceId The audienceId of the ContactOptions.
         * @return The builder instance.
         * @deprecated This field is ignored. Use ContactSegments service for segment operations.
         */
        @Deprecated
        public B audienceId(String audienceId) {
            this.audienceId = audienceId;
            return self();
        }

        /**
         * Set the segmentId of the ContactOptions.
         *
         * @param segmentId The segmentId of the ContactOptions.
         * @return The builder instance.
         * @deprecated This field is ignored. Use ContactSegments service for segment operations.
         */
        @Deprecated
        public B segmentId(String segmentId) {
            this.segmentId = segmentId;
            return self();
        }

        /**
         * Set the email of the ContactOptions.
         *
         * @param email The email of the ContactOptions.
         * @return The builder instance.
         */
        public B email(String email) {
            this.email = email;
            return self();
        }

        /**
         * Abstract method to be implemented by subclasses to create an instance of the corresponding ContactOptions class.
         *
         * @return A new ContactOptions object.
         */
        public abstract T build();

        /**
         * Abstract method to be implemented by subclasses to return the builder instance (used for self-referencing in methods).
         *
         * @return The builder instance.
         */
        protected abstract B self();
    }
}

