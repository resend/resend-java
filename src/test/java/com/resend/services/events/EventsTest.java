package com.resend.services.events;

import com.resend.core.exception.ResendException;
import com.resend.core.net.ListParams;
import com.resend.services.events.model.*;
import com.resend.services.util.EventsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventsTest {

    @Mock
    private Events events;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        events = mock(Events.class);
    }

    @Test
    public void testCreateEvent_Success() throws ResendException {
        CreateEventResponseSuccess expectedResponse = EventsUtil.createEventResponse();
        CreateEventOptions createOptions = EventsUtil.createEventRequest();

        when(events.create(createOptions)).thenReturn(expectedResponse);

        CreateEventResponseSuccess response = events.create(createOptions);

        assertEquals(expectedResponse, response);
        verify(events, times(1)).create(createOptions);
    }

    @Test
    public void testGetEvent_Success() throws ResendException {
        String eventId = "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794";
        Event expectedResponse = EventsUtil.getEventResponse();

        when(events.get(eventId)).thenReturn(expectedResponse);

        Event response = events.get(eventId);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getName(), response.getName());
        verify(events, times(1)).get(eventId);
    }

    @Test
    public void testGetEventByName_Success() throws ResendException {
        String eventName = "user.signup";
        Event expectedResponse = EventsUtil.getEventResponse();

        when(events.get(eventName)).thenReturn(expectedResponse);

        Event response = events.get(eventName);

        assertNotNull(response);
        assertEquals(expectedResponse.getName(), response.getName());
        verify(events, times(1)).get(eventName);
    }

    @Test
    public void testListEvents_Success() throws ResendException {
        ListEventsResponseSuccess expectedResponse = EventsUtil.listEventsResponse();

        when(events.list()).thenReturn(expectedResponse);

        ListEventsResponseSuccess response = events.list();

        assertNotNull(response);
        assertEquals(expectedResponse.getData().size(), response.getData().size());
        assertEquals(expectedResponse.getObject(), response.getObject());
        verify(events, times(1)).list();
    }

    @Test
    public void testListEventsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder()
                .limit(10)
                .build();

        ListEventsResponseSuccess expectedResponse = EventsUtil.listEventsResponse();

        when(events.list(params)).thenReturn(expectedResponse);

        ListEventsResponseSuccess response = events.list(params);

        assertNotNull(response);
        assertEquals(expectedResponse.getObject(), response.getObject());
        verify(events, times(1)).list(params);
    }

    @Test
    public void testUpdateEvent_Success() throws ResendException {
        UpdateEventResponseSuccess expectedResponse = EventsUtil.updateEventResponse();
        UpdateEventOptions updateOptions = EventsUtil.updateEventRequest();

        when(events.update(updateOptions)).thenReturn(expectedResponse);

        UpdateEventResponseSuccess response = events.update(updateOptions);

        assertEquals(expectedResponse, response);
        verify(events, times(1)).update(updateOptions);
    }

    @Test
    public void testRemoveEvent_Success() throws ResendException {
        String eventId = "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794";
        RemoveEventResponseSuccess expectedResponse = EventsUtil.removeEventResponse();

        when(events.remove(eventId)).thenReturn(expectedResponse);

        RemoveEventResponseSuccess response = events.remove(eventId);

        assertEquals(expectedResponse, response);
        assertTrue(response.getDeleted());
        verify(events, times(1)).remove(eventId);
    }

    @Test
    public void testSendEvent_Success() throws ResendException {
        SendEventResponseSuccess expectedResponse = EventsUtil.sendEventResponse();
        SendEventOptions sendOptions = EventsUtil.sendEventRequest();

        when(events.send(sendOptions)).thenReturn(expectedResponse);

        SendEventResponseSuccess response = events.send(sendOptions);

        assertEquals(expectedResponse, response);
        assertEquals("user.signup", response.getEvent());
        verify(events, times(1)).send(sendOptions);
    }

    @Test
    public void testSendEventWithContactId_Success() throws ResendException {
        SendEventOptions sendOptions = SendEventOptions.builder()
                .event("user.signup")
                .contactId("contact_123")
                .build();

        SendEventResponseSuccess expectedResponse = EventsUtil.sendEventResponse();

        when(events.send(sendOptions)).thenReturn(expectedResponse);

        SendEventResponseSuccess response = events.send(sendOptions);

        assertEquals(expectedResponse, response);
        verify(events, times(1)).send(sendOptions);
    }
}
