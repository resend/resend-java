package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jspecify.annotations.Nullable;

/**
 * Represents the options for listing segments a contact belongs to.
 */
public class ListContactSegmentsOptions {

    /**
     * The contact ID (either id or email must be provided, but not both).
     */
    @JsonIgnore
    private @Nullable String id;

    /**
     * The contact email address (either id or email must be provided, but not both).
     */
    @JsonIgnore
    private @Nullable String email;

    /**
     * Constructs a new ListContactSegmentsOptions using the builder.
     *
     * @param builder The builder to construct from.
     */
    private ListContactSegmentsOptions(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
    }

    /**
     * Gets the contact ID.
     *
     * @return The contact ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the contact email.
     *
     * @return The contact email.
     */
    public @Nullable String getEmail() {
        return email;
    }

    /**
     * Creates a new Builder for constructing ListContactSegmentsOptions.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ListContactSegmentsOptions instances.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private @Nullable String id;
        private @Nullable String email;

        /**
         * Sets the contact ID.
         *
         * @param id The contact ID.
         * @return This builder instance.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the contact email.
         *
         * @param email The contact email.
         * @return This builder instance.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Builds the ListContactSegmentsOptions instance.
         *
         * @return A new ListContactSegmentsOptions instance.
         */
        public ListContactSegmentsOptions build() {
            return new ListContactSegmentsOptions(this);
        }
    }
}
