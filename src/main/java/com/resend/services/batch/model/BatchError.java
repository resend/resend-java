package com.resend.services.batch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a validation error for a batch email in permissive mode.
 */
public class BatchError {

    @JsonProperty("index")
    private @Nullable Integer index;

    @JsonProperty("message")
    private @Nullable String message;

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
    public BatchError(final @Nullable Integer index, final @Nullable String message) {
        this.index = index;
        this.message = message;
    }

    /**
     * Get the index of the email in the batch request that failed validation.
     *
     * @return The index of the failed email.
     */
    public @Nullable Integer getIndex() {
        return index;
    }

    /**
     * Get the error message identifying the validation error.
     *
     * @return The error message.
     */
    public @Nullable String getMessage() {
        return message;
    }
}