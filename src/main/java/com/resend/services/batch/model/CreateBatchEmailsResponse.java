package com.resend.services.batch.model;

import java.util.List;

/**
 * Response for batch emails sent in strict mode.
 * In strict mode, either all emails are sent successfully or the entire batch fails.
 */
public class CreateBatchEmailsResponse extends AbstractBatchEmailsResponse {

    /**
     * Default constructor.
     */
    public CreateBatchEmailsResponse() {
        super();
    }

    /**
     * Constructor with data.
     *
     * @param data A list of successfully created batch emails.
     */
    public CreateBatchEmailsResponse(final List<BatchEmail> data) {
        super(data);
    }

}