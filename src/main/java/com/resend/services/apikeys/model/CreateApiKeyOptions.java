package com.resend.services.apikeys.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to create an api key.
 */
public class CreateApiKeyOptions {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("permission")
    private final String permission;

    @JsonProperty("domain_id")
    private final String domainId;

    /**
     * Constructs an API Key object using the provided builder.
     *
     * @param builder The builder to construct the API Key.
     */
    public CreateApiKeyOptions(Builder builder) {
        this.name = builder.name;
        this.permission = builder.permission;
        this.domainId = builder.domainId;
    }

    /**
     * Get the name of the API Key.
     *
     * @return The name of the API Key.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the permission of the API Key.
     *
     * @return The permission of the API Key.
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Get the domain id of the API Key.
     *
     * @return The domain id of the API Key.
     */
    public String getDomainId() {
        return domainId;
    }

    /**
     * Create a new builder instance for constructing CreateApiKeyRequest objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateApiKeyRequest objects.
     */
    public static class Builder {
        private String name;
        private String permission;
        private String domainId;

        /**
         * Set the name of the Api Key.
         *
         * @param name The name of the Api Key.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the permission of the Api Key.
         *
         * @param permission The permission of the Api Key.
         * @return The builder instance.
         */
        public Builder permission(String permission) {
            this.permission = permission;
            return this;
        }

        /**
         * Set the domain id of the Api Key.
         *
         * @param domainId The domain id of the Api Key.
         * @return The builder instance.
         */
        public Builder domainId(String domainId) {
            this.domainId = domainId;
            return this;
        }

        /**
         * Build a new CreateApiKeyRequest object.
         *
         * @return A new CreateApiKeyRequest object.
         */
        public CreateApiKeyOptions build() {
            return new CreateApiKeyOptions(this);
        }
    }
}
