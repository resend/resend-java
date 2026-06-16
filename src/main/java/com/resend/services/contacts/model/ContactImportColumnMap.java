package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps CSV columns to contact fields for a contact import.
 *
 * <p>Supports the standard contact fields ({@code email}, {@code firstName}, {@code lastName},
 * {@code unsubscribed}) as well as custom properties via {@link #getProperties()}.</p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactImportColumnMap {

    @JsonProperty("email")
    private final String email;

    @JsonProperty("first_name")
    private final String firstName;

    @JsonProperty("last_name")
    private final String lastName;

    @JsonProperty("unsubscribed")
    private final String unsubscribed;

    @JsonProperty("properties")
    private final Map<String, ContactImportPropertyMapping> properties;

    /**
     * Constructs a ContactImportColumnMap object using the provided builder.
     *
     * @param builder The builder to construct the column map.
     */
    public ContactImportColumnMap(Builder builder) {
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.unsubscribed = builder.unsubscribed;
        this.properties = builder.properties;
    }

    /**
     * Gets the CSV column that contains contact email addresses.
     *
     * @return The CSV column name for emails.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the CSV column that contains contact first names.
     *
     * @return The CSV column name for first names.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the CSV column that contains contact last names.
     *
     * @return The CSV column name for last names.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the CSV column that contains the contact's global subscription status.
     *
     * @return The CSV column name for the unsubscribed status.
     */
    public String getUnsubscribed() {
        return unsubscribed;
    }

    /**
     * Gets the map of custom property keys to their CSV column mapping.
     *
     * @return The custom property mappings.
     */
    public Map<String, ContactImportPropertyMapping> getProperties() {
        return properties;
    }

    /**
     * Creates a new builder instance for constructing ContactImportColumnMap objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ContactImportColumnMap objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private String email;
        private String firstName;
        private String lastName;
        private String unsubscribed;
        private Map<String, ContactImportPropertyMapping> properties;

        /**
         * Sets the CSV column that contains contact email addresses.
         *
         * @param email The CSV column name.
         * @return The builder instance.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the CSV column that contains contact first names.
         *
         * @param firstName The CSV column name.
         * @return The builder instance.
         */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets the CSV column that contains contact last names.
         *
         * @param lastName The CSV column name.
         * @return The builder instance.
         */
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Sets the CSV column that contains the contact's global subscription status.
         *
         * @param unsubscribed The CSV column name.
         * @return The builder instance.
         */
        public Builder unsubscribed(String unsubscribed) {
            this.unsubscribed = unsubscribed;
            return this;
        }

        /**
         * Sets the full custom property mapping.
         *
         * @param properties A map of custom property keys to their column mapping.
         * @return The builder instance.
         */
        public Builder properties(Map<String, ContactImportPropertyMapping> properties) {
            this.properties = properties;
            return this;
        }

        /**
         * Adds a single custom property mapping.
         *
         * @param key     The custom property key.
         * @param mapping The mapping details for the property.
         * @return The builder instance.
         */
        public Builder property(String key, ContactImportPropertyMapping mapping) {
            if (this.properties == null) {
                this.properties = new HashMap<>();
            }
            this.properties.put(key, mapping);
            return this;
        }

        /**
         * Builds a new ContactImportColumnMap instance.
         *
         * @return A new ContactImportColumnMap instance.
         */
        public ContactImportColumnMap build() {
            return new ContactImportColumnMap(this);
        }
    }
}
