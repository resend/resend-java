package com.resend.services.domains;

import com.resend.core.exception.ResendException;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.service.BaseService;
import com.resend.services.domains.model.Domain;
import com.resend.services.domains.model.EnableForgeOptions;
import com.resend.services.domains.model.UpdateForgeOptions;
import com.resend.services.domains.model.VerifyForgeResponse;
import okhttp3.MediaType;

/**
 * Represents the Resend Domain Forge module.
 */
public final class DomainForge extends BaseService {

    /**
     * Constructs an instance of the {@code DomainForge} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public DomainForge(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code DomainForge} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public DomainForge(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Enables Forge on a domain, keeping the domain's current return path.
     * Enabling an already-migrated domain is idempotent and returns its current state.
     *
     * @param domainId The unique identifier of the domain.
     * @return A Domain representing the domain after Forge is enabled.
     * @throws ResendException If an error occurs while enabling Forge.
     */
    public Domain enable(String domainId) throws ResendException {
        return enable(domainId, EnableForgeOptions.builder().build());
    }

    /**
     * Enables Forge on a domain with the provided options.
     * Enabling an already-migrated domain is idempotent and returns its current state.
     *
     * @param domainId            The unique identifier of the domain.
     * @param enableForgeOptions  The request object containing the Forge activation details.
     * @return A Domain representing the domain after Forge is enabled.
     * @throws ResendException If an error occurs while enabling Forge.
     */
    public Domain enable(String domainId, EnableForgeOptions enableForgeOptions) throws ResendException {
        if (enableForgeOptions == null) {
            throw new ResendException("enableForgeOptions must not be null");
        }
        String payload = super.resendMapper.writeValue(enableForgeOptions);
        return execute("/domains/" + domainId + "/forge", HttpMethod.POST, payload, MediaType.get("application/json"), Domain.class);
    }

    /**
     * Updates the Forge configuration of a domain, moving its return path and re-verifying.
     *
     * @param domainId            The unique identifier of the domain.
     * @param updateForgeOptions  The request object containing the new return path.
     * @return A Domain representing the domain after the update.
     * @throws ResendException If an error occurs while updating the Forge configuration.
     */
    public Domain update(String domainId, UpdateForgeOptions updateForgeOptions) throws ResendException {
        if (updateForgeOptions == null) {
            throw new ResendException("updateForgeOptions must not be null");
        }
        String payload = super.resendMapper.writeValue(updateForgeOptions);
        return execute("/domains/" + domainId + "/forge", HttpMethod.PATCH, payload, MediaType.get("application/json"), Domain.class);
    }

    /**
     * Triggers an asynchronous Forge verification for a domain. Callers should poll
     * {@code Domains.get} to observe the resulting Forge status.
     *
     * @param domainId The unique identifier of the domain.
     * @return A VerifyForgeResponse representing the result of the verification trigger.
     * @throws ResendException If an error occurs while triggering the verification.
     */
    public VerifyForgeResponse verify(String domainId) throws ResendException {
        return execute("/domains/" + domainId + "/forge/verify", HttpMethod.POST, "", null, VerifyForgeResponse.class);
    }
}
