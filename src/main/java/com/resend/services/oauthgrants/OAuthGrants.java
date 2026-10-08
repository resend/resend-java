package com.resend.services.oauthgrants;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
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

    /**
     * Constructs an instance of the {@code OAuthGrants} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public OAuthGrants(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Retrieves a list of OAuth grants for the authenticated team.
     *
     * @return A ListOAuthGrantsResponseSuccess containing the list of OAuth grants.
     * @throws ResendException If an error occurs during the OAuth grants list retrieval process.
     */
    public ListOAuthGrantsResponseSuccess list() throws ResendException {
        return list((RequestOptions) null);
    }

    /**
     * Retrieves a list of OAuth grants for the authenticated team.
     *
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListOAuthGrantsResponseSuccess containing the list of OAuth grants.
     * @throws ResendException If an error occurs during the OAuth grants list retrieval process.
     */
    public ListOAuthGrantsResponseSuccess list(RequestOptions requestOptions) throws ResendException {
        return execute("/oauth/grants", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListOAuthGrantsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of OAuth grants for the authenticated team.
     *
     * @param params The params used to customize the list.
     * @return A ListOAuthGrantsResponseSuccess containing the paginated list of OAuth grants.
     * @throws ResendException If an error occurs during the OAuth grants list retrieval process.
     */
    public ListOAuthGrantsResponseSuccess list(ListParams params) throws ResendException {
        return list(params, (RequestOptions) null);
    }

    /**
     * Retrieves a paginated list of OAuth grants for the authenticated team.
     *
     * @param params The params used to customize the list.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListOAuthGrantsResponseSuccess containing the paginated list of OAuth grants.
     * @throws ResendException If an error occurs during the OAuth grants list retrieval process.
     */
    public ListOAuthGrantsResponseSuccess list(ListParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/oauth/grants" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListOAuthGrantsResponseSuccess.class);
    }

    /**
     * Revokes an OAuth grant based on the provided OAuth grant ID.
     *
     * @param oauthGrantId The unique identifier of the OAuth grant to revoke.
     * @return The RevokeOAuthGrantResponseSuccess with the details of the revoked OAuth grant.
     * @throws ResendException If an error occurs during the OAuth grant revocation process.
     */
    public RevokeOAuthGrantResponseSuccess revoke(String oauthGrantId) throws ResendException {
        return revoke(oauthGrantId, (RequestOptions) null);
    }

    /**
     * Revokes an OAuth grant based on the provided OAuth grant ID.
     *
     * @param oauthGrantId The unique identifier of the OAuth grant to revoke.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The RevokeOAuthGrantResponseSuccess with the details of the revoked OAuth grant.
     * @throws ResendException If an error occurs during the OAuth grant revocation process.
     */
    public RevokeOAuthGrantResponseSuccess revoke(String oauthGrantId, RequestOptions requestOptions) throws ResendException {
        return execute("/oauth/grants/" + oauthGrantId, HttpMethod.DELETE, "", null, requestOptions, RevokeOAuthGrantResponseSuccess.class);
    }
}
