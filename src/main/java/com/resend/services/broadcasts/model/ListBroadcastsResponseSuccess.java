package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a list of broadcasts, containing metadata and associated data.
 */
public class ListBroadcastsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("data")
    private List<Broadcast> data;

    /**
     * Default constructor
     */
    public ListBroadcastsResponseSuccess() {

    }

    /**
     * Constructs a new BroadcastList instance.
     *
     * @param object Type of the object (e.g., "list").
     * @param data List of Broadcast objects.
     */
    public ListBroadcastsResponseSuccess(String object, List<Broadcast> data) {
        this.object = object;
        this.data = data;
    }

    /**
     * @return Type of the object (e.g., "list").
     */
    public String getObject() {
        return object;
    }

    /**
     * @return List of Broadcast objects.
     */
    public List<Broadcast> getData() {
        return data;
    }
}

