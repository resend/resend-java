package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update the Forge configuration of a domain.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateForgeOptions {

    @JsonProperty("return_path")
    private final String returnPath;

    /**
     * Constructs an UpdateForgeOptions object using the provided builder.
     *
     * @param builder The builder to construct the UpdateForgeOptions from.
     */
    public UpdateForgeOptions(Builder builder) {
        this.returnPath = builder.returnPath;
    }

    /**
     * Get the subdomain used as the return path.
     *
     * @return The return path.
     */
    public String getReturnPath() {
        return returnPath;
    }

    /**
     * Create a new builder instance for constructing UpdateForgeOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateForgeOptions objects.
     */
    public static class Builder {

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String returnPath;

        /**
         * Set the subdomain used as the return path.
         *
         * @param returnPath The return path.
         * @return The builder instance.
         */
        public Builder returnPath(String returnPath) {
            this.returnPath = returnPath;
            return this;
        }

        /**
         * Build a new UpdateForgeOptions object.
         *
         * @return A new UpdateForgeOptions object.
         */
        public UpdateForgeOptions build() {
            return new UpdateForgeOptions(this);
        }
    }
}
