package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.services.inboxes.model.*;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static com.resend.services.util.InboxesUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class InboxThreadsTest {

    private static final String THREADS_PATH = "/inboxes/" + INBOX_ID + "/threads";
    private static final String THREAD_JSON = "{\"id\":\"" + THREAD_ID + "\",\"subject\":\"Refund for order 1041\"," +
            "\"from\":\"ada@example.org\",\"to\":[\"support@example.com\"],\"cc\":[],\"bcc\":[]," +
            "\"labels\":[{\"id\":\"" + LABEL_ID + "\",\"name\":\"Urgent\",\"color\":\"#E93D82\"}]," +
            "\"email_count\":2,\"has_attachment\":true,\"has_draft\":false,\"read\":false," +
            "\"received_at\":\"2026-08-05T14:03:11.229Z\",\"folder\":\"inbox\"";
    private static final String SUMMARY_JSON = "{\"object\":\"inbox_thread\",\"id\":\"" + THREAD_ID + "\"," +
            "\"subject\":\"Refund for order 1041\",\"folder\":\"archive\"," +
            "\"labels\":[{\"id\":\"" + LABEL_ID + "\",\"name\":\"Urgent\",\"color\":\"#E93D82\"}],\"read\":true}";

    @Mock
    private IHttpClient httpClient;

    private InboxThreads threads;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        threads = new Inboxes("test-api-key", httpClient).threads();
    }

    @Test
    public void testListThreads_WithFilters() throws ResendException {
        ListInboxThreadsParams params = ListInboxThreadsParams.builder()
                .folders(InboxFolder.INBOX, InboxFolder.SENT)
                .labels(LABEL_ID, OTHER_LABEL_ID)
                .read(false)
                .limit(5)
                .after(THREAD_ID)
                .build();
        String path = THREADS_PATH + "?limit=5&after=" + THREAD_ID + "&folders=inbox%2Csent&labels=" + LABEL_ID + "%2C" + OTHER_LABEL_ID + "&read=false";
        when(httpClient.perform(eq(path), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"list\",\"has_more\":true,\"data\":[" + THREAD_JSON + "}]}", true));

        ListInboxThreadsResponseSuccess response = threads.list(INBOX_ID, params);

        assertTrue(response.hasMore());
        InboxThread thread = response.getData().get(0);
        assertEquals(THREAD_ID, thread.getId());
        assertEquals("ada@example.org", thread.getFrom());
        assertEquals(Collections.singletonList("support@example.com"), thread.getTo());
        assertEquals("#E93D82", thread.getLabels().get(0).getColor());
        assertEquals(2, (int) thread.getEmailCount());
        assertTrue(thread.getHasAttachment());
        assertFalse(thread.getHasDraft());
        assertFalse(thread.getRead());
        assertEquals("inbox", thread.getFolder());
    }

    @Test
    public void testListThreads_NoParams() throws ResendException {
        when(httpClient.perform(eq(THREADS_PATH), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"list\",\"has_more\":false,\"data\":[]}", true));

        assertFalse(threads.list(INBOX_ID).hasMore());
    }

    @Test
    public void testSearchThreads_Success() throws ResendException {
        SearchInboxThreadsParams params = SearchInboxThreadsParams.builder()
                .query("invoice -draft")
                .from("isabella@example.com", "carolina@example.com")
                .hasAttachment(true)
                .startDate("2026-09-01")
                .endDate("2026-09-30")
                .folders(InboxFolder.INBOX)
                .limit(10)
                .build();
        String path = THREADS_PATH + "/search?limit=10&query=invoice+-draft&from=isabella%40example.com%2Ccarolina%40example.com" +
                "&has_attachment=true&start_date=2026-09-01&end_date=2026-09-30&folders=inbox";
        String json = "{\"object\":\"list\",\"has_more\":false,\"data\":[" + THREAD_JSON + "," +
                "\"matched_email_id\":\"e8a3f1c6-2b9d-4e5a-a7c4-9d1b6f3e2a80\"," +
                "\"highlights\":{\"subject\":[\"September **invoice**\"],\"attachments\":[\"**invoice**-2026-09.pdf\"]}}]}";
        when(httpClient.perform(eq(path), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, json, true));

        SearchInboxThreadsResponseSuccess response = threads.search(INBOX_ID, params);

        InboxThreadSearchResult result = response.getData().get(0);
        assertEquals(THREAD_ID, result.getId());
        assertEquals(2, (int) result.getEmailCount());
        assertEquals("e8a3f1c6-2b9d-4e5a-a7c4-9d1b6f3e2a80", result.getMatchedEmailId());
        assertEquals(Collections.singletonList("September **invoice**"), result.getHighlights().get("subject"));
        assertFalse(result.getHighlights().containsKey("body"));
    }

    @Test
    public void testGetThread_Success() throws ResendException {
        when(httpClient.perform(eq(THREADS_PATH + "/" + THREAD_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, SUMMARY_JSON, true));

        GetInboxThreadResponseSuccess response = threads.get(INBOX_ID, THREAD_ID);

        assertEquals("inbox_thread", response.getObject());
        assertEquals("archive", response.getFolder());
        assertEquals(LABEL_ID, response.getLabels().get(0).getId());
        assertTrue(response.getRead());
    }

    @Test
    public void testUpdateThread_ReadAndFolder() throws ResendException {
        UpdateInboxThreadOptions options = UpdateInboxThreadOptions.builder()
                .read(true)
                .folder(MoveInboxThreadFolder.ARCHIVE)
                .build();
        when(httpClient.perform(eq(THREADS_PATH + "/" + THREAD_ID), anyString(), eq(HttpMethod.PATCH),
                eq("{\"read\":true,\"folder\":\"archive\"}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, SUMMARY_JSON, true));

        UpdateInboxThreadResponseSuccess response = threads.update(INBOX_ID, THREAD_ID, options);

        assertEquals(THREAD_ID, response.getId());
        assertEquals("archive", response.getFolder());
    }

    @Test
    public void testRemoveThread_Success() throws ResendException {
        when(httpClient.perform(eq(THREADS_PATH + "/" + THREAD_ID), anyString(), eq(HttpMethod.DELETE), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"inbox_thread\",\"id\":\"" + THREAD_ID + "\",\"deleted\":false}", true));

        RemoveInboxThreadResponseSuccess response = threads.remove(INBOX_ID, THREAD_ID);

        assertEquals(THREAD_ID, response.getId());
        assertFalse(response.getDeleted());
    }

    @Test
    public void testUpdateThreadBuilder_Validation() {
        assertThrows(IllegalArgumentException.class, () -> UpdateInboxThreadOptions.builder().build());
    }

    @Test
    public void testParamsBuilders_RejectBothCursors() {
        assertThrows(IllegalArgumentException.class, () -> ListInboxThreadsParams.builder().after(THREAD_ID).before(THREAD_ID).build());
        assertThrows(IllegalArgumentException.class, () -> SearchInboxThreadsParams.builder().after(THREAD_ID).before(THREAD_ID).build());
    }

    @Test
    public void testFolder_UnknownValue_Throws() {
        assertEquals(InboxFolder.TRASH, InboxFolder.fromValue("trash"));
        assertThrows(IllegalArgumentException.class, () -> MoveInboxThreadFolder.fromValue("sent"));
    }

    @Test
    public void testAddLabels() throws ResendException {
        AddInboxThreadLabelsOptions options = AddInboxThreadLabelsOptions.builder().labels(LABEL_ID, OTHER_LABEL_ID).build();
        when(httpClient.perform(eq(THREADS_PATH + "/" + THREAD_ID + "/labels"), anyString(), eq(HttpMethod.POST),
                eq("{\"labels\":[\"" + LABEL_ID + "\",\"" + OTHER_LABEL_ID + "\"]}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"list\",\"data\":[" +
                        "{\"id\":\"" + LABEL_ID + "\",\"name\":\"Urgent\",\"color\":\"#E93D82\"}," +
                        "{\"id\":\"" + OTHER_LABEL_ID + "\",\"name\":\"Billing\",\"color\":\"#00A2C7\"}]}", true));

        AddInboxThreadLabelsResponseSuccess response = threads.addLabels(INBOX_ID, THREAD_ID, options);

        assertEquals("list", response.getObject());
        assertEquals(2, response.getData().size());
        assertEquals("Billing", response.getData().get(1).getName());
    }

    @Test
    public void testSetLabels_EmptyListClearsLabels() throws ResendException {
        SetInboxThreadLabelsOptions options = SetInboxThreadLabelsOptions.builder().labels(Collections.<String>emptyList()).build();
        when(httpClient.perform(eq(THREADS_PATH + "/" + THREAD_ID + "/labels"), anyString(), eq(HttpMethod.PUT),
                eq("{\"labels\":[]}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"list\",\"data\":[]}", true));

        SetInboxThreadLabelsResponseSuccess response = threads.setLabels(INBOX_ID, THREAD_ID, options);

        assertTrue(response.getData().isEmpty());
    }

    @Test
    public void testRemoveLabel() throws ResendException {
        when(httpClient.perform(eq(THREADS_PATH + "/" + THREAD_ID + "/labels/" + LABEL_ID), anyString(), eq(HttpMethod.DELETE),
                isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"list\",\"data\":[" +
                        "{\"id\":\"" + OTHER_LABEL_ID + "\",\"name\":\"Billing\",\"color\":\"#00A2C7\"}]}", true));

        RemoveInboxThreadLabelResponseSuccess response = threads.removeLabel(INBOX_ID, THREAD_ID, LABEL_ID);

        assertEquals(1, response.getData().size());
        assertEquals(OTHER_LABEL_ID, response.getData().get(0).getId());
    }

    @Test
    public void testThreadLabelsBuilders_Validate() {
        assertThrows(IllegalArgumentException.class, () -> AddInboxThreadLabelsOptions.builder().build());
        assertThrows(IllegalArgumentException.class, () -> AddInboxThreadLabelsOptions.builder().labels(Collections.<String>emptyList()).build());
        assertThrows(IllegalArgumentException.class, () -> SetInboxThreadLabelsOptions.builder().build());
    }
}
