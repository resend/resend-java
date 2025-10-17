package com.resend.services.batch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response for batch emails sent in permissive mode.
 * In permissive mode, partial success is allowed and validation errors are returned.
 */
public class CreateBatchEmailsResponse extends AbstractBatchEmailsResponse {

    @JsonProperty("errors")
    private List<BatchError> errors;

    /**
     * Default constructor.
     */
    public CreateBatchEmailsResponse() {
        super();
    }

    /**
     * Constructor with data and errors.
     *
     * @param data A list of successfully created batch emails.
     * @param errors A list of validation errors.
     */
    public CreateBatchEmailsResponse(final List<BatchEmail> data, final List<BatchError> errors) {
        super(data);
        this.errors = errors;
    }

    /**
     * Get the list of validation errors.
     *
     * @return A list of batch email errors, never null (but may be empty).
     */
    public List<BatchError> getErrors() {
        return errors;
    }

    /**
     * Check if the response has any validation errors.
     *
     * @return true if there are validation errors, false otherwise.
     */
    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    /**
     * Get the total number of failed emails.
     *
     * @return The count of emails that failed validation.
     */
    public int getErrorCount() {
        return errors != null ? errors.size() : 0;
    }

}