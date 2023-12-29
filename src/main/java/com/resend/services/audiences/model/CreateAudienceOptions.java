package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to create an audience with options.
 */
public class CreateAudienceOptions {

    @JsonProperty("name")
    private final String name;

    /**
     * Constructs an Audience Options object using the provided builder.
     *
     * @param builder The builder to construct the Audience Options.
     */
    public CreateAudienceOptions(Builder builder) {
        this.name = builder.name;
    }

    /**
     * Get the name of the Audience Options.
     *
     * @return The name of the Audience Options.
     */
    public String getName() {
        return name;
    }

    /**
     * Create a new builder instance for constructing CreateAudienceOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateAudienceOptions objects.
     */
    public static class Builder {
        private String name;

        /**
         * Set the name of the Audience Options.
         *
         * @param name The name of the Audience Options.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Build a new CreateAudienceOptions object.
         *
         * @return A new CreateAudienceOptions object.
         */
        public CreateAudienceOptions build() {
            return new CreateAudienceOptions(this);
        }
    }
}
