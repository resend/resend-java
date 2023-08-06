package com.resend.core.modules;

import com.resend.core.model.Email;
import com.resend.core.model.SendEmailRequest;
import com.resend.core.model.SendEmailResponse;

/**
 * Represents the interface for interacting with email-related operations.
 */
public interface Emails {

    /**
     * Sends an email based on the provided email request.
     *
     * @param sendEmailRequest The request containing email details.
     * @return The response indicating the status of the email sending.
     */
    SendEmailResponse sendEmail(SendEmailRequest sendEmailRequest);

    /**
     * Retrieves an email by its unique identifier.
     *
     * @param emailId The unique identifier of the email.
     * @return The retrieved email's details.
     */
    Email retrieveEmail(String emailId);
}

