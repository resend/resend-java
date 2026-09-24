package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a request to create a global contact.
 *
 * <p><strong>Note:</strong> This class is for creating global contacts only.
 * To add a contact to a segment, use the workflow:</p>
 * <ol>
 *   <li>Create global contact: {@code resend.contacts().create(options)}</li>
 *   <li>Add to segment: {@code resend.contacts().segments().add(options)}</li>
 * </ol>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateContactOptions {

    @JsonProperty("audience_id")
    @Deprecated
    private final String audienceId;

    @JsonProperty("segment_id")
    @Deprecated
    private final String segmentId;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("unsubscribed")
    private final Boolean unsubscribed;

    @JsonProperty("first_name")
    private final String firstName;

    @JsonProperty("last_name")
    private final String lastName;

    @JsonProperty("properties")
    @JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.ALWAYS)
    private final Map<String, Object> properties;

    /**
     * Constructs a Contact object using the provided builder.
     *
     * @param builder The builder to construct the Contact.
     */
    public CreateContactOptions(Builder builder) {
        this.audienceId = builder.audienceId;
        this.segmentId = builder.segmentId;
        this.email = builder.email;
        this.unsubscribed = builder.unsubscribed;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.properties = builder.properties;
    }

    /**
     * Get the audience ID of the contact.
     *
     * @return The audience ID of the contact.
     * @deprecated Use {@link #getSegmentId()} instead.
     */
    @Deprecated
    public String getAudienceId() {
        return audienceId;
    }

    /**
     * Get the segment ID of the contact.
     *
     * @return The segment ID of the contact.
     * @deprecated This field is ignored when creating global contacts.
     *             Use {@code resend.contacts().segments().add(options)} to add contacts to segments.
     */
    @Deprecated
    public String getSegmentId() {
        return segmentId;
    }

    /**
     * Get the email of the contact.
     *
     * @return The email of the contact.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the unsubscribed status of the contact.
     *
     * @return The unsubscribed status of the contact.
     */
    public Boolean getUnsubscribed() {
        return unsubscribed;
    }

    /**
     * Get the first name of the contact.
     *
     * @return The first name of the contact.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name of the contact.
     *
     * @return The last name of the contact.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the custom properties of the contact.
     *
     * @return The custom properties of the contact.
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Create a new builder instance for constructing CreateContactRequest objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateContactRequest objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String audienceId;
        private String segmentId;
        private String email;
        private Boolean unsubscribed;
        private String firstName;
        private String lastName;
        private Map<String, Object> properties;

        /**
         * Set the audience ID of the contact.
         *
         * @param audienceId The audience ID of the contact.
         * @return The builder instance.
         * @deprecated Use {@link #segmentId(String)} instead.
         */
        @Deprecated
        public Builder audienceId(String audienceId) {
            this.audienceId = audienceId;
            return this;
        }

        /**
         * Set the segment ID of the contact.
         *
         * @param segmentId The segment ID of the contact.
         * @return The builder instance.
         * @deprecated This field is ignored when creating global contacts.
         *             Use {@code resend.contacts().segments().add(options)} to add contacts to segments.
         */
        @Deprecated
        public Builder segmentId(String segmentId) {
            this.segmentId = segmentId;
            return this;
        }

        /**
         * Set the email of the contact.
         *
         * @param email The email of the contact.
         * @return The builder instance.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Set the unsubscribed status of the contact.
         *
         * @param unsubscribed The unsubscribed status of the contact.
         * @return The builder instance.
         */
        public Builder unsubscribed(Boolean unsubscribed) {
            this.unsubscribed = unsubscribed;
            return this;
        }

        /**
         * Set the first name of the contact.
         *
         * @param firstName The first name of the contact.
         * @return The builder instance.
         */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Set the last name of the contact.
         *
         * @param lastName The last name of the contact.
         * @return The builder instance.
         */
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Set the custom properties of the contact.
         *
         * @param properties A map of custom property keys to their values.
         * @return The builder instance.
         */
        public Builder properties(Map<String, Object> properties) {
            this.properties = properties == null ? null : new HashMap<>(properties);
            return this;
        }

        /**
         * Set a single custom property of the contact.
         *
         * @param key   The custom property key.
         * @param value The custom property value.
         * @return The builder instance.
         */
        public Builder property(String key, Object value) {
            if (this.properties == null) {
                this.properties = new HashMap<>();
            }
            this.properties.put(key, value);
            return this;
        }

        /**
         * Build a new CreateContactRequest object.
         *
         * @return A new CreateContactRequest object.
         */
        public CreateContactOptions build() {
            return new CreateContactOptions(this);
        }
    }
}