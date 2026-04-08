package com.resend.services.events.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a request to send an event to a contact.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendEventOptions {

    @JsonProperty("event")
    private final String event;

    @JsonProperty("contact_id")
    private final String contactId;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("payload")
    private final Map<String, Object> payload;

    public SendEventOptions(Builder builder) {
        this.event = builder.event;
        this.contactId = builder.contactId;
        this.email = builder.email;
        this.payload = builder.payload;
    }

    /**
     * Retrieves the event name or identifier.
     *
     * @return The event name or identifier.
     */
    public String getEvent() {
        return event;
    }

    /**
     * Retrieves the contact ID.
     *
     * @return The contact ID.
     */
    public String getContactId() {
        return contactId;
    }

    /**
     * Retrieves the contact email address.
     *
     * @return The contact email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the event payload data.
     *
     * @return The payload as a map of key-value pairs.
     */
    public Map<String, Object> getPayload() {
        return payload;
    }

    /**
     * Creates a new builder instance for SendEventOptions.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String event;
        private String contactId;
        private String email;
        private Map<String, Object> payload;

        public Builder event(String event) {
            this.event = event;
            return this;
        }

        public Builder contactId(String contactId) {
            this.contactId = contactId;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder payload(Map<String, Object> payload) {
            this.payload = payload;
            return this;
        }

        public Builder addPayload(String key, Object value) {
            if (this.payload == null) {
                this.payload = new HashMap<>();
            }
            this.payload.put(key, value);
            return this;
        }

        public SendEventOptions build() {
            return new SendEventOptions(this);
        }
    }
}
