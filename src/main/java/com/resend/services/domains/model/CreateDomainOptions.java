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

    /**
     * Constructs a CreateDomainOptions object using the provided builder.
     *
     * @param builder The builder to construct the CreateDomainOptions from.
     */
    public CreateDomainOptions(Builder builder) {
        this.name = builder.name;
        this.region = builder.region;
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
         * Build a new CreateDomainOptions object.
         *
         * @return A new CreateDomainOptions object.
         */
        public CreateDomainOptions build() {
            return new CreateDomainOptions(this);
        }
    }
}