package com.resend.services.domains;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.domains.model.*;
import okhttp3.MediaType;

/**
 *  Represents the Resend Emails module.
 */
public final class Domains extends BaseService {

    /**
     * Constructs an instance of the {@code Domains} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Domains(final String apiKey) {
        super(apiKey);
    }

    /**
     * Creates a domain based on the provided CreateDomainOptions and returns a CreateDomainResponse.
     *
     * @param createDomainOptions The request object containing the domain creation details.
     * @return A CreateDomainResponse representing the result of the domain creation operation.
     * @throws ResendException If an error occurs during the domain creation process.
     */
    public CreateDomainResponse create(CreateDomainOptions createDomainOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createDomainOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/domains", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateDomainResponse.class);
    }

    /**
     * Retrieves a domain based on the provided domain ID and returns a Domain object.
     *
     * @param domainId The unique identifier of the domain to retrieve.
     * @return A Domain object representing the retrieved domain.
     * @throws ResendException If an error occurs during the domain retrieval process.
     */
    public Domain get(String domainId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/domains/" + domainId, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, Domain.class);

    }

    /**
     * Verifies a domain based on the provided domain ID and returns a VerifyDomainResponse.
     *
     * @param domainId The unique identifier of the domain to verify.
     * @return A VerifyDomainResponse representing the result of the domain verification operation.
     * @throws ResendException If an error occurs during the domain verification process.
     */
    public VerifyDomainResponse verify(String domainId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/domains/" + domainId + "/verify", super.apiKey, HttpMethod.POST, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, VerifyDomainResponse.class);
    }

    /**
     * Retrieves a list of domains and returns a ListDomainsResponse.
     *
     * @return A ListDomainsResponse containing the list of domains.
     * @throws ResendException If an error occurs during the domain list retrieval process.
     */
    public ListDomainsResponse list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/domains", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListDomainsResponse.class);
    }

    /**
     * Retrieves a paginated list of domains and returns a ListDomainsResponse.
     *
     * @param params The params used to customize the list.
     * @return A ListDomainsResponse containing the paginated list of domains.
     * @throws ResendException If an error occurs during the domain list retrieval process.
     */
    public ListDomainsResponse list(ListParams params) throws ResendException {
        String pathWithQuery = "/domains" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListDomainsResponse.class);
    }

    /**
     * Updates a domain based on the provided domain ID and returns a UpdateDomainResponseSuccess.
     *
     * @param updateDomainOptions The object containing the information to be updated.
     * @return A UpdateDomainResponseSuccess representing the result of the domain update operation.
     * @throws ResendException If an error occurs during the domain update process.
     */
    public UpdateDomainResponseSuccess update(UpdateDomainOptions updateDomainOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateDomainOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/domains/" + updateDomainOptions.getId(), super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, UpdateDomainResponseSuccess.class);
    }

    /**
     * Deletes a domain based on the provided domain ID and returns a RemoveDomainResponse.
     *
     * @param domainId The unique identifier of the domain to delete.
     * @return A RemoveDomainResponse representing the result of the domain deletion operation.
     * @throws ResendException If an error occurs during the domain deletion process.
     */
    public RemoveDomainResponse remove(String domainId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/domains/" + domainId, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, RemoveDomainResponse.class);
    }
}