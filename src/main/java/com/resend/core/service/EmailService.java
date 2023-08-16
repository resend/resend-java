package com.resend.core.service;

import com.resend.Resend;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;

import com.resend.core.model.Email;
import com.resend.core.model.SendEmailRequest;
import com.resend.core.model.SendEmailResponse;
import com.resend.core.provider.AuthenticationProvider;

/**
 * Class providing methods to interact with email-related operations.
 */
public class EmailService extends Resend {

    public EmailService() {
        super();
    }

    /**
     * Constructs an instance of the {@code EmailsImpl} class.
     *
     * @param authenticationProvider The provider used for authentication.
     */
    public EmailService(final AuthenticationProvider authenticationProvider) {
        super(authenticationProvider);
    }

    /**
     * Sends an email based on the provided email request.
     *
     * @param sendEmailRequest The request containing email details.
     * @return The response indicating the status of the email sending.
     * @throws RuntimeException If an error occurs while sending the email.
     */
    public SendEmailResponse sendEmail(SendEmailRequest sendEmailRequest) {
        try {
            String payload = super.resendMapper.writeValue(sendEmailRequest);
            AbstractHttpResponse<String> response = super.httpClient.perform("/emails", super.getAuthenticationProvider().token(), HttpMethod.POST, payload);

            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to send email: " + response.getCode() + " " + response.getBody());
            }

            String responseBody = response.getBody();

            SendEmailResponse sendEmailResponse = resendMapper.readValue(responseBody, SendEmailResponse.class);

            return sendEmailResponse;
        } catch (Exception e) {
            throw new RuntimeException("Error sending email: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves an email by its unique identifier.
     *
     * @param emailId The unique identifier of the email.
     * @return The retrieved email's details.
     * @throws RuntimeException If an error occurs while retrieving the email.
     */
    public Email retrieveEmail(String emailId) {
        try {
            AbstractHttpResponse<String> response = this.httpClient.perform("/emails/" + emailId, super.getAuthenticationProvider().token(), HttpMethod.GET, null);

            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to retrieve email: " + response.getCode() + " " + response.getBody());
            }

            String responseBody = response.getBody();

            Email email = resendMapper.readValue(responseBody, Email.class);
            return email;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving email: " + e.getMessage(), e);
        }
    }
}
