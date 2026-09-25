package com.resend.services.usage;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.usage.model.UsageResponse;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class UsageTest {

    private static final String GET_USAGE_JSON =
            "{\"object\":\"usage\"," +
            "\"emails\":{" +
            "\"daily\":{\"used\":258,\"limit\":null,\"sent\":57,\"received\":201,\"resets_at\":\"2026-07-17T00:00:00.000Z\"}," +
            "\"monthly\":{\"used\":5422,\"limit\":10000,\"sent\":1000,\"received\":4442,\"resets_at\":\"2026-08-01T00:00:00.000Z\"}" +
            "}," +
            "\"contacts\":{\"used\":85000,\"limit\":150000}," +
            "\"segments\":{\"used\":2,\"limit\":3}," +
            "\"broadcasts\":{\"used\":100,\"limit\":null}," +
            "\"ai_credits\":{\"used\":0,\"limit\":500,\"next_increase_at\":\"2026-07-18T09:00:00.000Z\"}," +
            "\"automation_runs\":{\"used\":0,\"limit\":1000,\"resets_at\":\"2026-08-01T00:00:00.000Z\"}," +
            "\"domains\":{\"used\":1,\"limit\":1000}," +
            "\"rate_limit\":{\"limit\":10,\"duration\":\"1000ms\"}" +
            "}";

    @Mock
    private IHttpClient httpClient;

    private Usage usage;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usage = new Usage("test-api-key", httpClient);
    }

    @Test
    public void testGetUsage_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_USAGE_JSON, true);

        when(httpClient.perform(eq("/usage"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UsageResponse res = usage.get();

        assertNotNull(res);
        assertEquals("usage", res.getObject());

        assertEquals(258, (int) res.getEmails().getDaily().getUsed());
        assertNull(res.getEmails().getDaily().getLimit());
        assertEquals(57, (int) res.getEmails().getDaily().getSent());
        assertEquals(201, (int) res.getEmails().getDaily().getReceived());

        assertEquals(5422, (int) res.getEmails().getMonthly().getUsed());
        assertEquals(10000, (int) res.getEmails().getMonthly().getLimit());

        assertEquals(85000, (int) res.getContacts().getUsed());
        assertEquals(150000, (int) res.getContacts().getLimit());

        assertEquals(2, (int) res.getSegments().getUsed());
        assertEquals(3, (int) res.getSegments().getLimit());

        assertEquals(100, (int) res.getBroadcasts().getUsed());
        assertNull(res.getBroadcasts().getLimit());

        assertEquals(0, (int) res.getAiCredits().getUsed());
        assertEquals(500, (int) res.getAiCredits().getLimit());
        assertEquals("2026-07-18T09:00:00.000Z", res.getAiCredits().getNextIncreaseAt());

        assertEquals(0, (int) res.getAutomationRuns().getUsed());
        assertEquals(1000, (int) res.getAutomationRuns().getLimit());

        assertEquals(1, (int) res.getDomains().getUsed());
        assertEquals(1000, (int) res.getDomains().getLimit());

        assertEquals(10, (int) res.getRateLimit().getLimit());
        assertEquals("1000ms", res.getRateLimit().getDuration());
    }

    @Test
    public void testGetUsage_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(500,
                "{\"name\":\"internal_server_error\",\"message\":\"Server error\"}", false);

        when(httpClient.perform(eq("/usage"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> usage.get());
        assertEquals(500, (int) ex.getStatusCode());
    }
}
