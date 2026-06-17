package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a reference to a segment that imported contacts should be added to.
 */
public class ContactImportSegmentReference {

    @JsonProperty("id")
    private final String id;

    /**
     * Constructs a ContactImportSegmentReference object using the provided builder.
     *
     * @param builder The builder to construct the reference.
     */
    public ContactImportSegmentReference(Builder builder) {
        this.id = builder.id;
    }

    /**
     * Constructs a ContactImportSegmentReference with the given segment ID.
     *
     * @param id The segment UUID.
     */
    public ContactImportSegmentReference(@JsonProperty("id") final String id) {
        this.id = id;
    }

    /**
     * Gets the segment UUID.
     *
     * @return The segment UUID.
     */
    public String getId() {
        return id;
    }

    /**
     * Creates a new builder instance for constructing ContactImportSegmentReference objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ContactImportSegmentReference objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String id;

        /**
         * Sets the segment UUID.
         *
         * @param id The segment UUID.
         * @return The builder instance.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Builds a new ContactImportSegmentReference instance.
         *
         * @return A new ContactImportSegmentReference instance.
         */
        public ContactImportSegmentReference build() {
            return new ContactImportSegmentReference(this);
        }
    }
}
