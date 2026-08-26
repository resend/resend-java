package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update a segment.
 */
public class UpdateSegmentOptions {

    /**
     * The segment name.
     */
    @JsonProperty("name")
    private final String name;

    /**
     * Private constructor used by the Builder.
     *
     * @param builder The builder instance.
     */
    private UpdateSegmentOptions(Builder builder) {
        this.name = builder.name;
    }

    /**
     * Retrieves the name of the segment.
     *
     * @return The segment name.
     */
    public String getName() {
        return name;
    }

    /**
     * Creates a new builder instance to construct UpdateSegmentOptions.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateSegmentOptions instances.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String name;

        /**
         * Set the segment name.
         *
         * @param name The segment name.
         * @return This builder instance for method chaining.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Builds and returns a {@code UpdateSegmentOptions} based on the configured properties.
         *
         * @return A {@code UpdateSegmentOptions} instance.
         */
        public UpdateSegmentOptions build() {
            return new UpdateSegmentOptions(this);
        }
    }
}
