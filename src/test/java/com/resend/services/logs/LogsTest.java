package com.resend.services.logs;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.logs.model.GetLogResponseSuccess;
import com.resend.services.logs.model.ListLogsResponseSuccess;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class LogsTest {

    private static final String GET_LOG_JSON =
            "{\"id\":\"log_123\",\"created_at\":\"2024-01-01T00:00:00.000Z\"," +
            "\"endpoint\":\"/emails\",\"method\":\"POST\",\"response_status\":200," +
            "\"user_agent\":\"resend-java/4.13.0\",\"object\":\"log\"}";

    private static final String LIST_LOGS_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"id\":\"log_1\",\"created_at\":\"2024-01-01T00:00:00.000Z\",\"endpoint\":\"/emails\",\"method\":\"POST\",\"response_status\":200,\"user_agent\":\"resend-java/4.13.0\"}," +
            "{\"id\":\"log_2\",\"created_at\":\"2024-01-02T00:00:00.000Z\",\"endpoint\":\"/domains\",\"method\":\"GET\",\"response_status\":200,\"user_agent\":\"resend-java/4.13.0\"}," +
            "{\"id\":\"log_3\",\"created_at\":\"2024-01-03T00:00:00.000Z\",\"endpoint\":\"/contacts\",\"method\":\"DELETE\",\"response_status\":204}" +
            "]}";

    @Mock
    private IHttpClient httpClient;

    private Logs logs;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        logs = new Logs("test-api-key", httpClient);
    }

    @Test
    public void testGetLog_Success() throws ResendException {
        String logId = "log_123";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_LOG_JSON, true);

        when(httpClient.perform(eq("/logs/" + logId), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetLogResponseSuccess res = logs.get(logId);

        assertNotNull(res);
        assertEquals("log_123", res.getId());
        assertEquals("/emails", res.getEndpoint());
        assertEquals("POST", res.getMethod());
        assertEquals(200, (int) res.getResponseStatus());
        assertEquals("log", res.getObject());
    }

    @Test
    public void testGetLog_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"Log not found\"}", false);

        when(httpClient.perform(eq("/logs/bad_id"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> logs.get("bad_id"));
        assertEquals(404, (int) ex.getStatusCode());
    }

    @Test
    public void testListLogs_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_LOGS_JSON, true);

        when(httpClient.perform(eq("/logs"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListLogsResponseSuccess res = logs.list();

        assertNotNull(res);
        assertEquals(3, res.getData().size());
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListLogs_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(500,
                "{\"name\":\"internal_server_error\",\"message\":\"Server error\"}", false);

        when(httpClient.perform(eq("/logs"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> logs.list());
        assertEquals(500, (int) ex.getStatusCode());
    }

    @Test
    public void testListLogsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(3).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_LOGS_JSON, true);

        when(httpClient.perform(startsWith("/logs?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListLogsResponseSuccess res = logs.list(params);

        assertNotNull(res);
        assertEquals("list", res.getObject());
    }

    @Test
    public void testListLogsWithAfterCursor_Success() throws ResendException {
        ListParams params = ListParams.builder().after("log_50").build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_LOGS_JSON, true);

        when(httpClient.perform(startsWith("/logs?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListLogsResponseSuccess res = logs.list(params);

        assertNotNull(res);
        assertEquals(3, res.getData().size());
    }

    @Test
    public void testListLogsWithBeforeCursor_Success() throws ResendException {
        ListParams params = ListParams.builder().before("log_100").build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_LOGS_JSON, true);

        when(httpClient.perform(startsWith("/logs?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListLogsResponseSuccess res = logs.list(params);

        assertNotNull(res);
        assertEquals(3, res.getData().size());
    }
}
