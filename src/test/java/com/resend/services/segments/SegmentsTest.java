package com.resend.services.segments;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.segments.model.*;
import com.resend.services.util.SegmentsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class SegmentsTest {

    private static final String CREATE_RESPONSE_JSON = "{\"id\":\"123\",\"name\":\"seg\",\"object\":\"audience\"}";
    private static final String REMOVE_RESPONSE_JSON = "{\"id\":\"123\",\"object\":\"audience\",\"deleted\":true}";
    private static final String GET_RESPONSE_JSON =
            "{\"id\":\"123\",\"name\":\"seg\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\",\"object\":\"audience\"}";
    private static final String LIST_RESPONSE_JSON =
            "{\"data\":[" +
            "{\"id\":\"1\",\"name\":\"test1\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\"}," +
            "{\"id\":\"2\",\"name\":\"test2\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\"}," +
            "{\"id\":\"3\",\"name\":\"test3\",\"created_at\":\"2023-04-08T00:11:13.110779+00:00\"}" +
            "],\"object\":\"list\",\"has_more\":true}";

    @Mock
    private IHttpClient httpClient;

    private Segments segments;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        segments = new Segments("test-api-key", httpClient);
    }

    @Test
    public void testCreateSegment_Success() throws ResendException {
        CreateSegmentOptions param = SegmentsUtil.createSegmentRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/segments"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateSegmentResponseSuccess created = segments.create(param);

        assertNotNull(created);
        assertEquals("123", created.getId());
        assertEquals("seg", created.getName());
        assertEquals("audience", created.getObject());
    }

    @Test
    public void testCreateSegment_ApiError_ThrowsResendException() throws ResendException {
        CreateSegmentOptions param = SegmentsUtil.createSegmentRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422, "{\"name\":\"validation_error\",\"message\":\"Invalid\"}", false);

        when(httpClient.perform(eq("/segments"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> segments.create(param));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testDeleteSegment_Success() throws ResendException {
        String segmentId = "123";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/segments/" + segmentId), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveSegmentResponseSuccess res = segments.remove(segmentId);

        assertNotNull(res);
        assertEquals("123", res.getId());
        assertTrue(res.getDeleted());
    }

    @Test
    public void testListSegments_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/segments"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListSegmentsResponseSuccess res = segments.list();

        assertNotNull(res);
        assertEquals(3, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListSegmentsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(3).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(startsWith("/audiences?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListSegmentsResponseSuccess res = segments.list(params);

        assertNotNull(res);
        assertEquals(3, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testGetSegment_Success() throws ResendException {
        String segmentId = "123";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/segments/" + segmentId), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetSegmentResponseSuccess res = segments.get(segmentId);

        assertNotNull(res);
        assertEquals("123", res.getId());
        assertEquals("seg", res.getName());
    }
}
