package com.resend.services.audiences;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.audiences.model.CreateAudienceOptions;
import com.resend.services.audiences.model.CreateAudienceResponseSuccess;
import com.resend.services.audiences.model.GetAudienceResponseSuccess;
import com.resend.services.audiences.model.ListAudiencesResponseSuccess;
import com.resend.services.audiences.model.RemoveAudienceResponseSuccess;
import com.resend.services.util.AudiencesUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class AudiencesTest {

    private static final String CREATE_RESPONSE_JSON =
            "{\"id\":\"123\",\"name\":\"aud\",\"object\":\"audience\"}";

    private static final String LIST_RESPONSE_JSON = "{\"data\":[" +
            "{\"id\":\"1\",\"name\":\"test1\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\"}," +
            "{\"id\":\"2\",\"name\":\"test2\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\"}," +
            "{\"id\":\"3\",\"name\":\"test3\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\"}" +
            "],\"object\":\"list\",\"has_more\":true}";

    private static final String REMOVE_RESPONSE_JSON =
            "{\"id\":\"123\",\"object\":\"audience\",\"deleted\":true}";

    private static final String GET_RESPONSE_JSON =
            "{\"id\":\"123\",\"name\":\"aud\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\",\"object\":\"audience\"}";

    @Mock
    private IHttpClient httpClient;

    private Audiences audiences;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        audiences = new Audiences("test-api-key", httpClient);
    }

    @Test
    public void testCreateAudience_Success() throws ResendException {
        CreateAudienceOptions request = AudiencesUtil.createAudienceRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/audiences"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateAudienceResponseSuccess response = audiences.create(request);

        assertNotNull(response);
        assertEquals("123", response.getId());
        assertEquals("aud", response.getName());
        assertEquals("audience", response.getObject());
    }

    @Test
    public void testCreateAudience_ApiError_ThrowsResendException() throws ResendException {
        CreateAudienceOptions request = AudiencesUtil.createAudienceRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid request\"}", false);

        when(httpClient.perform(eq("/audiences"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> audiences.create(request));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testDeleteAudience_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/audiences/123"), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveAudienceResponseSuccess response = audiences.remove("123");

        assertNotNull(response);
        assertEquals("123", response.getId());
        assertTrue(response.getDeleted());
    }

    @Test
    public void testDeleteAudience_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"Audience not found\"}", false);

        when(httpClient.perform(eq("/audiences/123"), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> audiences.remove("123"));
    }

    @Test
    public void testGetAudience_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/audiences/123"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetAudienceResponseSuccess response = audiences.get("123");

        assertNotNull(response);
        assertEquals("123", response.getId());
        assertEquals("aud", response.getName());
    }

    @Test
    public void testGetAudience_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"Audience not found\"}", false);

        when(httpClient.perform(eq("/audiences/123"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> audiences.get("123"));
    }

    @Test
    public void testListAudiences_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/audiences"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAudiencesResponseSuccess response = audiences.list();

        assertNotNull(response);
        assertEquals(3, response.getData().size());
        assertEquals("list", response.getObject());
    }

    @Test
    public void testListAudiences_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(401,
                "{\"name\":\"unauthorized\",\"message\":\"Unauthorized\"}", false);

        when(httpClient.perform(eq("/audiences"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> audiences.list());
    }

    @Test
    public void testListAudiencesWithPagination_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/audiences?limit=3"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        com.resend.core.net.ListParams params = com.resend.core.net.ListParams.builder().limit(3).build();
        ListAudiencesResponseSuccess response = audiences.list(params);

        assertNotNull(response);
        assertEquals(3, response.getData().size());
        assertEquals("list", response.getObject());
    }
}
