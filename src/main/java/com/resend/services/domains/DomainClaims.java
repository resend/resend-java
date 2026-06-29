package com.resend.services.domains;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
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
     * Claims a domain already verified by another team.
     *
     * @param claimDomainOptions The request object containing the domain claim details.
     * @return A DomainClaimResponseSuccess representing the created claim.
     * @throws ResendException If an error occurs during the domain claim process.
     */
    public DomainClaimResponseSuccess create(ClaimDomainOptions claimDomainOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(claimDomainOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/domains/claim", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, DomainClaimResponseSuccess.class);
    }

    /**
     * Retrieves the latest claim for a domain.
     *
     * @param domainId The placeholder domain ID returned when the claim was created.
     * @return A DomainClaimResponseSuccess representing the current claim state.
     * @throws ResendException If an error occurs during the retrieval process.
     */
    public DomainClaimResponseSuccess get(String domainId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/domains/" + domainId + "/claim", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, DomainClaimResponseSuccess.class);
    }

    /**
     * Triggers DNS verification for a domain claim.
     *
     * @param domainId The placeholder domain ID returned when the claim was created.
     * @return A DomainClaimResponseSuccess representing the claim after verification is triggered.
     * @throws ResendException If an error occurs during the verification process.
     */
    public DomainClaimResponseSuccess verify(String domainId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/domains/" + domainId + "/claim/verify", super.apiKey, HttpMethod.POST, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, DomainClaimResponseSuccess.class);
    }
}
