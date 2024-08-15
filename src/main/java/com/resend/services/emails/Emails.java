package com.resend.services.emails;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.service.BaseService;
import com.resend.services.emails.model.*;
import okhttp3.MediaType;

/**
 *  Represents the Resend Emails module.
 */
public final class Emails extends BaseService {

    /**
     * Constructs an instance of the {@code Emails} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Emails(final String apiKey) {
        super(apiKey);
    }

    /**
     * Sends an email based on the provided email request.
     *
     * @param createEmailOptions The request containing email details.
     * @return The response indicating the status of the email sending.
     * @throws ResendException If an error occurs while sending the email.
     */
    public CreateEmailResponse send(CreateEmailOptions createEmailOptions) throws ResendException {

        String payload = super.resendMapper.writeValue(createEmailOptions);
        AbstractHttpResponse<String> response = super.httpClient.perform("/emails", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new RuntimeException("Failed to send email: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, CreateEmailResponse.class);
    }

    /**
     * Retrieves an email by its unique identifier.
     *
     * @param emailId The unique identifier of the email.
     * @return The retrieved email's details.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public Email get(String emailId) throws ResendException {
            AbstractHttpResponse<String> response = this.httpClient.perform("/emails/" + emailId, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to retrieve email: " + response.getCode() + " " + response.getBody());
            }

            String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, Email.class);
    }

    /**
     * Update the schedule of an email by its unique identifier.
     *
     * @param emailId The unique identifier of the email.
     * @param updateEmailOptions The new data of the email.
     * @return The retrieved email's details.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public UpdateEmailResponse update(String emailId, UpdateEmailOptions updateEmailOptions) throws ResendException {

        String payload = super.resendMapper.writeValue(updateEmailOptions);
        AbstractHttpResponse<String> response = this.httpClient.perform("/emails/" + emailId, super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new RuntimeException("Failed to update email: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, UpdateEmailResponse.class);
    }

    /**
     * Cancel the schedule of an email by its unique identifier.
     *
     * @param emailId The unique identifier of the email.
     * @return The retrieved email's details.
     * @throws ResendException If an error occurs while retrieving the email.
     */
    public CancelEmailResponse cancel(String emailId) throws ResendException {

        AbstractHttpResponse<String> response = this.httpClient.perform("/emails/" + emailId + "/cancel", super.apiKey, HttpMethod.POST, "", MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new RuntimeException("Failed to cancel email schedule: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, CancelEmailResponse.class);
    }
}