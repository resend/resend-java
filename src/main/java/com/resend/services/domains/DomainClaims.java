package com.resend.services.domains;

import com.resend.core.exception.ResendException;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.RequestOptions;
import com.resend.core.service.BaseService;
import com.resend.services.domains.model.ClaimDomainOptions;
import com.resend.services.domains.model.DomainClaimResponseSuccess;
import okhttp3.MediaType;

/**
 * Represents the Resend Domain Claims module.
 */
public final class DomainClaims extends BaseService {

    /**
     * Constructs an instance of the {@code DomainClaims} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public DomainClaims(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code DomainClaims} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public DomainClaims(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Claims a domain already verified by another team.
     *
     * @param claimDomainOptions The request object containing the domain claim details.
     * @return A DomainClaimResponseSuccess representing the created claim.
     * @throws ResendException If an error occurs during the domain claim process.
     */
    public DomainClaimResponseSuccess create(ClaimDomainOptions claimDomainOptions) throws ResendException {
        return create(claimDomainOptions, (RequestOptions) null);
    }

    /**
     * Claims a domain already verified by another team.
     *
     * @param claimDomainOptions The request object containing the domain claim details.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A DomainClaimResponseSuccess representing the created claim.
     * @throws ResendException If an error occurs during the domain claim process.
     */
    public DomainClaimResponseSuccess create(ClaimDomainOptions claimDomainOptions, RequestOptions requestOptions) throws ResendException {
        if (claimDomainOptions == null) {
            throw new ResendException("claimDomainOptions must not be null");
        }
        String payload = super.resendMapper.writeValue(claimDomainOptions);
        return execute("/domains/claim", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, DomainClaimResponseSuccess.class);
    }

    /**
     * Retrieves the latest claim for a domain.
     *
     * @param domainId The placeholder domain ID returned when the claim was created.
     * @return A DomainClaimResponseSuccess representing the current claim state.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public DomainClaimResponseSuccess get(String domainId) throws ResendException {
        return get(domainId, (RequestOptions) null);
    }

    /**
     * Retrieves the latest claim for a domain.
     *
     * @param domainId The placeholder domain ID returned when the claim was created.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A DomainClaimResponseSuccess representing the current claim state.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public DomainClaimResponseSuccess get(String domainId, RequestOptions requestOptions) throws ResendException {
        return execute("/domains/" + domainId + "/claim", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, DomainClaimResponseSuccess.class);
    }

    /**
     * Triggers DNS verification for a domain claim.
     *
     * @param domainId The placeholder domain ID returned when the claim was created.
     * @return A DomainClaimResponseSuccess representing the claim after verification is triggered.
     * @throws ResendException If an error occurs during the verification process.
     */
    public DomainClaimResponseSuccess verify(String domainId) throws ResendException {
        return verify(domainId, (RequestOptions) null);
    }

    /**
     * Triggers DNS verification for a domain claim.
     *
     * @param domainId The placeholder domain ID returned when the claim was created.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A DomainClaimResponseSuccess representing the claim after verification is triggered.
     * @throws ResendException If an error occurs during the verification process.
     */
    public DomainClaimResponseSuccess verify(String domainId, RequestOptions requestOptions) throws ResendException {
        return execute("/domains/" + domainId + "/claim/verify", HttpMethod.POST, "", null, requestOptions, DomainClaimResponseSuccess.class);
    }
}
