package com.resend.services.batch;

import com.resend.ResendOptions;
import com.resend.core.exception.ResendException;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.RequestOptions;
import com.resend.core.service.BaseService;

import com.resend.services.batch.model.CreateBatchEmailsResponse;
import com.resend.services.emails.model.CreateEmailOptions;
import okhttp3.MediaType;

import java.util.List;

/**
 *  Represents the Resend Batch module.
 */
public class Batch extends BaseService {
    /**
     * Constructs an instance of the {@code Batch} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Batch(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code Batch} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Batch(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Sends up to 100 batch emails.
     *
     * @param emails batch emails to send.
     * @return The emails ids.
     * @throws ResendException If an error occurs while sending batch emails.
     */
    public CreateBatchEmailsResponse send(List<CreateEmailOptions> emails) throws ResendException {

        String payload = super.resendMapper.writeValue(emails);
        return execute("/emails/batch", HttpMethod.POST, payload, MediaType.get("application/json"), CreateBatchEmailsResponse.class);
    }

    /**
     * Sends up to 100 batch emails.
     *
     * @param emails batch emails to send.
     * @param requestOptions The options with additional headers.
     * @return The emails ids.
     * @throws ResendException If an error occurs while sending batch emails.
     */
    public CreateBatchEmailsResponse send(List<CreateEmailOptions> emails, RequestOptions requestOptions) throws ResendException {

        String payload = super.resendMapper.writeValue(emails);
        return execute("/emails/batch", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, CreateBatchEmailsResponse.class);
    }

    /**
     * Creates and sends a batch of email messages based on the provided list of email requests.
     *
     * @param emails A list of {@link CreateEmailOptions} objects representing the email messages to be sent.
     * @return A {@link CreateBatchEmailsResponse} containing information about the created batch of emails.
     * @throws ResendException if an error occurs during the creation and sending of the emails.
     */
    public CreateBatchEmailsResponse create(List<CreateEmailOptions> emails) throws ResendException {
        return this.send(emails);
    }

}
