package com.resend.services.suppressions;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.service.BaseService;
import com.resend.services.suppressions.model.*;
import okhttp3.MediaType;

/**
 * Represents the Resend Suppressions Batch module.
 */
public class SuppressionsBatch extends BaseService {

    /**
     * Constructs an instance of the {@code SuppressionsBatch} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public SuppressionsBatch(final String apiKey) {
        super(apiKey);
    }

    SuppressionsBatch(final String apiKey, final IHttpClient httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Adds up to 100 email addresses to the suppression list at once.
     *
     * @param addSuppressionsOptions The email addresses to suppress.
     * @return The AddSuppressionsResponseSuccess with the details of the added suppressions.
     * @throws ResendException If an error occurs during the suppressions creation process.
     */
    public AddSuppressionsResponseSuccess add(AddSuppressionsOptions addSuppressionsOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(addSuppressionsOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/suppressions/batch/add", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, AddSuppressionsResponseSuccess.class);
    }

    /**
     * Removes up to 100 suppressions from the suppression list at once.
     * Provide either emails or ids, but not both.
     *
     * @param removeSuppressionsOptions The suppressions to remove.
     * @return The RemoveSuppressionsResponseSuccess with the details of the removed suppressions.
     * @throws ResendException If an error occurs during the suppressions removal process.
     */
    public RemoveSuppressionsResponseSuccess remove(RemoveSuppressionsOptions removeSuppressionsOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(removeSuppressionsOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/suppressions/batch/remove", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, RemoveSuppressionsResponseSuccess.class);
    }
}
