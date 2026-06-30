package com.resend.services.automations;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.automations.model.*;
import com.resend.services.util.AutomationsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class AutomationsTest {

    private static final String AUTOMATION_ID = "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794";

    private static final String CREATE_AUTOMATION_JSON =
            "{\"object\":\"automation\",\"id\":\"" + AUTOMATION_ID + "\"}";

    private static final String GET_AUTOMATION_JSON =
            "{\"object\":\"automation\",\"id\":\"" + AUTOMATION_ID + "\"," +
            "\"name\":\"Welcome Automation\",\"status\":\"enabled\"," +
            "\"created_at\":\"2024-12-01T10:00:00.000Z\",\"updated_at\":\"2024-12-02T10:00:00.000Z\"," +
            "\"steps\":[],\"connections\":[]}";

    private static final String LIST_AUTOMATIONS_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"id\":\"1\",\"name\":\"Automation 1\",\"status\":\"enabled\",\"created_at\":\"2024-12-01T10:00:00.000Z\",\"updated_at\":\"2024-12-01T10:00:00.000Z\"}," +
            "{\"id\":\"2\",\"name\":\"Automation 2\",\"status\":\"disabled\",\"created_at\":\"2024-12-02T10:00:00.000Z\",\"updated_at\":\"2024-12-02T10:00:00.000Z\"}," +
            "{\"id\":\"3\",\"name\":\"Automation 3\",\"status\":\"enabled\",\"created_at\":\"2024-12-03T10:00:00.000Z\",\"updated_at\":\"2024-12-03T10:00:00.000Z\"}" +
            "]}";

    private static final String UPDATE_AUTOMATION_JSON =
            "{\"object\":\"automation\",\"id\":\"" + AUTOMATION_ID + "\"}";

    private static final String DELETE_AUTOMATION_JSON =
            "{\"object\":\"automation\",\"id\":\"" + AUTOMATION_ID + "\",\"deleted\":true}";

    private static final String STOP_AUTOMATION_JSON =
            "{\"object\":\"automation\",\"id\":\"" + AUTOMATION_ID + "\",\"status\":\"disabled\"}";

    private static final String LIST_RUNS_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"id\":\"run_1\",\"status\":\"completed\",\"started_at\":\"2024-12-01T10:00:00.000Z\",\"finished_at\":\"2024-12-01T10:05:00.000Z\",\"created_at\":\"2024-12-01T10:00:00.000Z\"}," +
            "{\"id\":\"run_2\",\"status\":\"running\",\"started_at\":\"2024-12-02T10:00:00.000Z\",\"created_at\":\"2024-12-02T10:00:00.000Z\"}," +
            "{\"id\":\"run_3\",\"status\":\"failed\",\"started_at\":\"2024-12-03T10:00:00.000Z\",\"finished_at\":\"2024-12-03T10:02:00.000Z\",\"created_at\":\"2024-12-03T10:00:00.000Z\"}" +
            "]}";

    private static final String GET_RUN_JSON =
            "{\"object\":\"automation_run\",\"id\":\"run_1\",\"status\":\"completed\"," +
            "\"started_at\":\"2024-12-01T10:00:00.000Z\",\"finished_at\":\"2024-12-01T10:00:05.000Z\"," +
            "\"created_at\":\"2024-12-01T10:00:00.000Z\",\"steps\":[]}";

    @Mock
    private IHttpClient httpClient;

    private Automations automations;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        automations = new Automations("test-api-key", httpClient);
    }

    @Test
    public void testCreateAutomation_Success() throws ResendException {
        CreateAutomationOptions createOptions = AutomationsUtil.createAutomationRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_AUTOMATION_JSON, true);

        when(httpClient.perform(eq("/automations"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateAutomationResponseSuccess response = automations.create(createOptions);

        assertNotNull(response);
        assertEquals(AUTOMATION_ID, response.getId());
        assertEquals("automation", response.getObject());
    }

    @Test
    public void testCreateAutomation_ApiError_ThrowsResendException() throws ResendException {
        CreateAutomationOptions createOptions = AutomationsUtil.createAutomationRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid options\"}", false);

        when(httpClient.perform(eq("/automations"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> automations.create(createOptions));
        assertEquals(422, (int) ex.getStatusCode());
        assertEquals("Invalid options", ex.getMessage());
    }

    @Test
    public void testGetAutomation_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_AUTOMATION_JSON, true);

        when(httpClient.perform(eq("/automations/" + AUTOMATION_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        Automation response = automations.get(AUTOMATION_ID);

        assertNotNull(response);
        assertEquals(AUTOMATION_ID, response.getId());
        assertEquals("Welcome Automation", response.getName());
    }

    @Test
    public void testListAutomations_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_AUTOMATIONS_JSON, true);

        when(httpClient.perform(eq("/automations"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAutomationsResponseSuccess response = automations.list();

        assertNotNull(response);
        assertEquals(3, response.getData().size());
        assertEquals("list", response.getObject());
    }

    @Test
    public void testListAutomationsWithParams_Success() throws ResendException {
        ListAutomationsParams params = ListAutomationsParams.builder()
                .status(AutomationStatus.ENABLED)
                .limit(10)
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_AUTOMATIONS_JSON, true);

        when(httpClient.perform(startsWith("/automations?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAutomationsResponseSuccess response = automations.list(params);

        assertNotNull(response);
        assertEquals("list", response.getObject());
    }

    @Test
    public void testUpdateAutomation_Success() throws ResendException {
        UpdateAutomationOptions updateOptions = AutomationsUtil.updateAutomationRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_AUTOMATION_JSON, true);

        when(httpClient.perform(eq("/automations/" + AUTOMATION_ID), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateAutomationResponseSuccess response = automations.update(updateOptions);

        assertNotNull(response);
        assertEquals(AUTOMATION_ID, response.getId());
        assertEquals("automation", response.getObject());
    }

    @Test
    public void testDeleteAutomation_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, DELETE_AUTOMATION_JSON, true);

        when(httpClient.perform(eq("/automations/" + AUTOMATION_ID), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        DeleteAutomationResponseSuccess response = automations.remove(AUTOMATION_ID);

        assertNotNull(response);
        assertTrue(response.getDeleted());
        assertEquals(AUTOMATION_ID, response.getId());
    }

    @Test
    public void testStopAutomation_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, STOP_AUTOMATION_JSON, true);

        when(httpClient.perform(eq("/automations/" + AUTOMATION_ID + "/stop"), anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(httpResponse);

        StopAutomationResponseSuccess response = automations.stop(AUTOMATION_ID);

        assertNotNull(response);
        assertEquals(AutomationStatus.DISABLED, response.getStatus());
    }

    @Test
    public void testListAutomationRuns_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RUNS_JSON, true);

        when(httpClient.perform(eq("/automations/" + AUTOMATION_ID + "/runs"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAutomationRunsResponseSuccess response = automations.listRuns(AUTOMATION_ID);

        assertNotNull(response);
        assertEquals(3, response.getData().size());
    }

    @Test
    public void testListAutomationRunsWithParams_Success() throws ResendException {
        ListAutomationRunsParams params = ListAutomationRunsParams.builder()
                .status(RunStatus.COMPLETED)
                .limit(10)
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RUNS_JSON, true);

        when(httpClient.perform(startsWith("/automations/" + AUTOMATION_ID + "/runs?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAutomationRunsResponseSuccess response = automations.listRuns(AUTOMATION_ID, params);

        assertNotNull(response);
        assertEquals(3, response.getData().size());
    }

    @Test
    public void testListAutomationRunsWithMultipleStatuses_Success() throws ResendException {
        ListAutomationRunsParams params = ListAutomationRunsParams.builder()
                .status(RunStatus.RUNNING, RunStatus.COMPLETED)
                .limit(10)
                .build();

        assertEquals(2, params.getStatus().size());
        assertEquals("?status=running,completed&limit=10", params.toQueryString());

        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RUNS_JSON, true);

        when(httpClient.perform(startsWith("/automations/" + AUTOMATION_ID + "/runs?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAutomationRunsResponseSuccess response = automations.listRuns(AUTOMATION_ID, params);

        assertNotNull(response);
    }

    @Test
    public void testGetAutomationRun_Success() throws ResendException {
        GetAutomationRunOptions options = GetAutomationRunOptions.builder()
                .automationId(AUTOMATION_ID)
                .runId("run_1")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_RUN_JSON, true);

        when(httpClient.perform(eq("/automations/" + AUTOMATION_ID + "/runs/run_1"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        AutomationRun response = automations.getRun(options);

        assertNotNull(response);
        assertEquals("run_1", response.getId());
        assertEquals(RunStatus.COMPLETED, response.getStatus());
    }
}
