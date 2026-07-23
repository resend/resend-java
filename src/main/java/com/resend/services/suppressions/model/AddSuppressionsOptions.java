package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a request to add email addresses to the suppression list.
 * Must contain between 1 and 100 email addresses.
 */
public class AddSuppressionsOptions {

    @JsonProperty("emails")
    private final List<String> emails;

    /**
     * Constructs an Add Suppressions Options object using the provided builder.
     *
     * @param builder The builder to construct the Add Suppressions Options.
     */
    public AddSuppressionsOptions(Builder builder) {
        this.emails = builder.emails;
    }

    /**
     * Get the email addresses to suppress.
     *
     * @return The email addresses to suppress.
     */
    public List<String> getEmails() {
        return emails;
    }

    /**
     * Create a new builder instance for constructing AddSuppressionsOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing AddSuppressionsOptions objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private List<String> emails;

        /**
         * Set the email addresses to suppress.
         *
         * @param emails The email addresses to suppress.
         * @return The builder instance.
         */
        public Builder emails(List<String> emails) {
            this.emails = emails;
            return this;
        }

        /**
         * Add an email address to suppress.
         *
         * @param email The email address to suppress.
         * @return The builder instance.
         */
        public Builder email(String email) {
            if (this.emails == null) {
                this.emails = new ArrayList<>();
            }
            this.emails.add(email);
            return this;
        }

        /**
         * Build a new AddSuppressionsOptions object.
         *
         * @return A new AddSuppressionsOptions object.
         */
        public AddSuppressionsOptions build() {
            return new AddSuppressionsOptions(this);
        }
    }
}
