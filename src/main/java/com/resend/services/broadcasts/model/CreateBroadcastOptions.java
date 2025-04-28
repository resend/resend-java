package com.resend.services.broadcasts.model;

/**
 * Class representing options to create a broadcast.
 */
public class CreateBroadcastOptions extends BroadcastOptions {

    /**
     * Constructs a CreateBroadcastOptions object using the provided builder.
     *
     * @param builder The builder to construct the CreateBroadcastOptions.
     */
    public CreateBroadcastOptions(Builder builder) {
        super(builder);
    }

    /**
     * Create a new builder instance for constructing CreateBroadcastOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing BroadcastOptions objects.
     */
    public static class Builder extends BroadcastOptions.Builder<CreateBroadcastOptions, Builder> {

        /**
         * Build a new BroadcastOptions object.
         *
         * @return A new BroadcastOptions object.
         */
        @Override
        public CreateBroadcastOptions build() {
            return new CreateBroadcastOptions(this);
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
