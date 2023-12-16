package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing contacts.
 */
public class ListContactsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("data")
    private List<Contact> data;

    /**
     * Default constructor
     */
    public ListContactsResponseSuccess() {
    }

    /**
     * Constructs a successful response for listing contacts.
     *
     * @param data The list of contacts.
     * @param object The object of the list contacts.
     */
    public ListContactsResponseSuccess(final List<Contact> data, final String object) {
        this.data = data;
        this.object = object;
    }

    /**
     * Gets the list of contacts.
     *
     * @return The list of contacts.
     */
    public List<Contact> getData() {
        return data;
    }

    /**
     * Gets the list of contacts object.
     *
     * @return The list of contacts object.
     */
    public String getObject() {
        return object;
    }
}

