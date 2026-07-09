package com.resend.services.oauthgrants;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.oauthgrants.model.ListOAuthGrantsResponseSuccess;
import com.resend.services.oauthgrants.model.RemoveOAuthGrantResponseSuccess;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class OAuthGrantsTest {

    private static final String LIST_RESPONSE_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"id\":\"650e8400-e29b-41d4-a716-446655440001\",\"client_id\":\"430eed87-632a-4ea6-90db-0aace67ec228\"," +
            "\"scopes\":[\"emails:send\"],\"resource\":null,\"created_at\":\"2026-04-08 00:11:13.110779+00\"," +
            "\"revoked_at\":null,\"revoked_reason\":null," +
            "\"client\":{\"name\":\"Resend CLI\",\"logo_uri\":\"https://example.com/logo.png\"}}," +
            "{\"id\":\"650e8400-e29b-41d4-a716-446655440002\",\"client_id\":\"430eed87-632a-4ea6-90db-0aace67ec228\"," +
            "\"scopes\":[\"emails:send\",\"domains:read\"],\"resource\":\"https://api.resend.com\"," +
            "\"created_at\":\"2026-04-07 00:11:13.110779+00\",\"revoked_at\":\"2026-04-09 00:11:13.110779+00\"," +
            "\"revoked_reason\":\"revoked_from_api\"," +
            "\"client\":{\"name\":\"Resend CLI\",\"logo_uri\":\"https://example.com/logo.png\"}}" +
            "]}";

    private static final String REMOVE_RESPONSE_JSON =
            "{\"object\":\"oauth_grant\",\"id\":\"650e8400-e29b-41d4-a716-446655440001\"," +
            "\"revoked_at\":\"2026-04-08T00:11:13.110Z\",\"revoked_reason\":\"revoked_from_api\"}";

    @Mock
    private IHttpClient httpClient;

    private OAuthGrants oauthGrants;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        oauthGrants = new OAuthGrants("test-api-key", httpClient);
    }

    @Test
    public void testListOAuthGrants_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/oauth/grants"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListOAuthGrantsResponseSuccess res = oauthGrants.list();

        assertNotNull(res);
        assertEquals("list", res.getObject());
        assertFalse(res.hasMore());
        assertEquals(2, res.getData().size());
        assertEquals("650e8400-e29b-41d4-a716-446655440001", res.getData().get(0).getId());
        assertEquals("emails:send", res.getData().get(0).getScopes().get(0));
        assertNull(res.getData().get(0).getRevokedAt());
        assertEquals("revoked_from_api", res.getData().get(1).getRevokedReason());
        assertEquals("Resend CLI", res.getData().get(0).getClient().getName());
    }

    @Test
    public void testListOAuthGrantsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(10).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(startsWith("/oauth/grants?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListOAuthGrantsResponseSuccess res = oauthGrants.list(params);

        assertNotNull(res);
        assertEquals(2, res.getData().size());
    }

    @Test
    public void testListOAuthGrants_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(401, "{\"name\":\"unauthorized\",\"message\":\"Invalid API key\"}", false);

        when(httpClient.perform(eq("/oauth/grants"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> oauthGrants.list());
        assertEquals(401, (int) ex.getStatusCode());
    }

    @Test
    public void testRemoveOAuthGrant_Success() throws ResendException {
        String oauthGrantId = "650e8400-e29b-41d4-a716-446655440001";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/oauth/grants/" + oauthGrantId), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveOAuthGrantResponseSuccess res = oauthGrants.remove(oauthGrantId);

        assertNotNull(res);
        assertEquals("oauth_grant", res.getObject());
        assertEquals(oauthGrantId, res.getId());
        assertEquals("revoked_from_api", res.getRevokedReason());
        assertNotNull(res.getRevokedAt());
    }

    @Test
    public void testRemoveOAuthGrant_ApiError_ThrowsResendException() throws ResendException {
        String oauthGrantId = "does-not-exist";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404, "{\"name\":\"not_found\",\"message\":\"Grant not found\"}", false);

        when(httpClient.perform(eq("/oauth/grants/" + oauthGrantId), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> oauthGrants.remove(oauthGrantId));
        assertEquals(404, (int) ex.getStatusCode());
    }
}
