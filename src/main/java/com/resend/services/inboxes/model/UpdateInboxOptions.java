package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update an inbox. Provide at least one of {@code name} or {@code from_name}.
 */
public class UpdateInboxOptions {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("from_name")
    private final String fromName;

    private UpdateInboxOptions(Builder builder) {
        this.name = builder.name;
        this.fromName = builder.fromName;
    }

    /**
     * Get the new internal name for the inbox.
     *
     * @return The new internal name for the inbox.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the name recipients see when mail is sent from this inbox.
     *
     * @return The name recipients see when mail is sent from this inbox.
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * Create a new builder instance for constructing UpdateInboxOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateInboxOptions objects.
     */
    public static class Builder {
        private String name;
        private String fromName;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set the new internal name for the inbox. Recipients do not see it. Between 1 and 64 characters after trimming.
         *
         * @param name The new internal name for the inbox.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the name recipients see when mail is sent from this inbox. A plain name, not a {@code Name <email>} address. Max 78 characters. An empty string removes it.
         *
         * @param fromName The name recipients see when mail is sent from this inbox.
         * @return The builder instance.
         */
        public Builder fromName(String fromName) {
            this.fromName = fromName;
            return this;
        }

        /**
         * Build a new UpdateInboxOptions object.
         *
         * @return A new UpdateInboxOptions object.
         * @throws IllegalArgumentException if neither {@code name} nor {@code from_name} is set, or {@code name} is blank.
         */
        public UpdateInboxOptions build() {
            if (name == null && fromName == null) {
                throw new IllegalArgumentException("At least one of name or from_name must be provided");
            }
            if (name != null && name.trim().isEmpty()) {
                throw new IllegalArgumentException("name must not be blank");
            }
            return new UpdateInboxOptions(this);
        }
    }
}
