package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to create a domain.
 */
public class CreateDomainOptions {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("region")
    private final String region;

    @JsonProperty("custom_return_path")
    private final String customReturnPath;

    /**
     * Constructs a CreateDomainOptions object using the provided builder.
     *
     * @param builder The builder to construct the CreateDomainOptions from.
     */
    public CreateDomainOptions(Builder builder) {
        this.name = builder.name;
        this.region = builder.region;
        this.customReturnPath = builder.customReturnPath;
    }

    /**
     * Get the name of the domain.
     *
     * @return The name of the domain.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the region of the domain.
     *
     * @return The region of the domain.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Get the customReturnPath of the domain.
     *
     * @return The customReturnPath of the domain.
     */
    public String getCustomReturnPath() {
        return customReturnPath;
    }

    /**
     * Create a new builder instance for constructing CreateDomainOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateDomainOptions objects.
     */
    public static class Builder {
        private String name;
        private String region;
        private String customReturnPath;

        /**
         * Set the name of the domain.
         *
         * @param name The name of the domain.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the region of the domain.
         *
         * @param region The region of the domain.
         * @return The builder instance.
         */
        public Builder region(String region) {
            this.region = region;
            return this;
        }

        /**
         * Set the customReturnPath of the domain.
         *
         * @param customReturnPath The customReturnPath of the domain.
         * @return The builder instance.
         */
        public Builder customReturnPath(String customReturnPath) {
            this.customReturnPath = customReturnPath;
            return this;
        }

        /**
         * Build a new CreateDomainOptions object.
         *
         * @return A new CreateDomainOptions object.
         */
        public CreateDomainOptions build() {
            return new CreateDomainOptions(this);
        }
    }
}