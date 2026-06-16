package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the mapping for a single custom contact property in a contact import.
 *
 * <p>Each mapping points a property key (e.g. {@code "plan"}) to the CSV column that
 * holds its value, along with an optional value type.</p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactImportPropertyMapping {

    @JsonProperty("column")
    private final String column;

    @JsonProperty("type")
    private final String type;

    /**
     * Constructs a ContactImportPropertyMapping object using the provided builder.
     *
     * @param builder The builder to construct the mapping.
     */
    public ContactImportPropertyMapping(Builder builder) {
        this.column = builder.column;
        this.type = builder.type;
    }

    /**
     * Gets the CSV column that contains the custom property value.
     *
     * @return The CSV column name.
     */
    public String getColumn() {
        return column;
    }

    /**
     * Gets the custom property value type ({@code "string"}, {@code "number"}, or {@code "boolean"}).
     *
     * @return The property value type, or {@code null} when defaulted by the API.
     */
    public String getType() {
        return type;
    }

    /**
     * Creates a new builder instance for constructing ContactImportPropertyMapping objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ContactImportPropertyMapping objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String column;
        private String type;

        /**
         * Sets the CSV column that contains the custom property value.
         *
         * @param column The CSV column name.
         * @return The builder instance.
         */
        public Builder column(String column) {
            this.column = column;
            return this;
        }

        /**
         * Sets the custom property value type ({@code "string"}, {@code "number"}, or {@code "boolean"}).
         * Defaults to {@code "string"} when omitted.
         *
         * @param type The property value type.
         * @return The builder instance.
         */
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * Builds a new ContactImportPropertyMapping instance.
         *
         * @return A new ContactImportPropertyMapping instance.
         */
        public ContactImportPropertyMapping build() {
            return new ContactImportPropertyMapping(this);
        }
    }
}
