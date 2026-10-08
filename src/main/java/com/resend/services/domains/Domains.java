package com.resend.services.domains;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
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
     * Constructs an instance of the {@code Domains} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Domains(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Creates a domain based on the provided CreateDomainOptions and returns a CreateDomainResponse.
     *
     * @param createDomainOptions The request object containing the domain creation details.
     * @return A CreateDomainResponse representing the result of the domain creation operation.
     * @throws ResendException If an error occurs during the domain creation process.
     */
    public CreateDomainResponse create(CreateDomainOptions createDomainOptions) throws ResendException {
        return create(createDomainOptions, (RequestOptions) null);
    }

    /**
     * Creates a domain based on the provided CreateDomainOptions and returns a CreateDomainResponse.
     *
     * @param createDomainOptions The request object containing the domain creation details.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A CreateDomainResponse representing the result of the domain creation operation.
     * @throws ResendException If an error occurs during the domain creation process.
     */
    public CreateDomainResponse create(CreateDomainOptions createDomainOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createDomainOptions);
        return execute("/domains", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, CreateDomainResponse.class);
    }

    /**
     * Retrieves a domain based on the provided domain ID and returns a Domain object.
     *
     * @param domainId The unique identifier of the domain to retrieve.
     * @return A Domain object representing the retrieved domain.
     * @throws ResendException If an error occurs during the domain retrieval process.
     */
    public Domain get(String domainId) throws ResendException {
        return get(domainId, (RequestOptions) null);
    }

    /**
     * Retrieves a domain based on the provided domain ID and returns a Domain object.
     *
     * @param domainId The unique identifier of the domain to retrieve.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A Domain object representing the retrieved domain.
     * @throws ResendException If an error occurs during the domain retrieval process.
     */
    public Domain get(String domainId, RequestOptions requestOptions) throws ResendException {
        return execute("/domains/" + domainId, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, Domain.class);

    }

    /**
     * Verifies a domain based on the provided domain ID and returns a VerifyDomainResponse.
     *
     * @param domainId The unique identifier of the domain to verify.
     * @return A VerifyDomainResponse representing the result of the domain verification operation.
     * @throws ResendException If an error occurs during the domain verification process.
     */
    public VerifyDomainResponse verify(String domainId) throws ResendException {
        return verify(domainId, (RequestOptions) null);
    }

    /**
     * Verifies a domain based on the provided domain ID and returns a VerifyDomainResponse.
     *
     * @param domainId The unique identifier of the domain to verify.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A VerifyDomainResponse representing the result of the domain verification operation.
     * @throws ResendException If an error occurs during the domain verification process.
     */
    public VerifyDomainResponse verify(String domainId, RequestOptions requestOptions) throws ResendException {
        return execute("/domains/" + domainId + "/verify", HttpMethod.POST, "", null, requestOptions, VerifyDomainResponse.class);
    }

    /**
     * Retrieves a list of domains and returns a ListDomainsResponse.
     *
     * @return A ListDomainsResponse containing the list of domains.
     * @throws ResendException If an error occurs during the domain list retrieval process.
     */
    public ListDomainsResponse list() throws ResendException {
        return list((RequestOptions) null);
    }

    /**
     * Retrieves a list of domains and returns a ListDomainsResponse.
     *
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListDomainsResponse containing the list of domains.
     * @throws ResendException If an error occurs during the domain list retrieval process.
     */
    public ListDomainsResponse list(RequestOptions requestOptions) throws ResendException {
        return execute("/domains", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListDomainsResponse.class);
    }

    /**
     * Retrieves a paginated list of domains and returns a ListDomainsResponse.
     *
     * @param params The params used to customize the list.
     * @return A ListDomainsResponse containing the paginated list of domains.
     * @throws ResendException If an error occurs during the domain list retrieval process.
     */
    public ListDomainsResponse list(ListParams params) throws ResendException {
        return list(params, (RequestOptions) null);
    }

    /**
     * Retrieves a paginated list of domains and returns a ListDomainsResponse.
     *
     * @param params The params used to customize the list.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListDomainsResponse containing the paginated list of domains.
     * @throws ResendException If an error occurs during the domain list retrieval process.
     */
    public ListDomainsResponse list(ListParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/domains" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListDomainsResponse.class);
    }

    /**
     * Updates a domain based on the provided domain ID and returns a UpdateDomainResponseSuccess.
     *
     * @param updateDomainOptions The object containing the information to be updated.
     * @return A UpdateDomainResponseSuccess representing the result of the domain update operation.
     * @throws ResendException If an error occurs during the domain update process.
     */
    public UpdateDomainResponseSuccess update(UpdateDomainOptions updateDomainOptions) throws ResendException {
        return update(updateDomainOptions, (RequestOptions) null);
    }

    /**
     * Updates a domain based on the provided domain ID and returns a UpdateDomainResponseSuccess.
     *
     * @param updateDomainOptions The object containing the information to be updated.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A UpdateDomainResponseSuccess representing the result of the domain update operation.
     * @throws ResendException If an error occurs during the domain update process.
     */
    public UpdateDomainResponseSuccess update(UpdateDomainOptions updateDomainOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateDomainOptions);
        return execute("/domains/" + updateDomainOptions.getId(), HttpMethod.PATCH, payload, MediaType.get("application/json"), requestOptions, UpdateDomainResponseSuccess.class);
    }

    /**
     * Returns a DomainClaims object that can be used to interact with the Domain Claims service.
     *
     * @return A DomainClaims object.
     */
    public DomainClaims claims() {
        return new DomainClaims(apiKey, this.httpClient);
    }

    /**
     * Deletes a domain based on the provided domain ID and returns a RemoveDomainResponse.
     *
     * @param domainId The unique identifier of the domain to delete.
     * @return A RemoveDomainResponse representing the result of the domain deletion operation.
     * @throws ResendException If an error occurs during the domain deletion process.
     */
    public RemoveDomainResponse remove(String domainId) throws ResendException {
        return remove(domainId, (RequestOptions) null);
    }

    /**
     * Deletes a domain based on the provided domain ID and returns a RemoveDomainResponse.
     *
     * @param domainId The unique identifier of the domain to delete.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A RemoveDomainResponse representing the result of the domain deletion operation.
     * @throws ResendException If an error occurs during the domain deletion process.
     */
    public RemoveDomainResponse remove(String domainId, RequestOptions requestOptions) throws ResendException {
        return execute("/domains/" + domainId, HttpMethod.DELETE, "", null, requestOptions, RemoveDomainResponse.class);
    }
}