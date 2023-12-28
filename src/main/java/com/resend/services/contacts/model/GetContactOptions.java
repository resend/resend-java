package com.resend.services.contacts.model;

/**
 * Class representing options to get a contact.
 */
public class GetContactOptions extends ContactOptions {

    /**
     * Constructs a GetContactOptions object using the provided builder.
     *
     * @param builder The builder to construct the GetContactOptions.
     */
    public GetContactOptions(Builder builder) {
        super(builder);
    }

    /**
     * Create a new builder instance for constructing GetContactOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing GetContactOptions objects.
     */
    public static class Builder extends ContactOptions.Builder<GetContactOptions, Builder> {

        /**
         * Build a new GetContactOptions object.
         *
         * @return A new GetContactOptions object.
         */
        @Override
        public GetContactOptions build() {
            return new GetContactOptions(this);
        }

        /**
         * Return the builder instance.
         *
         * @return The builder instance.
         */
        @Override
        protected Builder self() {
            return this;
        }
    }
}