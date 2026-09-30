package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jspecify.annotations.Nullable;

/**
 * Represents the options for adding a contact to a segment.
 */
public class AddContactToSegmentOptions {

    /**
     * The contact ID (either id or email must be provided, but not both).
     */
    @JsonIgnore
    private @Nullable String id;

    /**
     * The contact email address (either id or email must be provided, but not both).
     */
    @JsonIgnore
    private @Nullable String email;

    /**
     * The segment ID to add the contact to (required).
     */
    @JsonIgnore
    private @Nullable String segmentId;

    /**
     * Constructs a new AddContactToSegmentOptions using the builder.
     *
     * @param builder The builder to construct from.
     */
    private AddContactToSegmentOptions(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.segmentId = builder.segmentId;
    }

    /**
     * Gets the contact ID.
     *
     * @return The contact ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the contact email.
     *
     * @return The contact email.
     */
    public @Nullable String getEmail() {
        return email;
    }

    /**
     * Gets the segment ID.
     *
     * @return The segment ID.
     */
    public @Nullable String getSegmentId() {
        return segmentId;
    }

    /**
     * Creates a new Builder for constructing AddContactToSegmentOptions.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing AddContactToSegmentOptions instances.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private @Nullable String id;
        private @Nullable String email;
        private @Nullable String segmentId;

        /**
         * Sets the contact ID.
         *
         * @param id The contact ID.
         * @return This builder instance.
         */
        public Builder id(@Nullable String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the contact email.
         *
         * @param email The contact email.
         * @return This builder instance.
         */
        public Builder email(@Nullable String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the segment ID.
         *
         * @param segmentId The segment ID.
         * @return This builder instance.
         */
        public Builder segmentId(String segmentId) {
            this.segmentId = segmentId;
            return this;
        }

        /**
         * Builds the AddContactToSegmentOptions instance.
         *
         * @return A new AddContactToSegmentOptions instance.
         */
        public AddContactToSegmentOptions build() {
            return new AddContactToSegmentOptions(this);
        }
    }
}
