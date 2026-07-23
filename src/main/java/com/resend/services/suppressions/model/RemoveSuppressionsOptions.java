package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a request to remove suppressions from the suppression list.
 * Provide either emails or ids, but not both.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemoveSuppressionsOptions {

    @JsonProperty("emails")
    private final List<String> emails;

    @JsonProperty("ids")
    private final List<String> ids;

    /**
     * Constructs a Remove Suppressions Options object using the provided builder.
     *
     * @param builder The builder to construct the Remove Suppressions Options.
     */
    public RemoveSuppressionsOptions(Builder builder) {
        this.emails = builder.emails;
        this.ids = builder.ids;
    }

    /**
     * Get the email addresses to remove from the suppression list.
     *
     * @return The email addresses to remove from the suppression list.
     */
    public List<String> getEmails() {
        return emails;
    }

    /**
     * Get the suppression IDs to remove from the suppression list.
     *
     * @return The suppression IDs to remove from the suppression list.
     */
    public List<String> getIds() {
        return ids;
    }

    /**
     * Create a new builder instance for constructing RemoveSuppressionsOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing RemoveSuppressionsOptions objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private List<String> emails;
        private List<String> ids;

        /**
         * Set the email addresses to remove from the suppression list.
         *
         * @param emails The email addresses to remove from the suppression list.
         * @return The builder instance.
         */
        public Builder emails(List<String> emails) {
            this.emails = emails;
            return this;
        }

        /**
         * Add an email address to remove from the suppression list.
         *
         * @param email The email address to remove from the suppression list.
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
         * Set the suppression IDs to remove from the suppression list.
         *
         * @param ids The suppression IDs to remove from the suppression list.
         * @return The builder instance.
         */
        public Builder ids(List<String> ids) {
            this.ids = ids;
            return this;
        }

        /**
         * Add a suppression ID to remove from the suppression list.
         *
         * @param id The suppression ID to remove from the suppression list.
         * @return The builder instance.
         */
        public Builder id(String id) {
            if (this.ids == null) {
                this.ids = new ArrayList<>();
            }
            this.ids.add(id);
            return this;
        }

        /**
         * Build a new RemoveSuppressionsOptions object.
         *
         * @return A new RemoveSuppressionsOptions object.
         * @throws IllegalArgumentException If neither or both of emails and ids are provided.
         */
        public RemoveSuppressionsOptions build() {
            boolean hasEmails = emails != null && !emails.isEmpty();
            boolean hasIds = ids != null && !ids.isEmpty();
            if (hasEmails == hasIds) {
                throw new IllegalArgumentException("Either 'emails' or 'ids' must be provided, but not both.");
            }
            return new RemoveSuppressionsOptions(this);
        }
    }
}
