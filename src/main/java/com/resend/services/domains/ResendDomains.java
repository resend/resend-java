package com.resend.services.domains;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.provider.AuthenticationProvider;
import com.resend.core.service.BaseService;
import com.resend.services.domains.model.*;
import okhttp3.MediaType;

public class ResendDomains extends BaseService {

    /**
     * Empty constructor of the ResendDomains module.
     */
    public ResendDomains() {
        super();
    }

    /**
     * Constructs an instance of the {@code ResendDomains} class.
     *
     * @param authenticationProvider The provider used for authentication.
     */
    public ResendDomains(final AuthenticationProvider authenticationProvider) {
        super(authenticationProvider);
    }

    /**
     * Creates a domain based on the provided CreateDomainRequest and returns a CreateDomainResponse.
     *
     * @param createDomainRequest The request object containing the domain creation details.
     * @return A CreateDomainResponse representing the result of the domain creation operation.
     * @throws ResendException If an error occurs during the domain creation process.
     */
    public CreateDomainResponse create(CreateDomainRequest createDomainRequest) throws ResendException {

        try {
            String payload = super.resendMapper.writeValue(createDomainRequest);
            AbstractHttpResponse<String> response = httpClient.perform("/domains", getAuthenticationProvider().token(), HttpMethod.POST, payload, MediaType.get("application/json"));

            if (!response.isSuccessful()) {
                throw new ResendException("Failed to create domain: " + response.getCode() + " " + response.getBody());
            }

            String responseBody = response.getBody();
            CreateDomainResponse createDomainResponse = resendMapper.readValue(responseBody, CreateDomainResponse.class);

            return createDomainResponse;
        } catch (Exception e) {
            throw new ResendException(e.getMessage());
        }
    }

    /**
     * Retrieves a domain based on the provided domain ID and returns a Domain object.
     *
     * @param domainId The unique identifier of the domain to retrieve.
     * @return A Domain object representing the retrieved domain.
     * @throws ResendException If an error occurs during the domain retrieval process.
     */
    public Domain get(String domainId) throws ResendException {

        try {
            AbstractHttpResponse<String> response = this.httpClient.perform("/domains/" + domainId, super.getAuthenticationProvider().token(), HttpMethod.GET, null, MediaType.get("application/json"));

            if (!response.isSuccessful()) {
                throw new ResendException("Failed to retrieve domain: " + response.getCode() + " " + response.getBody());
            }

            String responseBody = response.getBody();

            Domain domain = resendMapper.readValue(responseBody, Domain.class);
            return domain;
        } catch (Exception e) {
            throw new ResendException("Error retrieving domain: " + e.getMessage(), e);
        }
    }

    /**
     * Verifies a domain based on the provided domain ID and returns a VerifyDomainResponse.
     *
     * @param domainId The unique identifier of the domain to verify.
     * @return A VerifyDomainResponse representing the result of the domain verification operation.
     * @throws ResendException If an error occurs during the domain verification process.
     */
    public VerifyDomainResponse verify(String domainId) throws ResendException {

        try {
            AbstractHttpResponse<String> response = httpClient.perform("/domains/" + domainId + "/verify", getAuthenticationProvider().token(), HttpMethod.POST, "", null);

            if (!response.isSuccessful()) {
                throw new ResendException("Failed to verify domain: " + response.getCode() + " " + response.getBody());
            }

            String responseBody = response.getBody();
            VerifyDomainResponse verifyDomainResponse = resendMapper.readValue(responseBody, VerifyDomainResponse.class);

            return verifyDomainResponse;
        } catch (Exception e) {
            throw new ResendException(e.getMessage());
        }
    }

    /**
     * Retrieves a list of domains and returns a ListDomainsResponse.
     *
     * @return A ListDomainsResponse containing the list of domains.
     * @throws ResendException If an error occurs during the domain list retrieval process.
     */
    public ListDomainsResponse list() throws ResendException {

        try {
            AbstractHttpResponse<String> response = this.httpClient.perform("/domains", super.getAuthenticationProvider().token(), HttpMethod.GET, null, MediaType.get("application/json"));

            if (!response.isSuccessful()) {
                throw new ResendException("Failed to retrieve domains list: " + response.getCode() + " " + response.getBody());
            }

            String responseBody = response.getBody();

            ListDomainsResponse listDomainsResponse = resendMapper.readValue(responseBody, ListDomainsResponse.class);
            return listDomainsResponse;
        } catch (Exception e) {
            throw new ResendException("Error retrieving domain: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a domain based on the provided domain ID and returns a DeleteDomainResponse.
     *
     * @param domainId The unique identifier of the domain to delete.
     * @return A DeleteDomainResponse representing the result of the domain deletion operation.
     * @throws ResendException If an error occurs during the domain deletion process.
     */
    public DeleteDomainResponse delete(String domainId) throws ResendException {

        try {
            AbstractHttpResponse<String> response = httpClient.perform("/domains/" + domainId, getAuthenticationProvider().token(), HttpMethod.DELETE, "", null);

            if (!response.isSuccessful()) {
                throw new ResendException("Failed to delete domain: " + response.getCode() + " " + response.getBody());
            }

            String responseBody = response.getBody();
            DeleteDomainResponse deleteDomainResponse = resendMapper.readValue(responseBody, DeleteDomainResponse.class);

            return deleteDomainResponse;
        } catch (Exception e) {
            throw new ResendException(e.getMessage());
        }
    }
}