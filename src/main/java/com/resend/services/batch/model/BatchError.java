package com.resend.services.batch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a validation error for a batch email in permissive mode.
 */
public class BatchError {

    @JsonProperty("index")
    private Integer index;

    @JsonProperty("message")
    private String message;

    /**
     * Default constructor.
     */
    public BatchError() {
    }

    /**
     * Constructor with index and message.
     *
     * @param index The index of the email in the batch request that failed validation.
     * @param message The error message identifying the validation error.
     */
    public BatchError(final Integer index, final String message) {
        this.index = index;
        this.message = message;
    }

    /**
     * Get the index of the email in the batch request that failed validation.
     *
     * @return The index of the failed email.
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Get the error message identifying the validation error.
     *
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }
}