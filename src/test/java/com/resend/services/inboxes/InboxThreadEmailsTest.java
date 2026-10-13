package com.resend.services.inboxes;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
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
public class InboxThreadEmailsTest {

    private static final String EMAILS_PATH = "/inboxes/" + INBOX_ID + "/threads/" + THREAD_ID + "/emails";
    private static final String EMAIL_JSON = "{\"id\":\"" + EMAIL_ID + "\",\"direction\":\"inbound\"," +
            "\"from\":\"Ada Lovelace <ada@example.org>\",\"to\":[\"support@acme.com\"],\"cc\":[],\"bcc\":[]," +
            "\"reply_to\":[\"replies@example.org\"],\"subject\":\"Refund for order 1041\"," +
            "\"message_id\":\"<1041@example.org>\",\"html\":\"<p>Could I get a refund?</p>\"," +
            "\"text\":\"Could I get a refund?\",\"attachments\":[{\"id\":\"9d4f2b81-6c3a-4e7d-8b12-0a5c6d7e8f90\"," +
            "\"filename\":\"receipt.pdf\",\"size\":20841}],\"read\":false,\"received_at\":\"2026-08-05T14:03:11.229Z\"}";

    @Mock
    private IHttpClient httpClient;

    private InboxThreadEmails emails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emails = new Inboxes("test-api-key", httpClient).threads().emails();
    }

    @Test
    public void testListThreadEmails_Success() throws ResendException {
        when(httpClient.perform(eq(EMAILS_PATH + "?limit=2&before=" + EMAIL_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"object\":\"list\",\"has_more\":false,\"data\":[" + EMAIL_JSON + "]}", true));

        ListInboxThreadEmailsResponseSuccess response = emails.list(INBOX_ID, THREAD_ID, ListParams.builder().limit(2).before(EMAIL_ID).build());

        assertFalse(response.hasMore());
        InboxThreadEmail email = response.getData().get(0);
        assertEquals("inbound", email.getDirection());
        assertEquals(Collections.singletonList("replies@example.org"), email.getReplyTo());
        assertEquals("<1041@example.org>", email.getMessageId());
        assertEquals("receipt.pdf", email.getAttachments().get(0).getFilename());
        assertEquals(20841, (int) email.getAttachments().get(0).getSize());
    }

    @Test
    public void testGetThreadEmail_Success() throws ResendException {
        when(httpClient.perform(eq(EMAILS_PATH + "/" + EMAIL_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, EMAIL_JSON, true));

        GetInboxThreadEmailResponseSuccess response = emails.get(INBOX_ID, THREAD_ID, EMAIL_ID);

        assertEquals(EMAIL_ID, response.getId());
        assertEquals("Ada Lovelace <ada@example.org>", response.getFrom());
        assertEquals("Could I get a refund?", response.getText());
        assertFalse(response.getRead());
        assertEquals("2026-08-05T14:03:11.229Z", response.getReceivedAt());
    }

    @Test
    public void testReply_Success() throws ResendException {
        String payload = "{\"cc\":[\"billing@acme.com\"],\"text\":\"Refund issued for order 1041.\",\"reply_all\":true," +
                "\"attachments\":[{\"filename\":\"receipt.txt\",\"content\":\"cmVjZWlwdA==\"}]}";
        when(httpClient.perform(eq(EMAILS_PATH + "/" + EMAIL_ID + "/reply"), anyString(), eq(HttpMethod.POST), eq(payload), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"id\":\"" + SENT_EMAIL_ID + "\"}", true));

        ReplyInboxThreadEmailResponseSuccess response = emails.reply(INBOX_ID, THREAD_ID, EMAIL_ID, InboxesUtil.replyRequest());

        assertEquals(SENT_EMAIL_ID, response.getId());
    }

    @Test
    public void testForward_Success() throws ResendException {
        String payload = "{\"to\":[\"colleague@example.org\"],\"text\":\"Flagging this refund request for you.\"}";
        when(httpClient.perform(eq(EMAILS_PATH + "/" + EMAIL_ID + "/forward"), anyString(), eq(HttpMethod.POST), eq(payload), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"id\":\"" + SENT_EMAIL_ID + "\"}", true));

        ForwardInboxThreadEmailResponseSuccess response = emails.forward(INBOX_ID, THREAD_ID, EMAIL_ID, InboxesUtil.forwardRequest());

        assertEquals(SENT_EMAIL_ID, response.getId());
    }

    @Test
    public void testReplyBuilder_RequiresBody() {
        assertThrows(IllegalArgumentException.class, () -> ReplyInboxThreadEmailOptions.builder().subject("Re: hi").text("  ").build());
    }

    @Test
    public void testForwardBuilder_RequiresTo() {
        assertThrows(IllegalArgumentException.class, () -> ForwardInboxThreadEmailOptions.builder().text("FYI").build());
    }

    @Test
    public void testReplyAndForward_WithIdempotencyKey() throws ResendException {
        RequestOptions options = RequestOptions.builder().setIdempotencyKey("reply-key-1").build();
        when(httpClient.perform(eq(EMAILS_PATH + "/" + EMAIL_ID + "/reply"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class), eq(options)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"id\":\"" + SENT_EMAIL_ID + "\"}", true));
        when(httpClient.perform(eq(EMAILS_PATH + "/" + EMAIL_ID + "/forward"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class), eq(options)))
                .thenReturn(new AbstractHttpResponse<>(200, "{\"id\":\"" + SENT_EMAIL_ID + "\"}", true));

        assertEquals(SENT_EMAIL_ID, emails.reply(INBOX_ID, THREAD_ID, EMAIL_ID, InboxesUtil.replyRequest(), options).getId());
        assertEquals(SENT_EMAIL_ID, emails.forward(INBOX_ID, THREAD_ID, EMAIL_ID, InboxesUtil.forwardRequest(), options).getId());
    }

    @Test
    public void testReply_ValidationError_ThrowsResendException() throws ResendException {
        when(httpClient.perform(eq(EMAILS_PATH + "/" + EMAIL_ID + "/reply"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(new AbstractHttpResponse<>(422, "{\"name\":\"validation_error\",\"message\":\"Missing body\"}", false));

        ReplyInboxThreadEmailOptions options = ReplyInboxThreadEmailOptions.builder().text("Thanks").build();
        ResendException ex = assertThrows(ResendException.class, () -> emails.reply(INBOX_ID, THREAD_ID, EMAIL_ID, options));
        assertEquals(422, (int) ex.getStatusCode());
    }
}
