package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response for removing suppressions from the suppression list.
 */
public class RemoveSuppressionsResponseSuccess {

    @JsonProperty("data")
    private List<RemovedSuppression> data;

    /**
     * Default constructor
     */
    public RemoveSuppressionsResponseSuccess() {

    }

    /**
     * Constructs a successful response for removing suppressions.
     *
     * @param data The list of removed suppressions.
     */
    public RemoveSuppressionsResponseSuccess(List<RemovedSuppression> data) {
        this.data = data;
    }

    /**
     * Get the list of removed suppressions.
     *
     * @return The list of removed suppressions.
     */
    public List<RemovedSuppression> getData() {
        return data;
    }
}
