package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the sending and receiving capabilities of a domain.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DomainCapabilities {

    @JsonProperty("sending")
    private final DomainCapabilityStatus sending;

    @JsonProperty("receiving")
    private final DomainCapabilityStatus receiving;

    /**
     * Constructs a DomainCapabilities object using the provided builder.
     *
     * @param builder The builder to construct the DomainCapabilities from.
     */
    public DomainCapabilities(Builder builder) {
        this.sending = builder.sending;
        this.receiving = builder.receiving;
    }

    /**
     * Get the sending capability of the domain.
     *
     * @return The sending capability of the domain.
     */
    public DomainCapabilityStatus getSending() {
        return sending;
    }

    /**
     * Get the receiving capability of the domain.
     *
     * @return The receiving capability of the domain.
     */
    public DomainCapabilityStatus getReceiving() {
        return receiving;
    }

    /**
     * Create a new builder instance for constructing DomainCapabilities objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing DomainCapabilities objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private DomainCapabilityStatus sending;
        private DomainCapabilityStatus receiving;

        /**
         * Set the sending capability of the domain.
         *
         * @param sending The sending capability of the domain.
         * @return The builder instance.
         */
        public Builder sending(DomainCapabilityStatus sending) {
            this.sending = sending;
            return this;
        }

        /**
         * Set the receiving capability of the domain.
         *
         * @param receiving The receiving capability of the domain.
         * @return The builder instance.
         */
        public Builder receiving(DomainCapabilityStatus receiving) {
            this.receiving = receiving;
            return this;
        }

        /**
         * Build a new DomainCapabilities object.
         *
         * @return A new DomainCapabilities object.
         */
        public DomainCapabilities build() {
            return new DomainCapabilities(this);
        }
    }
}
