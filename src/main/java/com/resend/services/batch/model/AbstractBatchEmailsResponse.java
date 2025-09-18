package com.resend.services.batch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.util.List;

/**
 * Base class for batch email responses.
 * The actual implementation depends on the validation mode used.
 */
@JsonSubTypes({
        @JsonSubTypes.Type(CreateBatchEmailsResponse.class),
        @JsonSubTypes.Type(PermissiveBatchEmailsResponse.class)
})
public abstract class AbstractBatchEmailsResponse {
    /**
     * The list of e-mail ids created.
     */
    @JsonProperty("data")
    protected List<BatchEmail> data;

    /**
     * Default constructor.
     */
    public AbstractBatchEmailsResponse() {
    }

    /**
     * Constructor with data.
     *
     * @param data A list of successfully created batch emails.
     */
    public AbstractBatchEmailsResponse(final List<BatchEmail> data) {
        this.data = data;
    }

    /**
     * Get the list of successfully created batch emails.
     *
     * @return A list of batch emails.
     */
    public List<BatchEmail> getData() {
        return data;
    }

}