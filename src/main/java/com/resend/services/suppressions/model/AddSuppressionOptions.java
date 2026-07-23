package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to add an email address to the suppression list.
 */
public class AddSuppressionOptions {

    @JsonProperty("email")
    private final String email;

    /**
     * Constructs an Add Suppression Options object using the provided builder.
     *
     * @param builder The builder to construct the Add Suppression Options.
     */
    public AddSuppressionOptions(Builder builder) {
        this.email = builder.email;
    }

    /**
     * Get the email address to suppress.
     *
     * @return The email address to suppress.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Create a new builder instance for constructing AddSuppressionOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing AddSuppressionOptions objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String email;

        /**
         * Set the email address to suppress.
         *
         * @param email The email address to suppress.
         * @return The builder instance.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Build a new AddSuppressionOptions object.
         *
         * @return A new AddSuppressionOptions object.
         */
        public AddSuppressionOptions build() {
            return new AddSuppressionOptions(this);
        }
    }
}
