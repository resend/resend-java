package com.resend.services.automations;

import com.resend.core.exception.ResendException;
import com.resend.services.automations.model.*;
import com.resend.services.util.AutomationsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AutomationsTest {

    @Mock
    private Automations automations;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        automations = mock(Automations.class);
    }

    @Test
    public void testCreateAutomation_Success() throws ResendException {
        CreateAutomationResponseSuccess expectedResponse = AutomationsUtil.createAutomationResponse();
        CreateAutomationOptions createOptions = AutomationsUtil.createAutomationRequest();

        when(automations.create(createOptions)).thenReturn(expectedResponse);

        CreateAutomationResponseSuccess response = automations.create(createOptions);

        assertEquals(expectedResponse, response);
        verify(automations, times(1)).create(createOptions);
    }

    @Test
    public void testGetAutomation_Success() throws ResendException {
        String automationId = "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794";
        Automation expectedResponse = AutomationsUtil.getAutomationResponse();

        when(automations.get(automationId)).thenReturn(expectedResponse);

        Automation response = automations.get(automationId);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getName(), response.getName());
        verify(automations, times(1)).get(automationId);
    }

    @Test
    public void testListAutomations_Success() throws ResendException {
        ListAutomationsResponseSuccess expectedResponse = AutomationsUtil.listAutomationsResponse();

        when(automations.list()).thenReturn(expectedResponse);

        ListAutomationsResponseSuccess response = automations.list();

        assertNotNull(response);
        assertEquals(expectedResponse.getData().size(), response.getData().size());
        assertEquals(expectedResponse.getObject(), response.getObject());
        verify(automations, times(1)).list();
    }

    @Test
    public void testListAutomationsWithParams_Success() throws ResendException {
        ListAutomationsParams params = ListAutomationsParams.builder()
                .status(AutomationStatus.ENABLED)
                .limit(10)
                .build();

        ListAutomationsResponseSuccess expectedResponse = AutomationsUtil.listAutomationsResponse();

        when(automations.list(params)).thenReturn(expectedResponse);

        ListAutomationsResponseSuccess response = automations.list(params);

        assertNotNull(response);
        assertEquals(expectedResponse.getObject(), response.getObject());
        verify(automations, times(1)).list(params);
    }

    @Test
    public void testUpdateAutomation_Success() throws ResendException {
        UpdateAutomationResponseSuccess expectedResponse = AutomationsUtil.updateAutomationResponse();
        UpdateAutomationOptions updateOptions = AutomationsUtil.updateAutomationRequest();

        when(automations.update(updateOptions)).thenReturn(expectedResponse);

        UpdateAutomationResponseSuccess response = automations.update(updateOptions);

        assertEquals(expectedResponse, response);
        verify(automations, times(1)).update(updateOptions);
    }

    @Test
    public void testDeleteAutomation_Success() throws ResendException {
        String automationId = "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794";
        DeleteAutomationResponseSuccess expectedResponse = AutomationsUtil.deleteAutomationResponse();

        when(automations.remove(automationId)).thenReturn(expectedResponse);

        DeleteAutomationResponseSuccess response = automations.remove(automationId);

        assertEquals(expectedResponse, response);
        assertTrue(response.getDeleted());
        verify(automations, times(1)).remove(automationId);
    }

    @Test
    public void testStopAutomation_Success() throws ResendException {
        String automationId = "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794";
        StopAutomationResponseSuccess expectedResponse = AutomationsUtil.stopAutomationResponse();

        when(automations.stop(automationId)).thenReturn(expectedResponse);

        StopAutomationResponseSuccess response = automations.stop(automationId);

        assertEquals(expectedResponse, response);
        assertEquals(AutomationStatus.DISABLED, response.getStatus());
        verify(automations, times(1)).stop(automationId);
    }

    @Test
    public void testListAutomationRuns_Success() throws ResendException {
        String automationId = "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794";
        ListAutomationRunsResponseSuccess expectedResponse = AutomationsUtil.listAutomationRunsResponse();

        when(automations.listRuns(automationId)).thenReturn(expectedResponse);

        ListAutomationRunsResponseSuccess response = automations.listRuns(automationId);

        assertNotNull(response);
        assertEquals(expectedResponse.getData().size(), response.getData().size());
        verify(automations, times(1)).listRuns(automationId);
    }

    @Test
    public void testListAutomationRunsWithParams_Success() throws ResendException {
        String automationId = "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794";
        ListAutomationRunsParams params = ListAutomationRunsParams.builder()
                .status(RunStatus.COMPLETED)
                .limit(10)
                .build();

        ListAutomationRunsResponseSuccess expectedResponse = AutomationsUtil.listAutomationRunsResponse();

        when(automations.listRuns(automationId, params)).thenReturn(expectedResponse);

        ListAutomationRunsResponseSuccess response = automations.listRuns(automationId, params);

        assertNotNull(response);
        verify(automations, times(1)).listRuns(automationId, params);
    }

    @Test
    public void testListAutomationRunsWithMultipleStatuses_Success() throws ResendException {
        String automationId = "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794";
        ListAutomationRunsParams params = ListAutomationRunsParams.builder()
                .status(RunStatus.RUNNING, RunStatus.COMPLETED)
                .limit(10)
                .build();

        ListAutomationRunsResponseSuccess expectedResponse = AutomationsUtil.listAutomationRunsResponse();

        when(automations.listRuns(automationId, params)).thenReturn(expectedResponse);

        ListAutomationRunsResponseSuccess response = automations.listRuns(automationId, params);

        assertNotNull(response);
        assertEquals(2, params.getStatus().size());
        assertEquals("?status=running,completed&limit=10", params.toQueryString());
        verify(automations, times(1)).listRuns(automationId, params);
    }

    @Test
    public void testGetAutomationRun_Success() throws ResendException {
        GetAutomationRunOptions options = GetAutomationRunOptions.builder()
                .automationId("49a3999c-0ce1-4ea6-ab68-afcd6dc2e794")
                .runId("run_1")
                .build();

        AutomationRun expectedResponse = AutomationsUtil.getAutomationRunResponse();

        when(automations.getRun(options)).thenReturn(expectedResponse);

        AutomationRun response = automations.getRun(options);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        verify(automations, times(1)).getRun(options);
    }
}
