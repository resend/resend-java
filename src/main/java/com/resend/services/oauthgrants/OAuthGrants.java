package com.resend.services.oauthgrants;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.oauthgrants.model.ListOAuthGrantsResponseSuccess;
import com.resend.services.oauthgrants.model.RevokeOAuthGrantResponseSuccess;
import okhttp3.MediaType;

/**
 * Represents the Resend OAuth Grants module.
 */
public class OAuthGrants extends BaseService {

    /**
     * Constructs an instance of the {@code OAuthGrants} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public OAuthGrants(final String apiKey) {
        super(apiKey);
    }

    OAuthGrants(final String apiKey, final IHttpClient httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Retrieves a list of OAuth grants for the authenticated team.
     *
     * @return A ListOAuthGrantsResponseSuccess containing the list of OAuth grants.
     * @throws ResendException If an error occurs during the OAuth grants list retrieval process.
     */
    public ListOAuthGrantsResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/oauth/grants", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListOAuthGrantsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of OAuth grants for the authenticated team.
     *
     * @param params The params used to customize the list.
     * @return A ListOAuthGrantsResponseSuccess containing the paginated list of OAuth grants.
     * @throws ResendException If an error occurs during the OAuth grants list retrieval process.
     */
    public ListOAuthGrantsResponseSuccess list(ListParams params) throws ResendException {
        String pathWithQuery = "/oauth/grants" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListOAuthGrantsResponseSuccess.class);
    }

    /**
     * Revokes an OAuth grant based on the provided OAuth grant ID.
     *
     * @param oauthGrantId The unique identifier of the OAuth grant to revoke.
     * @return The RevokeOAuthGrantResponseSuccess with the details of the revoked OAuth grant.
     * @throws ResendException If an error occurs during the OAuth grant revocation process.
     */
    public RevokeOAuthGrantResponseSuccess revoke(String oauthGrantId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/oauth/grants/" + oauthGrantId, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, RevokeOAuthGrantResponseSuccess.class);
    }
}
