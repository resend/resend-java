package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to create an audience with options.
 */
public class CreateAudienceRequestOptions {

    @JsonProperty("name")
    private final String name;

    /**
     * Constructs an Audience Options object using the provided builder.
     *
     * @param builder The builder to construct the Audience Options.
     */
    public CreateAudienceRequestOptions(Builder builder) {
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
     * Create a new builder instance for constructing CreateAudienceRequestOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateAudienceRequestOptions objects.
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
         * Build a new CreateAudienceRequestOptions object.
         *
         * @return A new CreateAudienceRequestOptions object.
         */
        public CreateAudienceRequestOptions build() {
            return new CreateAudienceRequestOptions(this);
        }
    }
}
