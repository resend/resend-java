package com.resend.services.util;

import com.resend.services.logs.model.GetLogResponseSuccess;
import com.resend.services.logs.model.ListLogsResponseSuccess;
import com.resend.services.logs.model.LogEntry;

import java.util.ArrayList;
import java.util.List;

public class LogsUtil {

    public static GetLogResponseSuccess getLogResponseSuccess() {
        return new GetLogResponseSuccess(
                "log_123",
                "2024-01-01T00:00:00.000Z",
                "/emails",
                "POST",
                200,
                "resend-java/4.13.0",
                null,
                null,
                "log"
        );
    }

    public static ListLogsResponseSuccess createLogsListResponse() {
        List<LogEntry> entries = new ArrayList<>();
        entries.add(new LogEntry("log_1", "2024-01-01T00:00:00.000Z", "/emails", "POST", 200, "resend-java/4.13.0"));
        entries.add(new LogEntry("log_2", "2024-01-02T00:00:00.000Z", "/domains", "GET", 200, "resend-java/4.13.0"));
        entries.add(new LogEntry("log_3", "2024-01-03T00:00:00.000Z", "/contacts", "DELETE", 204, null));
        return new ListLogsResponseSuccess("list", false, entries);
    }
}
