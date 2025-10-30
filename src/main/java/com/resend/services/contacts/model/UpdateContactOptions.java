package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update a contact.
 */
public class UpdateContactOptions {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("audience_id")
    @Deprecated
    private final String audienceId;

    @JsonProperty("segment_id")
    private final String segmentId;

    @JsonProperty("unsubscribed")
    private final Boolean unsubscribed;

    @JsonProperty("first_name")
    private final String firstName;

    @JsonProperty("last_name")
    private final String lastName;

    /**
     * Constructs a Contact object using the provided builder.
     *
     * @param builder The builder to construct the Contact.
     */
    public UpdateContactOptions(Builder builder) {
        this.audienceId = builder.audienceId;
        this.segmentId = builder.segmentId;
        this.id = builder.id;
        this.email = builder.email;
        this.unsubscribed = builder.unsubscribed;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    /**
     * Get the audience ID of the contact.
     *
     * @return The audience ID of the contact.
     * @deprecated Use {@link #getSegmentId()} instead.
     */
    @Deprecated
    public String getAudienceId() {
        return audienceId;
    }

    /**
     * Get the segment ID of the contact.
     *
     * @return The segment ID of the contact.
     */
    public String getSegmentId() {
        return segmentId;
    }

    /**
     * Get the email of the contact.
     *
     * @return The email of the contact.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the id of the contact.
     *
     * @return The id of the contact.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the unsubscribed status of the contact.
     *
     * @return The unsubscribed status of the contact.
     */
    public Boolean getUnsubscribed() {
        return unsubscribed;
    }

    /**
     * Get the first name of the contact.
     *
     * @return The first name of the contact.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name of the contact.
     *
     * @return The last name of the contact.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Create a new builder instance for constructing UpdateContactOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateContactOptions objects.
     */
    public static class Builder {
        private String audienceId;
        private String segmentId;
        private String id;
        private String email;
        private Boolean unsubscribed;
        private String firstName;
        private String lastName;

        /**
         * Set the audience ID of the contact.
         *
         * @param audienceId The audience ID of the contact.
         * @return The builder instance.
         * @deprecated Use {@link #segmentId(String)} instead.
         */
        @Deprecated
        public Builder audienceId(String audienceId) {
            this.audienceId = audienceId;
            return this;
        }

        /**
         * Set the segment ID of the contact.
         *
         * @param segmentId The segment ID of the contact.
         * @return The builder instance.
         */
        public Builder segmentId(String segmentId) {
            this.segmentId = segmentId;
            return this;
        }

        /**
         * Set the id of the contact.
         *
         * @param id The id of the contact.
         * @return The builder instance.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Set the email of the contact.
         *
         * @param email The email of the contact.
         * @return The builder instance.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Set the unsubscribed status of the contact.
         *
         * @param unsubscribed The unsubscribed status of the contact.
         * @return The builder instance.
         */
        public Builder unsubscribed(Boolean unsubscribed) {
            this.unsubscribed = unsubscribed;
            return this;
        }

        /**
         * Set the first name of the contact.
         *
         * @param firstName The first name of the contact.
         * @return The builder instance.
         */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Set the last name of the contact.
         *
         * @param lastName The last name of the contact.
         * @return The builder instance.
         */
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Build a new UpdateContactOptions object.
         *
         * @return A new UpdateContactOptions object.
         */
        public UpdateContactOptions build() {
            return new UpdateContactOptions(this);
        }
    }
}