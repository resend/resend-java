package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.inboxes.model.*;
import com.resend.services.util.InboxesUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.resend.services.util.InboxesUtil.INBOX_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class InboxesTest {

    private static final String INBOX_JSON = "{\"object\":\"inbox\",\"id\":\"" + INBOX_ID + "\"," +
            "\"name\":\"Customer Support\",\"email_address\":\"support@acme.com\"," +
            "\"domain_id\":\"d91cd9bd-1176-453e-8fc1-35364d380206\",\"receiving_address\":null," +
            "\"from_name\":\"Ada from Support\",\"unread\":3,\"drafts\":2," +
            "\"last_received\":\"2026-08-05T14:03:11.229Z\",\"created_at\":\"2026-08-01T09:12:44.512Z\"}";

    @Mock
    private IHttpClient httpClient;

    private Inboxes inboxes;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        inboxes = new Inboxes("test-api-key", httpClient);
    }

    @Test
    public void testCreateInbox_Success() throws ResendException {
        String payload = "{\"email_address\":\"support@acme.com\",\"name\":\"Customer Support\"," +
                "\"from_name\":\"Ada from Support\",\"forwarding\":true}";
        when(httpClient.perform(eq("/inboxes"), anyString(), eq(HttpMethod.POST), eq(payload), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(201, "{\"object\":\"inbox\",\"id\":\"" + INBOX_ID + "\"}", true));

        CreateInboxResponseSuccess response = inboxes.create(InboxesUtil.createInboxRequest());

        assertEquals("inbox", response.getObject());
        assertEquals(INBOX_ID, response.getId());
    }

    @Test
    public void testCreateInbox_PlanLimit_ThrowsResendException() throws ResendException {
        when(httpClient.perform(eq("/inboxes"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(403,
                        "{\"name\":\"validation_error\",\"message\":\"You have reached the inbox limit of your plan\"}", false));

        ResendException ex = assertThrows(ResendException.class, () -> inboxes.create(InboxesUtil.createInboxRequest()));
        assertEquals(403, (int) ex.getStatusCode());
    }

    @Test
    public void testListInboxes_Success() throws ResendException {
        String json = "{\"object\":\"list\",\"has_more\":false,\"data\":[{\"id\":\"" + INBOX_ID + "\"," +
                "\"name\":null,\"email_address\":\"support@acme.com\",\"from_name\":null,\"unread\":3," +
                "\"last_received\":null}]}";
        when(httpClient.perform(eq("/inboxes?limit=10&after=" + INBOX_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, json, true));

        ListInboxesResponseSuccess response = inboxes.list(ListParams.builder().limit(10).after(INBOX_ID).build());

        assertEquals("list", response.getObject());
        assertFalse(response.hasMore());
        assertEquals(1, response.getData().size());
        InboxListItem item = response.getData().get(0);
        assertEquals(INBOX_ID, item.getId());
        assertNull(item.getName());
        assertEquals("support@acme.com", item.getEmailAddress());
        assertEquals(3, (int) item.getUnread());
        assertNull(item.getLastReceived());
    }

    @Test
    public void testListInboxes_NoParams() throws ResendException {
        when(httpClient.perform(eq("/inboxes"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"list\",\"has_more\":false,\"data\":[]}", true));

        assertTrue(inboxes.list().getData().isEmpty());
    }

    @Test
    public void testGetInboxByEmail_Success() throws ResendException {
        when(httpClient.perform(eq("/inboxes/support%40acme.com"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, INBOX_JSON, true));

        GetInboxResponseSuccess response = inboxes.get("support@acme.com");

        assertEquals("inbox", response.getObject());
        assertEquals(INBOX_ID, response.getId());
        assertEquals("Customer Support", response.getName());
        assertEquals("d91cd9bd-1176-453e-8fc1-35364d380206", response.getDomainId());
        assertNull(response.getReceivingAddress());
        assertEquals("Ada from Support", response.getFromName());
        assertEquals(3, (int) response.getUnread());
        assertEquals(2, (int) response.getDrafts());
        assertEquals("2026-08-05T14:03:11.229Z", response.getLastReceived());
        assertEquals("2026-08-01T09:12:44.512Z", response.getCreatedAt());
    }

    @Test
    public void testGetInboxByEmail_EncodesPercentAndPlus() throws ResendException {
        when(httpClient.perform(eq("/inboxes/a%25b%2Bc%40acme.com"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, INBOX_JSON, true));

        assertEquals(INBOX_ID, inboxes.get("a%b+c@acme.com").getId());
    }

    @Test
    public void testBuilders_RequireFields() {
        assertThrows(IllegalArgumentException.class, () -> CreateInboxOptions.builder().name("Support").build());
        assertThrows(IllegalArgumentException.class, () -> UpdateInboxOptions.builder().build());
        assertEquals("", UpdateInboxOptions.builder().fromName("").build().getFromName());
    }

    @Test
    public void testUpdateInbox_Success() throws ResendException {
        when(httpClient.perform(eq("/inboxes/" + INBOX_ID), anyString(), eq(HttpMethod.PATCH), eq("{\"from_name\":\"Ada from Support\"}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"inbox\",\"id\":\"" + INBOX_ID + "\"}", true));

        UpdateInboxResponseSuccess response = inboxes.update(INBOX_ID, InboxesUtil.updateInboxRequest());

        assertEquals(INBOX_ID, response.getId());
    }

    @Test
    public void testRemoveInbox_Success() throws ResendException {
        when(httpClient.perform(eq("/inboxes/" + INBOX_ID), anyString(), eq(HttpMethod.DELETE), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"inbox\",\"id\":\"" + INBOX_ID + "\",\"deleted\":true}", true));

        RemoveInboxResponseSuccess response = inboxes.remove(INBOX_ID);

        assertEquals(INBOX_ID, response.getId());
        assertTrue(response.getDeleted());
    }

    @Test
    public void testRemoveInbox_NotFound_ThrowsResendException() throws ResendException {
        when(httpClient.perform(eq("/inboxes/" + INBOX_ID), anyString(), eq(HttpMethod.DELETE), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(404, "{\"name\":\"not_found\",\"message\":\"Inbox not found\"}", false));

        ResendException ex = assertThrows(ResendException.class, () -> inboxes.remove(INBOX_ID));
        assertEquals(404, (int) ex.getStatusCode());
    }
}
