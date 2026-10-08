package com.resend.services.suppressions;

import com.resend.core.exception.ResendException;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.RequestOptions;
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

    /**
     * Constructs an instance of the {@code SuppressionsBatch} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public SuppressionsBatch(final String apiKey, final IHttpClient<String> httpClient) {
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
        return add(addSuppressionsOptions, (RequestOptions) null);
    }

    /**
     * Adds up to 100 email addresses to the suppression list at once.
     *
     * @param addSuppressionsOptions The email addresses to suppress.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The AddSuppressionsResponseSuccess with the details of the added suppressions.
     * @throws ResendException If an error occurs during the suppressions creation process.
     */
    public AddSuppressionsResponseSuccess add(AddSuppressionsOptions addSuppressionsOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(addSuppressionsOptions);
        return execute("/suppressions/batch/add", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, AddSuppressionsResponseSuccess.class);
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
        return remove(removeSuppressionsOptions, (RequestOptions) null);
    }

    /**
     * Removes up to 100 suppressions from the suppression list at once.
     * Provide either emails or ids, but not both.
     *
     * @param removeSuppressionsOptions The suppressions to remove.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The RemoveSuppressionsResponseSuccess with the details of the removed suppressions.
     * @throws ResendException If an error occurs during the suppressions removal process.
     */
    public RemoveSuppressionsResponseSuccess remove(RemoveSuppressionsOptions removeSuppressionsOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(removeSuppressionsOptions);
        return execute("/suppressions/batch/remove", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, RemoveSuppressionsResponseSuccess.class);
    }
}
