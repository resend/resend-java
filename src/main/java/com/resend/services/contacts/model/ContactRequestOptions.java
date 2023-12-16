package com.resend.services.contacts.model;


/**
 * Represents a request to perform operations on contacts with options.
 */
public class ContactRequestOptions {

    private final String id;

    private final String audienceId;

    /**
     * Constructs a Contact Options object using the provided builder.
     *
     * @param builder The builder to construct the Contact Options.
     */
    public ContactRequestOptions(Builder builder) {
        this.id = builder.id;
        this.audienceId = builder.audienceId;
    }

    /**
     * Get the id of the Contact Options.
     *
     * @return The id of the Contact Options.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the audienceId of the Contact Options.
     *
     * @return The audienceId of the Contact Options.
     */
    public String getAudienceId() {
        return audienceId;
    }

    /**
     * Create a new builder instance for constructing ContactRequestOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ContactRequestOptions objects.
     */
    public static class Builder {
        private String id;
        private String audienceId;

        /**
         * Set the id of the Contact Options.
         *
         * @param id The id of the Contact Options.
         * @return The builder instance.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Set the audienceId of the Contact Options.
         *
         * @param audienceId The audienceId of the Contact Options.
         * @return The builder instance.
         */
        public Builder audienceId(String audienceId) {
            this.audienceId = audienceId;
            return this;
        }

        /**
         * Build a new ContactRequestOptions object.
         *
         * @return A new ContactRequestOptions object.
         */
        public ContactRequestOptions build() {
            return new ContactRequestOptions(this);
        }
    }
}
