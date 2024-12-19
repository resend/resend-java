package com.resend.services.broadcasts;

import com.resend.core.exception.ResendException;
import com.resend.services.broadcasts.model.*;
import com.resend.services.util.BroadcastsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BroadcastsTest {

    @Mock
    private Broadcasts broadcasts;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        broadcasts = mock(Broadcasts.class);
    }

    @Test
    public void testCreateBroadcast_Success() throws ResendException {
        CreateBroadcastResponseSuccess expectedResponse = BroadcastsUtil.createBroadcastResponse();
        CreateBroadcastOptions createOptions = BroadcastsUtil.createBroadcastRequest();

        when(broadcasts.create(createOptions)).thenReturn(expectedResponse);

        CreateBroadcastResponseSuccess response = broadcasts.create(createOptions);

        assertEquals(expectedResponse, response);
        verify(broadcasts, times(1)).create(createOptions);
    }

    @Test
    public void testGetBroadcast_Success() throws ResendException {
        String broadcastId = "12345";
        GetBroadcastResponseSuccess expectedResponse = BroadcastsUtil.getBroadcastResponse();

        when(broadcasts.get(broadcastId)).thenReturn(expectedResponse);

        GetBroadcastResponseSuccess response = broadcasts.get(broadcastId);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        verify(broadcasts, times(1)).get(broadcastId);
    }

    @Test
    public void testSendBroadcast_Success() throws ResendException {
        String broadcastId = "12345";
        SendBroadcastOptions sendOptions = BroadcastsUtil.sendBroadcastRequest();
        SendBroadcastResponseSuccess expectedResponse = BroadcastsUtil.sendBroadcastResponse();

        when(broadcasts.send(sendOptions, broadcastId)).thenReturn(expectedResponse);

        SendBroadcastResponseSuccess response = broadcasts.send(sendOptions, broadcastId);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        verify(broadcasts, times(1)).send(sendOptions, broadcastId);
    }

    @Test
    public void testDeleteBroadcast_Success() throws ResendException {
        String broadcastId = "12345";
        RemoveBroadcastResponseSuccess expectedResponse = BroadcastsUtil.removeBroadcastResponseSuccess();

        when(broadcasts.remove(broadcastId)).thenReturn(expectedResponse);

        RemoveBroadcastResponseSuccess response = broadcasts.remove(broadcastId);

        assertEquals(expectedResponse, response);
        verify(broadcasts, times(1)).remove(broadcastId);
    }

    @Test
    public void testListBroadcasts_Success() throws ResendException {
        ListBroadcastsResponseSuccess expectedResponse = BroadcastsUtil.createBroadcastsListResponse();

        when(broadcasts.list()).thenReturn(expectedResponse);

        ListBroadcastsResponseSuccess response = broadcasts.list();

        assertNotNull(response);
        assertEquals(expectedResponse.getData().size(), response.getData().size());
        assertEquals(expectedResponse.getObject(), response.getObject());
        verify(broadcasts, times(1)).list();
    }
}

