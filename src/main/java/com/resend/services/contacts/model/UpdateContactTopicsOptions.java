package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents options for updating contact topic subscriptions.
 */
public class UpdateContactTopicsOptions {

    @JsonIgnore
    private final String id;

    @JsonIgnore
    private final String email;

    @JsonProperty("topics")
    private final List<ContactTopicOptions> topics;

    /**
     * Constructs an UpdateContactTopicsOptions object using the provided builder.
     *
     * @param builder The builder to construct the options.
     */
    public UpdateContactTopicsOptions(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.topics = builder.topics;
    }

    /**
     * Gets the contact ID.
     *
     * @return The contact ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the contact email.
     *
     * @return The contact email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the list of topic subscription updates.
     *
     * @return The list of topic subscription updates.
     */
    public List<ContactTopicOptions> getTopics() {
        return topics;
    }

    /**
     * Creates a new builder instance for constructing UpdateContactTopicsOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateContactTopicsOptions objects.
     */
    public static class Builder {
        private String id;
        private String email;
        private List<ContactTopicOptions> topics;

        /**
         * Sets the contact ID.
         *
         * @param id The contact ID.
         * @return The builder instance.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the contact email.
         *
         * @param email The contact email.
         * @return The builder instance.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the list of topic subscription updates.
         *
         * @param topics The list of topic subscription updates.
         * @return The builder instance.
         */
        public Builder topics(List<ContactTopicOptions> topics) {
            this.topics = topics;
            return this;
        }

        /**
         * Sets the list of topic subscription updates using varargs.
         *
         * @param topics The list of topic subscription updates.
         * @return The builder instance.
         */
        public Builder topics(ContactTopicOptions... topics) {
            if (this.topics == null) {
                this.topics = new ArrayList<>();
            }

            this.topics.addAll(Arrays.asList(topics));
            return this;
        }

        /**
         * Builds a new UpdateContactTopicsOptions object.
         *
         * @return A new UpdateContactTopicsOptions object.
         */
        public UpdateContactTopicsOptions build() {
            return new UpdateContactTopicsOptions(this);
        }
    }
}
