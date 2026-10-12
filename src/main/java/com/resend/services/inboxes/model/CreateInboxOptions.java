package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to create an inbox.
 */
public class CreateInboxOptions {

    @JsonProperty("email_address")
    private final String emailAddress;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("from_name")
    private final String fromName;

    @JsonProperty("forwarding")
    private final Boolean forwarding;

    private CreateInboxOptions(Builder builder) {
        this.emailAddress = builder.emailAddress;
        this.name = builder.name;
        this.fromName = builder.fromName;
        this.forwarding = builder.forwarding;
    }

    /**
     * Get the inbox email address on one of your domains.
     *
     * @return The inbox email address on one of your domains.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Get the internal name for the inbox.
     *
     * @return The internal name for the inbox.
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
     * Get whether Resend provisions a receiving address, so you can receive mail without adding an MX record.
     *
     * @return Whether Resend provisions a receiving address, so you can receive mail without adding an MX record.
     */
    public Boolean getForwarding() {
        return forwarding;
    }

    /**
     * Create a new builder instance for constructing CreateInboxOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateInboxOptions objects.
     */
    public static class Builder {
        private String emailAddress;
        private String name;
        private String fromName;
        private Boolean forwarding;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set the inbox email address on one of your domains. The API trims and lowercases it.
         *
         * @param emailAddress The inbox email address on one of your domains.
         * @return The builder instance.
         */
        public Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        /**
         * Set the internal name for the inbox. Recipients do not see it. Max 64 characters. Defaults to {@code email_address}.
         *
         * @param name The internal name for the inbox.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the name recipients see when mail is sent from this inbox. A plain name, not a {@code Name <email>} address. Max 78 characters. An empty string leaves it unset.
         *
         * @param fromName The name recipients see when mail is sent from this inbox.
         * @return The builder instance.
         */
        public Builder fromName(String fromName) {
            this.fromName = fromName;
            return this;
        }

        /**
         * Set whether Resend provisions a receiving address, so you can receive mail without adding an MX record. The address is returned in {@code receiving_address}. Defaults to {@code false}.
         *
         * @param forwarding Whether Resend provisions a receiving address, so you can receive mail without adding an MX record.
         * @return The builder instance.
         */
        public Builder forwarding(Boolean forwarding) {
            this.forwarding = forwarding;
            return this;
        }

        /**
         * Build a new CreateInboxOptions object.
         *
         * @return A new CreateInboxOptions object.
         */
        public CreateInboxOptions build() {
            return new CreateInboxOptions(this);
        }
    }
}
