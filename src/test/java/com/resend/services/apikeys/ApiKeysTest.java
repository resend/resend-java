package com.resend.services.apikeys;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.apikeys.model.CreateApiKeyOptions;
import com.resend.services.apikeys.model.CreateApiKeyResponse;
import com.resend.services.apikeys.model.ListApiKeysResponse;
import com.resend.services.util.ApiKeysUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ApiKeysTest {

    private static final String CREATE_RESPONSE_JSON = "{\"id\":\"123\",\"token\":\"re_123\"}";

    private static final String LIST_RESPONSE_JSON = "{\"data\":[" +
            "{\"id\":\"abcdefg-4321-5678-ijklmnop\",\"name\":\"Production\"," +
            "\"created_at\":\"2023-04-08T00:11:13.110779+00:00\",\"last_used_at\":\"2023-04-26T20:21:26.844Z\"}," +
            "{\"id\":\"abcdefg-1234-5678-ijklmnop\",\"name\":\"Staging\"," +
            "\"created_at\":\"2023-04-08T00:11:13.110779+00:00\"}" +
            "],\"has_more\":true,\"object\":\"list\"}";

    @Mock
    private IHttpClient httpClient;

    private ApiKeys apiKeys;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        apiKeys = new ApiKeys("test-api-key", httpClient);
    }

    @Test
    public void testCreateApiKey_Success() throws ResendException {
        CreateApiKeyOptions request = ApiKeysUtil.createApiKeyRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/api-keys"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateApiKeyResponse response = apiKeys.create(request);

        assertNotNull(response);
        assertEquals("123", response.getId());
        assertEquals("re_123", response.getToken());
    }

    @Test
    public void testCreateApiKey_ApiError_ThrowsResendException() throws ResendException {
        CreateApiKeyOptions request = ApiKeysUtil.createApiKeyRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid request\"}", false);

        when(httpClient.perform(eq("/api-keys"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> apiKeys.create(request));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testDeleteApiKey_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, "", true);

        when(httpClient.perform(eq("/api-keys/123"), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        boolean result = apiKeys.remove("123");

        assertTrue(result);
    }

    @Test
    public void testDeleteApiKey_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"API key not found\"}", false);

        when(httpClient.perform(eq("/api-keys/123"), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> apiKeys.remove("123"));
    }

    @Test
    public void testListApiKeys_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/api-keys"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListApiKeysResponse response = apiKeys.list();

        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals("abcdefg-4321-5678-ijklmnop", response.getData().get(0).getId());
        assertEquals("list", response.getObject());
    }

    @Test
    public void testListApiKeys_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(401,
                "{\"name\":\"unauthorized\",\"message\":\"Unauthorized\"}", false);

        when(httpClient.perform(eq("/api-keys"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> apiKeys.list());
    }

    @Test
    public void testListApiKeysWithPagination_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/api-keys?limit=2"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        com.resend.core.net.ListParams params = com.resend.core.net.ListParams.builder().limit(2).build();
        ListApiKeysResponse response = apiKeys.list(params);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
    }
}
