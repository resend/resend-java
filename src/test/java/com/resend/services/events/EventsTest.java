package com.resend.services.events;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.events.model.*;
import com.resend.services.util.EventsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class EventsTest {

    private static final String EVENT_ID = "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794";

    private static final String CREATE_EVENT_JSON =
            "{\"object\":\"event\",\"id\":\"" + EVENT_ID + "\"}";

    private static final String GET_EVENT_JSON =
            "{\"object\":\"event\",\"id\":\"" + EVENT_ID + "\"," +
            "\"name\":\"user.signup\"," +
            "\"schema\":{\"user_id\":\"string\",\"plan\":\"string\"}," +
            "\"created_at\":\"2024-12-01T10:00:00.000Z\",\"updated_at\":\"2024-12-02T10:00:00.000Z\"}";

    private static final String LIST_EVENTS_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"id\":\"1\",\"name\":\"user.signup\",\"created_at\":\"2024-12-01T10:00:00.000Z\"}," +
            "{\"id\":\"2\",\"name\":\"user.verified\",\"created_at\":\"2024-12-02T10:00:00.000Z\"}," +
            "{\"id\":\"3\",\"name\":\"order.created\",\"created_at\":\"2024-12-03T10:00:00.000Z\"}" +
            "]}";

    private static final String UPDATE_EVENT_JSON =
            "{\"object\":\"event\",\"id\":\"" + EVENT_ID + "\"}";

    private static final String REMOVE_EVENT_JSON =
            "{\"object\":\"event\",\"id\":\"" + EVENT_ID + "\",\"deleted\":true}";

    private static final String SEND_EVENT_JSON =
            "{\"object\":\"event\",\"event\":\"user.signup\"}";

    @Mock
    private IHttpClient httpClient;

    private Events events;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        events = new Events("test-api-key", httpClient);
    }

    @Test
    public void testCreateEvent_Success() throws ResendException {
        CreateEventOptions createOptions = EventsUtil.createEventRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_EVENT_JSON, true);

        when(httpClient.perform(eq("/events"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateEventResponseSuccess response = events.create(createOptions);

        assertNotNull(response);
        assertEquals(EVENT_ID, response.getId());
        assertEquals("event", response.getObject());
    }

    @Test
    public void testCreateEvent_ApiError_ThrowsResendException() throws ResendException {
        CreateEventOptions createOptions = EventsUtil.createEventRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid event\"}", false);

        when(httpClient.perform(eq("/events"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> events.create(createOptions));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testGetEvent_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_EVENT_JSON, true);

        when(httpClient.perform(eq("/events/" + EVENT_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        Event response = events.get(EVENT_ID);

        assertNotNull(response);
        assertEquals(EVENT_ID, response.getId());
        assertEquals("user.signup", response.getName());
    }

    @Test
    public void testGetEventByName_Success() throws ResendException {
        String eventName = "user.signup";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_EVENT_JSON, true);

        when(httpClient.perform(eq("/events/" + eventName), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        Event response = events.get(eventName);

        assertNotNull(response);
        assertEquals("user.signup", response.getName());
    }

    @Test
    public void testListEvents_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_EVENTS_JSON, true);

        when(httpClient.perform(eq("/events"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListEventsResponseSuccess response = events.list();

        assertNotNull(response);
        assertEquals(3, response.getData().size());
        assertEquals("list", response.getObject());
    }

    @Test
    public void testListEventsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(10).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_EVENTS_JSON, true);

        when(httpClient.perform(startsWith("/events?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListEventsResponseSuccess response = events.list(params);

        assertNotNull(response);
        assertEquals("list", response.getObject());
    }

    @Test
    public void testUpdateEvent_Success() throws ResendException {
        UpdateEventOptions updateOptions = EventsUtil.updateEventRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_EVENT_JSON, true);

        when(httpClient.perform(eq("/events/" + EVENT_ID), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateEventResponseSuccess response = events.update(updateOptions);

        assertNotNull(response);
        assertEquals(EVENT_ID, response.getId());
        assertEquals("event", response.getObject());
    }

    @Test
    public void testRemoveEvent_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_EVENT_JSON, true);

        when(httpClient.perform(eq("/events/" + EVENT_ID), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveEventResponseSuccess response = events.remove(EVENT_ID);

        assertNotNull(response);
        assertTrue(response.getDeleted());
        assertEquals(EVENT_ID, response.getId());
    }

    @Test
    public void testSendEvent_Success() throws ResendException {
        SendEventOptions sendOptions = EventsUtil.sendEventRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, SEND_EVENT_JSON, true);

        when(httpClient.perform(eq("/events/send"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        SendEventResponseSuccess response = events.send(sendOptions);

        assertNotNull(response);
        assertEquals("user.signup", response.getEvent());
    }

    @Test
    public void testSendEventWithContactId_Success() throws ResendException {
        SendEventOptions sendOptions = SendEventOptions.builder()
                .event("user.signup")
                .contactId("contact_123")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, SEND_EVENT_JSON, true);

        when(httpClient.perform(eq("/events/send"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        SendEventResponseSuccess response = events.send(sendOptions);

        assertNotNull(response);
        assertEquals("user.signup", response.getEvent());
    }
}
