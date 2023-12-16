package com.resend.services.audiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for listing audiences.
 */
public class ListAudiencesResponseSuccess {

    @JsonProperty("data")
    private List<Audience> data;

    @JsonProperty("object")
    private String object;

    // Default constructor
    public ListAudiencesResponseSuccess() {
    }

    /**
     * Constructs a successful response for listing audiences.
     *
     * @param data The list of audiences.
     */
    public ListAudiencesResponseSuccess(List<Audience> data, String object) {
        this.data = data;
        this.object = object;
    }

    /**
     * Get the list of audiences.
     *
     * @return The list of audiences.
     */
    public List<Audience> getData() {
        return data;
    }

    /**
     * Get the object.
     *
     * @return The type of the data.
     */
    public String getObject() {
        return object;
    }
}