package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to create a domain.
 */
public class CreateDomainRequest {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("region")
    private final String region;

    /**
     * Constructs a CreateDomainRequest object using the provided builder.
     *
     * @param builder The builder to construct the CreateDomainRequest from.
     */
    public CreateDomainRequest(Builder builder) {
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
     * Create a new builder instance for constructing CreateDomainRequest objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateDomainRequest objects.
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
         * Build a new CreateDomainRequest object.
         *
         * @return A new CreateDomainRequest object.
         */
        public CreateDomainRequest build() {
            return new CreateDomainRequest(this);
        }
    }
}