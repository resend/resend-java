package com.resend.services.segments.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to create a segment with options.
 */
public class CreateSegmentOptions {

    @JsonProperty("name")
    private final String name;

    /**
     * Constructs a Segment Options object using the provided builder.
     *
     * @param builder The builder to construct the Segment Options.
     */
    public CreateSegmentOptions(Builder builder) {
        this.name = builder.name;
    }

    /**
     * Get the name of the Segment Options.
     *
     * @return The name of the Segment Options.
     */
    public String getName() {
        return name;
    }

    /**
     * Create a new builder instance for constructing CreateSegmentOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateSegmentOptions objects.
     */
    public static class Builder {
        private String name;

        /**
         * Set the name of the Segment Options.
         *
         * @param name The name of the Segment Options.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Build a new CreateSegmentOptions object.
         *
         * @return A new CreateSegmentOptions object.
         */
        public CreateSegmentOptions build() {
            return new CreateSegmentOptions(this);
        }
    }
}
