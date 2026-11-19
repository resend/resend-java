package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a request to verify a domain with additional options.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerifyDomainOptions {

    @JsonProperty("scope")
    private final List<VerifyDomainScope> scope;

    /**
     * Constructs a VerifyDomainOptions object using the provided builder.
     *
     * @param builder The builder to construct the VerifyDomainOptions from.
     */
    public VerifyDomainOptions(Builder builder) {
        this.scope = builder.scope;
    }

    /**
     * Get the records the verification is limited to.
     *
     * @return The verification scope.
     */
    public List<VerifyDomainScope> getScope() {
        return scope;
    }

    /**
     * Create a new builder instance for constructing VerifyDomainOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing VerifyDomainOptions objects.
     */
    public static class Builder {

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private List<VerifyDomainScope> scope;

        /**
         * Limit the check to specific records. Pass a list containing
         * {@code VerifyDomainScope.FORGE} to verify only the Forge record and leave
         * the other records untouched. Requires Forge to be enabled on the domain.
         * When omitted, all records are checked.
         *
         * @param scope The verification scope.
         * @return The builder instance.
         */
        public Builder scope(List<VerifyDomainScope> scope) {
            this.scope = scope;
            return this;
        }

        /**
         * Build a new VerifyDomainOptions object.
         *
         * @return A new VerifyDomainOptions object.
         */
        public VerifyDomainOptions build() {
            return new VerifyDomainOptions(this);
        }
    }
}
