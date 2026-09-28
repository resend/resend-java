package com.resend.services.suppressions;

import com.resend.core.exception.ResendException;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.service.BaseService;
import com.resend.services.suppressions.model.*;
import okhttp3.MediaType;

/**
 *  Represents the Resend Suppressions module.
 */
public class Suppressions extends BaseService {

    /**
     * Constructs an instance of the {@code Suppressions} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Suppressions(final String apiKey) {
        super(apiKey);
    }

    Suppressions(final String apiKey, final IHttpClient httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Returns a SuppressionsBatch object that can be used to add or remove suppressions in batch.
     *
     * @return A SuppressionsBatch object.
     */
    public SuppressionsBatch batch() {
        return new SuppressionsBatch(super.apiKey, super.httpClient);
    }

    /**
     * Adds an email address to the suppression list.
     *
     * @param addSuppressionOptions The suppression details.
     * @return The details of the added suppression.
     * @throws ResendException If an error occurs during the suppression creation process.
     */
    public AddSuppressionResponseSuccess add(AddSuppressionOptions addSuppressionOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(addSuppressionOptions);
        return execute("/suppressions", HttpMethod.POST, payload, MediaType.get("application/json"), AddSuppressionResponseSuccess.class);
    }

    /**
     * Removes a suppression by its unique identifier or by the suppressed email address.
     *
     * @param suppressionIdOrEmail The unique identifier or the suppressed email address.
     * @return The RemoveSuppressionResponseSuccess with the details of the removed suppression.
     * @throws ResendException If an error occurs during the suppression removal process.
     */
    public RemoveSuppressionResponseSuccess remove(String suppressionIdOrEmail) throws ResendException {
        return execute("/suppressions/" + suppressionIdOrEmail, HttpMethod.DELETE, null, MediaType.get("application/json"), RemoveSuppressionResponseSuccess.class);
    }

    /**
     * Retrieves a suppression by its unique identifier or by the suppressed email address.
     *
     * @param suppressionIdOrEmail The unique identifier or the suppressed email address.
     * @return The retrieved suppression details.
     * @throws ResendException If an error occurs while retrieving the suppression.
     */
    public GetSuppressionResponseSuccess get(String suppressionIdOrEmail) throws ResendException {
        return execute("/suppressions/" + suppressionIdOrEmail, HttpMethod.GET, null, MediaType.get("application/json"), GetSuppressionResponseSuccess.class);
    }

    /**
     * Retrieves a list of suppressions.
     *
     * @return A ListSuppressionsResponseSuccess containing the list of suppressions.
     * @throws ResendException If an error occurs during the suppressions list retrieval process.
     */
    public ListSuppressionsResponseSuccess list() throws ResendException {
        return execute("/suppressions", HttpMethod.GET, null, MediaType.get("application/json"), ListSuppressionsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of suppressions with optional filtering by origin.
     *
     * @param params The params used to customize the list.
     * @return A ListSuppressionsResponseSuccess containing the paginated list of suppressions.
     * @throws ResendException If an error occurs during the suppressions list retrieval process.
     */
    public ListSuppressionsResponseSuccess list(ListSuppressionsParams params) throws ResendException {
        String pathWithQuery = "/suppressions" + (params != null ? params.toQueryString() : "");
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), ListSuppressionsResponseSuccess.class);
    }
}
