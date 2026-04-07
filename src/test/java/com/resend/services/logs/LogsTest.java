package com.resend.services.logs;

import com.resend.core.exception.ResendException;
import com.resend.core.net.ListParams;
import com.resend.services.logs.model.GetLogResponseSuccess;
import com.resend.services.logs.model.ListLogsResponseSuccess;
import com.resend.services.util.LogsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class LogsTest {

    @Mock
    private Logs logs;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        logs = mock(Logs.class);
    }

    @Test
    public void testGetLog_Success() throws ResendException {
        String logId = "log_123";
        GetLogResponseSuccess expected = LogsUtil.getLogResponseSuccess();

        when(logs.get(logId)).thenReturn(expected);

        GetLogResponseSuccess res = logs.get(logId);

        assertNotNull(res);
        assertEquals(expected.getId(), res.getId());
        assertEquals(expected.getEndpoint(), res.getEndpoint());
        assertEquals(expected.getMethod(), res.getMethod());
        assertEquals(expected.getResponseStatus(), res.getResponseStatus());
        assertEquals(expected.getObject(), res.getObject());
        verify(logs, times(1)).get(logId);
    }

    @Test
    public void testListLogs_Success() throws ResendException {
        ListLogsResponseSuccess expected = LogsUtil.createLogsListResponse();

        when(logs.list()).thenReturn(expected);

        ListLogsResponseSuccess res = logs.list();

        assertNotNull(res);
        assertEquals(expected.getData().size(), res.getData().size());
        assertEquals(expected.getObject(), res.getObject());
        verify(logs, times(1)).list();
    }

    @Test
    public void testListLogsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(3).build();
        ListLogsResponseSuccess expected = LogsUtil.createLogsListResponse();

        when(logs.list(params)).thenReturn(expected);

        ListLogsResponseSuccess res = logs.list(params);

        assertNotNull(res);
        assertEquals(params.getLimit(), res.getData().size());
        assertEquals(expected.getObject(), res.getObject());
        verify(logs, times(1)).list(params);
    }

    @Test
    public void testListLogsWithAfterCursor_Success() throws ResendException {
        ListParams params = ListParams.builder().after("log_50").build();
        ListLogsResponseSuccess expected = LogsUtil.createLogsListResponse();

        when(logs.list(params)).thenReturn(expected);

        ListLogsResponseSuccess res = logs.list(params);

        assertNotNull(res);
        assertEquals(expected.getData().size(), res.getData().size());
        verify(logs, times(1)).list(params);
    }

    @Test
    public void testListLogsWithBeforeCursor_Success() throws ResendException {
        ListParams params = ListParams.builder().before("log_100").build();
        ListLogsResponseSuccess expected = LogsUtil.createLogsListResponse();

        when(logs.list(params)).thenReturn(expected);

        ListLogsResponseSuccess res = logs.list(params);

        assertNotNull(res);
        assertEquals(expected.getData().size(), res.getData().size());
        verify(logs, times(1)).list(params);
    }
}
