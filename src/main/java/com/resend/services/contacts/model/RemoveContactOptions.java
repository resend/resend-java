package com.resend.services.contacts.model;

/**
 * Class representing options to remove a contact.
 */
public class RemoveContactOptions extends ContactOptions {

    /**
     * Constructs a RemoveContactOptions object using the provided builder.
     *
     * @param builder The builder to construct the RemoveContactOptions.
     */
    public RemoveContactOptions(Builder builder) {
        super(builder);
    }

    /**
     * Create a new builder instance for constructing RemoveContactOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing RemoveContactOptions objects.
     */
    public static class Builder extends ContactOptions.Builder<RemoveContactOptions, Builder> {

        /**
         * Build a new RemoveContactOptions object.
         *
         * @return A new RemoveContactOptions object.
         */
        @Override
        public RemoveContactOptions build() {
            return new RemoveContactOptions(this);
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
