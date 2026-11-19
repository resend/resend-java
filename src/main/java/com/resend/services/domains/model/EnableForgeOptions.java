package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to enable Forge on a domain.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnableForgeOptions {

    @JsonProperty("custom_return_path")
    private final String customReturnPath;

    /**
     * Constructs an EnableForgeOptions object using the provided builder.
     *
     * @param builder The builder to construct the EnableForgeOptions from.
     */
    public EnableForgeOptions(Builder builder) {
        this.customReturnPath = builder.customReturnPath;
    }

    /**
     * Get the customReturnPath used for Forge.
     *
     * @return The customReturnPath.
     */
    public String getCustomReturnPath() {
        return customReturnPath;
    }

    /**
     * Create a new builder instance for constructing EnableForgeOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing EnableForgeOptions objects.
     */
    public static class Builder {

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String customReturnPath;

        /**
         * Set the customReturnPath used for Forge. Optional: when omitted, a free
         * label is picked. It is set once and cannot be changed afterwards.
         *
         * @param customReturnPath The customReturnPath.
         * @return The builder instance.
         */
        public Builder customReturnPath(String customReturnPath) {
            this.customReturnPath = customReturnPath;
            return this;
        }

        /**
         * Build a new EnableForgeOptions object.
         *
         * @return A new EnableForgeOptions object.
         */
        public EnableForgeOptions build() {
            return new EnableForgeOptions(this);
        }
    }
}
