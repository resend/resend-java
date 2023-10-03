package com.resend.services.batch;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.service.BaseService;

import com.resend.services.batch.model.CreateBatchEmailsResponse;
import com.resend.services.emails.model.SendEmailRequest;
import okhttp3.MediaType;

import java.util.List;

public class Batch extends BaseService {
    /**
     * Constructsan instance of the {@code Batch} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Batch(final String apiKey) {
        super(apiKey);
    }

    /**
     * Sends up to 100 batch emails.
     *
     * @param emails batch emails to send.
     * @return The emails ids.
     * @throws ResendException If an error occurs while sending batch emails.
     */
    public CreateBatchEmailsResponse send(List<SendEmailRequest> emails) throws ResendException {

        String payload = super.resendMapper.writeValue(emails);
        AbstractHttpResponse<String> response = super.httpClient.perform("/emails/batch", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new RuntimeException("Failed to send batch emails: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        CreateBatchEmailsResponse createBatchEmailsResponse = resendMapper.readValue(responseBody, CreateBatchEmailsResponse.class);

        return createBatchEmailsResponse;
    }
}
