package com.resend.services.broadcasts;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.broadcasts.model.*;
import com.resend.services.util.BroadcastsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class BroadcastsTest {

    private static final String BROADCAST_ID = "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794";
    private static final String AUDIENCE_ID = "78261eea-8f8b-4381-83c6-79fa7120f1cf";
    private static final String GET_BROADCAST_ID = "559ac32e-9ef5-46fb-82a1-b76b840c0f7b";

    private static final String CREATE_RESPONSE_JSON =
            "{\"id\":\"" + BROADCAST_ID + "\"}";

    private static final String GET_RESPONSE_JSON =
            "{\"id\":\"" + GET_BROADCAST_ID + "\"," +
            "\"audience_id\":\"" + AUDIENCE_ID + "\"," +
            "\"status\":\"draft\"," +
            "\"created_at\":\"2024-12-01T19:32:22.980Z\"," +
            "\"object\":\"broadcast\"," +
            "\"name\":\"Announcements\"," +
            "\"from\":\"Acme <onboarding@resend.dev>\"," +
            "\"html\":\"<p>Hello World</p>\"," +
            "\"subject\":\"Check out our latest announcements\"," +
            "\"text\":\"The plain text of the broadcast\"}";

    private static final String REMOVE_RESPONSE_JSON =
            "{\"id\":\"" + GET_BROADCAST_ID + "\",\"object\":\"object\",\"deleted\":true}";

    private static final String LIST_RESPONSE_JSON =
            "{\"object\":\"list\",\"has_more\":true,\"data\":[" +
            "{\"id\":\"1\",\"audience_id\":\"" + AUDIENCE_ID + "\",\"status\":\"draft\",\"created_at\":\"2024-12-01T19:32:22.980Z\"}," +
            "{\"id\":\"2\",\"audience_id\":\"" + AUDIENCE_ID + "\",\"status\":\"sent\",\"created_at\":\"2024-12-02T10:15:30.000Z\"}," +
            "{\"id\":\"3\",\"audience_id\":\"" + AUDIENCE_ID + "\",\"status\":\"queued\",\"created_at\":\"2024-12-03T08:45:00.000Z\"}" +
            "]}";

    private static final String SEND_RESPONSE_JSON =
            "{\"id\":\"" + BROADCAST_ID + "\"}";

    private static final String UPDATE_RESPONSE_JSON =
            "{\"id\":\"" + BROADCAST_ID + "\"}";

    @Mock
    private IHttpClient httpClient;

    private Broadcasts broadcasts;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        broadcasts = new Broadcasts("test-api-key", httpClient);
    }

    @Test
    public void testCreateBroadcast_Success() throws ResendException {
        CreateBroadcastOptions createOptions = BroadcastsUtil.createBroadcastRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/broadcasts"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateBroadcastResponseSuccess response = broadcasts.create(createOptions);

        assertNotNull(response);
        assertEquals(BROADCAST_ID, response.getId());
    }

    @Test
    public void testCreateBroadcast_ApiError_ThrowsResendException() throws ResendException {
        CreateBroadcastOptions createOptions = BroadcastsUtil.createBroadcastRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid audience\"}", false);

        when(httpClient.perform(eq("/broadcasts"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> broadcasts.create(createOptions));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testGetBroadcast_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/broadcasts/" + GET_BROADCAST_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetBroadcastResponseSuccess response = broadcasts.get(GET_BROADCAST_ID);

        assertNotNull(response);
        assertEquals(GET_BROADCAST_ID, response.getId());
        assertEquals("draft", response.getStatus());
        assertEquals("Announcements", response.getName());
    }

    @Test
    public void testSendBroadcast_Success() throws ResendException {
        SendBroadcastOptions sendOptions = BroadcastsUtil.sendBroadcastRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, SEND_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/broadcasts/" + BROADCAST_ID + "/send"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        SendBroadcastResponseSuccess response = broadcasts.send(sendOptions, BROADCAST_ID);

        assertNotNull(response);
        assertEquals(BROADCAST_ID, response.getId());
    }

    @Test
    public void testDeleteBroadcast_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/broadcasts/" + GET_BROADCAST_ID), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveBroadcastResponseSuccess response = broadcasts.remove(GET_BROADCAST_ID);

        assertNotNull(response);
        assertEquals(GET_BROADCAST_ID, response.getId());
        assertTrue(response.isDeleted());
    }

    @Test
    public void testListBroadcasts_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/broadcasts"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListBroadcastsResponseSuccess response = broadcasts.list();

        assertNotNull(response);
        assertEquals(3, response.getData().size());
        assertEquals("list", response.getObject());
    }

    @Test
    public void testListBroadcastsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(3).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(startsWith("/broadcasts?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListBroadcastsResponseSuccess response = broadcasts.list(params);

        assertNotNull(response);
        assertEquals("list", response.getObject());
    }

    @Test
    public void testUpdateBroadcast_Success() throws ResendException {
        UpdateBroadcastOptions updateOptions = BroadcastsUtil.updateBroadcastRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/broadcasts/" + updateOptions.getId()), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateBroadcastResponseSuccess response = broadcasts.update(updateOptions);

        assertNotNull(response);
        assertEquals(BROADCAST_ID, response.getId());
    }

    @Test
    public void testCreateAndSendBroadcast_Success() throws ResendException {
        CreateBroadcastOptions createOptions = BroadcastsUtil.createAndSendBroadcastRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/broadcasts"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateBroadcastResponseSuccess response = broadcasts.create(createOptions);

        assertNotNull(response);
        assertEquals(BROADCAST_ID, response.getId());
        assertTrue(createOptions.getSend());
    }

    @Test
    public void testCreateAndScheduleBroadcast_Success() throws ResendException {
        CreateBroadcastOptions createOptions = BroadcastsUtil.createAndScheduleBroadcastRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/broadcasts"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateBroadcastResponseSuccess response = broadcasts.create(createOptions);

        assertNotNull(response);
        assertEquals(BROADCAST_ID, response.getId());
        assertTrue(createOptions.getSend());
        assertNotNull(createOptions.getScheduledAt());
    }
}
