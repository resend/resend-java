package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.emails.model.Attachment;
import com.resend.services.inboxes.model.*;
import com.resend.services.util.InboxesUtil;
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
public class InboxDraftsTest {

    private static final String DRAFTS_PATH = "/inboxes/" + INBOX_ID + "/drafts";
    private static final String DRAFT_JSON = "{\"object\":\"inbox_draft\",\"id\":\"" + DRAFT_ID + "\",\"type\":\"reply\"," +
            "\"to\":[\"ada@example.org\"],\"cc\":[],\"bcc\":[],\"subject\":\"Re: Refund for order 1041\"," +
            "\"html\":null,\"text\":\"Refund issued for order 1041.\",\"thread_id\":\"" + THREAD_ID + "\"," +
            "\"reply_to_email_id\":\"" + EMAIL_ID + "\",\"email_id\":null," +
            "\"created_at\":\"2026-08-05T14:12:04.110Z\",\"updated_at\":\"2026-08-05T14:12:04.110Z\"," +
            "\"attachments\":[{\"id\":\"5c1e9d2a-7b3f-4e8a-9c6d-2f0a1b3c4d5e\",\"filename\":\"receipt.pdf\"," +
            "\"content_type\":\"application/pdf\",\"size\":20841}]}";

    @Mock
    private IHttpClient httpClient;

