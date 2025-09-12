package com.resend.services.audiences;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.audiences.model.*;
import okhttp3.MediaType;

/**
 *  Represents the Resend Audiences module.
 */
public class Audiences extends BaseService {

    /**
     * Constructs an instance of the {@code Audiences} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Audiences(final String apiKey) {
        super(apiKey);
    }

    /**
     * Creates an Audience.
     *
     * @param createAudienceOptions The Audience details.
     * @return The details of the created audience.
     * @throws ResendException If an error occurs during the Audience creation process.
     */
    public CreateAudienceResponseSuccess create(CreateAudienceOptions createAudienceOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createAudienceOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/audiences", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException("Failed to create Audience: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateAudienceResponseSuccess.class);
    }

    /**
     * Retrieves a list of audiences and returns a ListAudiencesResponseSuccess.
     *
     * @return A ListAudiencesResponseSuccess containing the list of audiences.
     * @throws ResendException If an error occurs during the audiences list retrieval process.
     */
    public ListAudiencesResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/audiences", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException("Failed to retrieve audiences: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListAudiencesResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of audiences and returns a ListAudiencesResponseSuccess.
     *
     * @return A ListAudiencesResponseSuccess containing the paginated list of audiences.
     * @throws ResendException If an error occurs during the audiences list retrieval process.
     */
    public ListAudiencesResponseSuccess list(ListParams params) throws ResendException {
        String pathWithQuery = "/audiences" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException("Failed to retrieve audiences: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListAudiencesResponseSuccess.class);
    }

    /**
     * Retrieves a audience by its unique identifier.
     *
     * @param id The unique identifier of the audience.
     * @return The retrieved audience details.
     * @throws ResendException If an error occurs while retrieving the audience.
     */
    public GetAudienceResponseSuccess get(String id) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/audiences/" +id, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new RuntimeException("Failed to retrieve audience: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, GetAudienceResponseSuccess.class);
    }

    /**
     * Deletes an audience based on the provided audience ID.
     *
     * @param id The unique identifier of the audience to delete.
     * @return The RemoveAudiencesResponseSuccess with the details of the removed audience.
     * @throws ResendException If an error occurs during the audience deletion process.
     */
    public RemoveAudienceResponseSuccess remove(String id) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/audiences/" +id, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException("Failed to delete audience: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, RemoveAudienceResponseSuccess.class);
    }
}