package com.resend.services.suppressions;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.suppressions.model.AddSuppressionOptions;
import com.resend.services.suppressions.model.AddSuppressionResponseSuccess;
import com.resend.services.suppressions.model.AddSuppressionsOptions;
import com.resend.services.suppressions.model.AddSuppressionsResponseSuccess;
import com.resend.services.suppressions.model.GetSuppressionResponseSuccess;
import com.resend.services.suppressions.model.ListSuppressionsParams;
import com.resend.services.suppressions.model.ListSuppressionsResponseSuccess;
import com.resend.services.suppressions.model.RemoveSuppressionResponseSuccess;
import com.resend.services.suppressions.model.RemoveSuppressionsOptions;
import com.resend.services.suppressions.model.RemoveSuppressionsResponseSuccess;
import com.resend.services.suppressions.model.SuppressionOrigin;
import com.resend.services.util.SuppressionsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class SuppressionsTest {

    private static final String ADD_SINGLE_RESPONSE_JSON =
            "{\"object\":\"suppression\",\"id\":\"e169aa45-1ecf-4183-9955-b1499d5701d3\"}";

    private static final String REMOVE_SINGLE_RESPONSE_JSON =
            "{\"object\":\"suppression\",\"id\":\"e169aa45-1ecf-4183-9955-b1499d5701d3\",\"deleted\":true}";

    private static final String ADD_RESPONSE_JSON = "{\"data\":[" +
            "{\"object\":\"suppression\",\"id\":\"e169aa45-1ecf-4183-9955-b1499d5701d3\"}," +
            "{\"object\":\"suppression\",\"id\":\"520784e2-887d-4c25-b53c-4ad46ad38100\"}" +
            "]}";

    private static final String GET_RESPONSE_JSON =
            "{\"object\":\"suppression\",\"id\":\"e169aa45-1ecf-4183-9955-b1499d5701d3\"," +
            "\"email\":\"steve.wozniak@example.com\",\"origin\":\"bounce\"," +
            "\"source_id\":\"4ef9a417-02e9-4d39-ad75-9611e0fcc33c\"," +
            "\"created_at\":\"2026-10-06T23:47:56.678Z\"}";

    private static final String LIST_RESPONSE_JSON = "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"object\":\"suppression\",\"id\":\"e169aa45-1ecf-4183-9955-b1499d5701d3\"," +
            "\"email\":\"steve.wozniak@example.com\",\"origin\":\"manual\",\"source_id\":null," +
            "\"created_at\":\"2026-10-06T23:47:56.678Z\"}," +
            "{\"object\":\"suppression\",\"id\":\"520784e2-887d-4c25-b53c-4ad46ad38100\"," +
            "\"email\":\"susan.kare@example.com\",\"origin\":\"bounce\"," +
            "\"source_id\":\"4ef9a417-02e9-4d39-ad75-9611e0fcc33c\"," +
            "\"created_at\":\"2026-10-07T08:12:03.412Z\"}" +
            "]}";

    private static final String REMOVE_RESPONSE_JSON = "{\"data\":[" +
            "{\"object\":\"suppression\",\"id\":\"e169aa45-1ecf-4183-9955-b1499d5701d3\",\"deleted\":true}" +
            "]}";

    @Mock
    private IHttpClient httpClient;

    private Suppressions suppressions;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        suppressions = new Suppressions("test-api-key", httpClient);
    }

    @Test
    public void testAddSuppression_Success() throws ResendException {
        AddSuppressionOptions request = SuppressionsUtil.addSuppressionRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, ADD_SINGLE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/suppressions"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        AddSuppressionResponseSuccess response = suppressions.add(request);

        assertNotNull(response);
        assertEquals("e169aa45-1ecf-4183-9955-b1499d5701d3", response.getId());
        assertEquals("suppression", response.getObject());
    }

    @Test
    public void testAddSuppression_ApiError_ThrowsResendException() throws ResendException {
        AddSuppressionOptions request = SuppressionsUtil.addSuppressionRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid request\"}", false);

        when(httpClient.perform(eq("/suppressions"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> suppressions.add(request));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testRemoveSuppressionById_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_SINGLE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/suppressions/e169aa45-1ecf-4183-9955-b1499d5701d3"), anyString(), eq(HttpMethod.DELETE), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        RemoveSuppressionResponseSuccess response = suppressions.remove("e169aa45-1ecf-4183-9955-b1499d5701d3");

        assertNotNull(response);
        assertEquals("e169aa45-1ecf-4183-9955-b1499d5701d3", response.getId());
        assertEquals("suppression", response.getObject());
        assertTrue(response.getDeleted());
    }

    @Test
    public void testRemoveSuppressionByEmail_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_SINGLE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/suppressions/steve.wozniak@example.com"), anyString(), eq(HttpMethod.DELETE), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        RemoveSuppressionResponseSuccess response = suppressions.remove("steve.wozniak@example.com");

        assertNotNull(response);
        assertTrue(response.getDeleted());
    }

    @Test
    public void testRemoveSuppression_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"Suppression not found\"}", false);

        when(httpClient.perform(eq("/suppressions/123"), anyString(), eq(HttpMethod.DELETE), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> suppressions.remove("123"));
    }

    @Test
    public void testAddSuppressions_Success() throws ResendException {
        AddSuppressionsOptions request = SuppressionsUtil.addSuppressionsRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, ADD_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/suppressions/batch/add"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        AddSuppressionsResponseSuccess response = suppressions.batch().add(request);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals("e169aa45-1ecf-4183-9955-b1499d5701d3", response.getData().get(0).getId());
        assertEquals("suppression", response.getData().get(0).getObject());
        assertEquals("520784e2-887d-4c25-b53c-4ad46ad38100", response.getData().get(1).getId());
    }

    @Test
    public void testAddSuppressions_ApiError_ThrowsResendException() throws ResendException {
        AddSuppressionsOptions request = SuppressionsUtil.addSuppressionsRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid request\"}", false);

        when(httpClient.perform(eq("/suppressions/batch/add"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> suppressions.batch().add(request));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testGetSuppressionById_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/suppressions/e169aa45-1ecf-4183-9955-b1499d5701d3"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetSuppressionResponseSuccess response = suppressions.get("e169aa45-1ecf-4183-9955-b1499d5701d3");

        assertNotNull(response);
        assertEquals("e169aa45-1ecf-4183-9955-b1499d5701d3", response.getId());
        assertEquals("steve.wozniak@example.com", response.getEmail());
        assertEquals("bounce", response.getOrigin());
        assertEquals("4ef9a417-02e9-4d39-ad75-9611e0fcc33c", response.getSourceId());
        assertEquals("2026-10-06T23:47:56.678Z", response.getCreatedAt());
        assertEquals("suppression", response.getObject());
    }

    @Test
    public void testGetSuppressionByEmail_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/suppressions/steve.wozniak@example.com"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetSuppressionResponseSuccess response = suppressions.get("steve.wozniak@example.com");

        assertNotNull(response);
        assertEquals("e169aa45-1ecf-4183-9955-b1499d5701d3", response.getId());
        assertEquals("steve.wozniak@example.com", response.getEmail());
    }

    @Test
    public void testGetSuppression_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"Suppression not found\"}", false);

        when(httpClient.perform(eq("/suppressions/123"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> suppressions.get("123"));
    }

    @Test
    public void testListSuppressions_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/suppressions"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListSuppressionsResponseSuccess response = suppressions.list();

        assertNotNull(response);
        assertEquals("list", response.getObject());
        assertFalse(response.hasMore());
        assertEquals(2, response.getData().size());
        assertEquals("e169aa45-1ecf-4183-9955-b1499d5701d3", response.getData().get(0).getId());
        assertEquals("steve.wozniak@example.com", response.getData().get(0).getEmail());
        assertEquals("manual", response.getData().get(0).getOrigin());
        assertNull(response.getData().get(0).getSourceId());
        assertEquals("bounce", response.getData().get(1).getOrigin());
        assertEquals("4ef9a417-02e9-4d39-ad75-9611e0fcc33c", response.getData().get(1).getSourceId());
    }

    @Test
    public void testListSuppressionsWithParams_Success() throws ResendException {
        ListSuppressionsParams params = SuppressionsUtil.listSuppressionsParams();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/suppressions?limit=20&origin=bounce"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListSuppressionsResponseSuccess response = suppressions.list(params);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
    }

    @Test
    public void testListSuppressionsParams_ToQueryString() {
        ListSuppressionsParams params = ListSuppressionsParams.builder()
                .limit(50)
                .after("cursor_abc")
                .origin(SuppressionOrigin.MANUAL)
                .build();

        assertEquals("?limit=50&after=cursor_abc&origin=manual", params.toQueryString());
    }

    @Test
    public void testListSuppressions_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(401,
                "{\"name\":\"missing_api_key\",\"message\":\"Missing API key\"}", false);

        when(httpClient.perform(eq("/suppressions"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        assertThrows(ResendException.class, () -> suppressions.list());
    }

    @Test
    public void testRemoveSuppressionsByEmails_Success() throws ResendException {
        RemoveSuppressionsOptions request = SuppressionsUtil.removeSuppressionsByEmailsRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/suppressions/batch/remove"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        RemoveSuppressionsResponseSuccess response = suppressions.batch().remove(request);

        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertEquals("e169aa45-1ecf-4183-9955-b1499d5701d3", response.getData().get(0).getId());
        assertEquals("suppression", response.getData().get(0).getObject());
        assertTrue(response.getData().get(0).getDeleted());
    }

    @Test
    public void testRemoveSuppressionsByIds_Success() throws ResendException {
        RemoveSuppressionsOptions request = SuppressionsUtil.removeSuppressionsByIdsRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/suppressions/batch/remove"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        RemoveSuppressionsResponseSuccess response = suppressions.batch().remove(request);

        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertTrue(response.getData().get(0).getDeleted());
    }

    @Test
    public void testAddSuppressionsOptions_EmptyEmails_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> AddSuppressionsOptions.builder().build());
    }

    @Test
    public void testAddSuppressionsOptions_MoreThan100Emails_ThrowsIllegalArgumentException() {
        AddSuppressionsOptions.Builder builder = AddSuppressionsOptions.builder();
        for (int i = 0; i <= 100; i++) {
            builder.email("user" + i + "@example.com");
        }
        assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    public void testRemoveSuppressionsOptions_NeitherEmailsNorIds_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> RemoveSuppressionsOptions.builder().build());
    }

    @Test
    public void testRemoveSuppressionsOptions_BothEmailsAndIds_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> RemoveSuppressionsOptions.builder()
                .email("steve.wozniak@example.com")
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .build());
    }

    @Test
    public void testListSuppressionsParams_ToQueryString_EncodesValues() {
        ListSuppressionsParams params = ListSuppressionsParams.builder()
                .after("cursor&abc=def")
                .build();

        assertEquals("?after=cursor%26abc%3Ddef", params.toQueryString());
    }

    @Test
    public void testRemoveSuppressions_ApiError_ThrowsResendException() throws ResendException {
        RemoveSuppressionsOptions request = SuppressionsUtil.removeSuppressionsByIdsRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid request\"}", false);

        when(httpClient.perform(eq("/suppressions/batch/remove"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> suppressions.batch().remove(request));
        assertEquals(422, (int) ex.getStatusCode());
    }
}