    private InboxDrafts drafts;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        drafts = new Inboxes("test-api-key", httpClient).drafts();
    }

    @Test
    public void testListDrafts_Success() throws ResendException {
        String json = "{\"object\":\"list\",\"has_more\":false,\"data\":[{\"id\":\"" + DRAFT_ID + "\",\"type\":\"standalone\"," +
                "\"to\":null,\"cc\":[],\"bcc\":[],\"subject\":null,\"snippet\":\"Refund issued\",\"thread_id\":null," +
                "\"reply_to_email_id\":null,\"updated_at\":\"2026-08-05T14:12:04.110Z\",\"attachments\":[]}]}";
        when(httpClient.perform(eq(DRAFTS_PATH + "?limit=1"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, json, true));

        ListInboxDraftsResponseSuccess response = drafts.list(INBOX_ID, ListParams.builder().limit(1).build());

        InboxDraftListItem item = response.getData().get(0);
        assertEquals(DRAFT_ID, item.getId());
        assertEquals("standalone", item.getType());
        assertNull(item.getTo());
        assertEquals("Refund issued", item.getSnippet());
        assertNull(item.getThreadId());
        assertTrue(item.getAttachments().isEmpty());
    }

    @Test
    public void testCreateReplyDraft_Success() throws ResendException {
        String payload = "{\"text\":\"Refund issued for order 1041.\",\"thread_id\":\"" + THREAD_ID + "\"," +
                "\"reply_to_email_id\":\"" + EMAIL_ID + "\"}";
        when(httpClient.perform(eq(DRAFTS_PATH), anyString(), eq(HttpMethod.POST), eq(payload), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(201, "{\"object\":\"inbox_draft\",\"id\":\"" + DRAFT_ID + "\"}", true));

        CreateInboxDraftResponseSuccess response = drafts.create(INBOX_ID, InboxesUtil.createReplyDraftRequest());

        assertEquals("inbox_draft", response.getObject());
        assertEquals(DRAFT_ID, response.getId());
    }

    @Test
    public void testCreateDraft_EmptyToIsSent() throws ResendException {
        CreateInboxDraftOptions options = CreateInboxDraftOptions.builder()
                .to(Collections.<String>emptyList())
                .subject("Hello")
                .build();
        when(httpClient.perform(eq(DRAFTS_PATH), anyString(), eq(HttpMethod.POST), eq("{\"to\":[],\"subject\":\"Hello\"}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(201, "{\"object\":\"inbox_draft\",\"id\":\"" + DRAFT_ID + "\"}", true));

        assertEquals(DRAFT_ID, drafts.create(INBOX_ID, options).getId());
    }

    @Test
    public void testCreateDraft_WithAttachment() throws ResendException {
        CreateInboxDraftOptions options = CreateInboxDraftOptions.builder()
                .addAttachment(Attachment.builder().fileName("notes.txt").content("bm90ZXM=").contentType("text/plain").build())
                .build();
        String payload = "{\"attachments\":[{\"filename\":\"notes.txt\",\"content\":\"bm90ZXM=\",\"content_type\":\"text/plain\"}]}";
        when(httpClient.perform(eq(DRAFTS_PATH), anyString(), eq(HttpMethod.POST), eq(payload), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(201, "{\"object\":\"inbox_draft\",\"id\":\"" + DRAFT_ID + "\"}", true));

        assertEquals(DRAFT_ID, drafts.create(INBOX_ID, options).getId());
    }

    @Test
    public void testUpdateDraft_EmptyAttachmentsRemovesThem() throws ResendException {
        UpdateInboxDraftOptions options = UpdateInboxDraftOptions.builder().attachments().build();
        when(httpClient.perform(eq(DRAFTS_PATH + "/" + DRAFT_ID), anyString(), eq(HttpMethod.PATCH),
                eq("{\"attachments\":[]}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, DRAFT_JSON, true));

        assertNotNull(drafts.update(INBOX_ID, DRAFT_ID, options));
        assertTrue(options.getAttachments().isEmpty());
    }

    @Test
    public void testDraftAttachmentBuilder_Validation() {
        Attachment withPath = Attachment.builder().fileName("a.txt").path("https://example.com/a.txt").contentType("text/plain").build();
        Attachment noType = Attachment.builder().fileName("a.txt").content("YQ==").build();
        assertThrows(IllegalArgumentException.class, () -> CreateInboxDraftOptions.builder().attachments(withPath).build());
        assertThrows(IllegalArgumentException.class, () -> CreateInboxDraftOptions.builder().attachments(noType).build());
        assertThrows(IllegalArgumentException.class, () -> UpdateInboxDraftOptions.builder().attachments(noType).build());
        Attachment blankName = Attachment.builder().fileName(" ").content("YQ==").contentType("text/plain").build();
        assertThrows(IllegalArgumentException.class, () -> CreateInboxDraftOptions.builder().attachments(blankName).build());
    }

    @Test
    public void testDraftAttachmentBuilder_AllowsMoreThanTenFiles() {
        CreateInboxDraftOptions.Builder builder = CreateInboxDraftOptions.builder();
        for (int i = 0; i < 11; i++) {
            builder.addAttachment(Attachment.builder().fileName("f" + i + ".txt").content("YQ==").contentType("text/plain").build());
        }
        assertEquals(11, builder.build().getAttachments().size());
    }

    @Test
    public void testCreateDraftBuilder_Validation() {
        assertThrows(IllegalArgumentException.class, () -> CreateInboxDraftOptions.builder().to(java.util.Collections.<String>emptyList()).build());
        assertThrows(IllegalArgumentException.class, () -> CreateInboxDraftOptions.builder().text("Hi").threadId(THREAD_ID).build());
    }

    @Test
    public void testUpdateDraftBuilder_RequiresAField() {
        assertThrows(IllegalArgumentException.class, () -> UpdateInboxDraftOptions.builder().build());
    }

    @Test
    public void testGetDraft_Success() throws ResendException {
        when(httpClient.perform(eq(DRAFTS_PATH + "/" + DRAFT_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, DRAFT_JSON, true));

        GetInboxDraftResponseSuccess response = drafts.get(INBOX_ID, DRAFT_ID);

        assertEquals("reply", response.getType());
        assertEquals(Collections.singletonList("ada@example.org"), response.getTo());
        assertNull(response.getHtml());
        assertEquals(THREAD_ID, response.getThreadId());
        assertEquals(EMAIL_ID, response.getReplyToEmailId());
        assertNull(response.getEmailId());
        InboxDraftAttachment attachment = response.getAttachments().get(0);
        assertEquals("5c1e9d2a-7b3f-4e8a-9c6d-2f0a1b3c4d5e", attachment.getId());
        assertEquals("receipt.pdf", attachment.getFilename());
        assertEquals("application/pdf", attachment.getContentType());
        assertEquals(20841, (int) attachment.getSize());
    }

    @Test
    public void testUpdateDraft_SendsNullToClear() throws ResendException {
        UpdateInboxDraftOptions options = UpdateInboxDraftOptions.builder()
                .subject(null)
                .cc((java.util.List<String>) null)
                .text("Updated body.")
                .build();
        when(httpClient.perform(eq(DRAFTS_PATH + "/" + DRAFT_ID), anyString(), eq(HttpMethod.PATCH),
                eq("{\"subject\":null,\"cc\":null,\"text\":\"Updated body.\"}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, DRAFT_JSON, true));

        UpdateInboxDraftResponseSuccess response = drafts.update(INBOX_ID, DRAFT_ID, options);

        assertEquals(DRAFT_ID, response.getId());
    }

    @Test
    public void testUpdateDraft_OmitsUnsetFields() throws ResendException {
        UpdateInboxDraftOptions options = UpdateInboxDraftOptions.builder().to("ada@example.org").build();
        when(httpClient.perform(eq(DRAFTS_PATH + "/" + DRAFT_ID), anyString(), eq(HttpMethod.PATCH),
                eq("{\"to\":[\"ada@example.org\"]}"), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, DRAFT_JSON, true));

        assertNotNull(drafts.update(INBOX_ID, DRAFT_ID, options));
        assertEquals(Collections.singletonList("ada@example.org"), options.getTo());
    }

    @Test
    public void testRemoveDraft_Success() throws ResendException {
        when(httpClient.perform(eq(DRAFTS_PATH + "/" + DRAFT_ID), anyString(), eq(HttpMethod.DELETE), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"inbox_draft\",\"id\":\"" + DRAFT_ID + "\",\"deleted\":true}", true));

        RemoveInboxDraftResponseSuccess response = drafts.remove(INBOX_ID, DRAFT_ID);

        assertTrue(response.getDeleted());
    }

    @Test
    public void testSendDraft_Success() throws ResendException {
        when(httpClient.perform(eq(DRAFTS_PATH + "/" + DRAFT_ID + "/send"), anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"id\":\"" + SENT_EMAIL_ID + "\"}", true));

        SendInboxDraftResponseSuccess response = drafts.send(INBOX_ID, DRAFT_ID);

        assertEquals(SENT_EMAIL_ID, response.getId());
    }

    @Test
    public void testSendDraft_AlreadySent_ThrowsResendException() throws ResendException {
        when(httpClient.perform(eq(DRAFTS_PATH + "/" + DRAFT_ID + "/send"), anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(409, "{\"name\":\"conflict\",\"message\":\"Draft already sent\"}", false));

        ResendException ex = assertThrows(ResendException.class, () -> drafts.send(INBOX_ID, DRAFT_ID));
        assertEquals(409, (int) ex.getStatusCode());
    }
}
