package com.resend.services.batch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents the response for creating batch emails.
 */
public class CreateBatchEmailsResponse {

    @JsonProperty("data")
    private List<BatchEmail> data;

    /**
     * Default constructor.
     */
    public CreateBatchEmailsResponse() {
    }

    /**
     * Constructor with a list of batch emails.
     *
     * @param data A list of batch emails.
     */
    public CreateBatchEmailsResponse(final List<BatchEmail> data) {
        this.data = data;
    }

    /**
     * Get the list of batch emails.
     *
     * @return A list of batch emails.
     */
    public List<BatchEmail> getData() {
        return data;
    }
}

