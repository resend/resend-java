package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a tag associated with an email.
 */
public class Tag {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("value")
    private final String value;

    private Tag(Builder builder) {
        this.name = builder.name;
        this.value = builder.value;
    }

    /**
     * Get the name of the tag.
     *
     * @return The name of the tag.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the value of the tag.
     *
     * @return The value of the tag.
     */
    public String getValue() {
        return value;
    }

    /**
     * Create a new builder instance to construct a Tag object.
     *
     * @return A new instance of the builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * A builder for constructing Tag objects.
     */
    public static class Builder {
        private String name;
        private String value;

        /**
         * Set the name of the tag.
         *
         * @param name The name of the tag.
         * @return This builder instance for method chaining.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the value of the tag.
         *
         * @param value The value of the tag.
         * @return This builder instance for method chaining.
         */
        public Builder value(String value) {
            this.value = value;
            return this;
        }

        /**
         * Build a new Tag object.
         *
         * @return The constructed Tag object.
         */
        public Tag build() {
            return new Tag(this);
        }
    }
}
