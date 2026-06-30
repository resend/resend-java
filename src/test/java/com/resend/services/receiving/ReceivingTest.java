package com.resend.services.receiving;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.receiving.model.*;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ReceivingTest {

    private static final String EMAIL_ID = "4ef9a417-02e9-4d39-ad75-9611e0fcc33c";
    private static final String ATTACHMENT_ID = "2a0c9ce0-3112-4728-976e-47ddcd16a318";

    private static final String GET_EMAIL_JSON =
            "{\"object\":\"email\",\"id\":\"" + EMAIL_ID + "\"," +
            "\"to\":[\"delivered@resend.dev\"]," +
            "\"from\":\"Acme <onboarding@resend.dev>\"," +
            "\"created_at\":\"2023-04-03T22:13:42.674981+00:00\"," +
            "\"subject\":\"Hello World\"," +
            "\"html\":\"Congrats on sending your <strong>first email</strong>!\"," +
            "\"bcc\":[],\"cc\":[],\"reply_to\":[]," +
            "\"received_for\":[\"forwarded@example.com\"]," +
            "\"message_id\":\"<example+123>\"," +
            "\"attachments\":[{\"id\":\"" + ATTACHMENT_ID + "\",\"filename\":\"avatar.png\",\"content_type\":\"image/png\",\"content_disposition\":\"inline\",\"content_id\":\"img001\"}]," +
            "\"headers\":{\"return-path\":\"lucas.costa@resend.com\",\"mime-version\":\"1.0\"}}";

    private static final String LIST_EMAILS_JSON =
            "{\"object\":\"list\",\"has_more\":true,\"data\":[" +
            "{\"id\":\"a39999a6-88e3-48b1-888b-beaabcde1b33\"," +
            "\"to\":[\"recipient@example.com\"]," +
            "\"from\":\"sender@example.com\"," +
            "\"created_at\":\"2025-10-09 14:37:40.951732+00\"," +
            "\"subject\":\"Hello World\"," +
            "\"bcc\":[],\"cc\":[],\"reply_to\":[]," +
            "\"message_id\":\"<111-222-333@email.provider.example.com>\"," +
            "\"attachments\":[{\"id\":\"47e999c7-c89c-4999-bf32-aaaaa1c3ff21\",\"filename\":\"example.txt\",\"content_type\":\"text/plain\",\"content_disposition\":\"attachment\",\"size\":13}]}" +
            "]}";

    private static final String GET_ATTACHMENT_JSON =
            "{\"object\":\"attachment\",\"id\":\"" + ATTACHMENT_ID + "\"," +
            "\"filename\":\"avatar.png\",\"content_type\":\"image/png\"," +
            "\"content_disposition\":\"inline\",\"content_id\":\"img001\"," +
            "\"download_url\":\"https://inbound-cdn.resend.com/" + EMAIL_ID + "/attachments/" + ATTACHMENT_ID + "?some-params=example&signature=sig-123\"," +
            "\"expires_at\":\"2025-10-17T14:29:41.521Z\"}";

    private static final String LIST_ATTACHMENTS_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"object\":\"attachment\",\"id\":\"" + ATTACHMENT_ID + "\"," +
            "\"filename\":\"avatar.png\",\"content_type\":\"image/png\"," +
            "\"content_disposition\":\"inline\",\"content_id\":\"img001\"," +
            "\"download_url\":\"https://inbound-cdn.resend.com/" + EMAIL_ID + "/attachments/" + ATTACHMENT_ID + "?some-params=example&signature=sig-123\"," +
            "\"expires_at\":\"2025-10-17T14:29:41.521Z\"}" +
            "]}";

    @Mock
    private IHttpClient httpClient;

    private Receiving receiving;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        receiving = new Receiving("test-api-key", httpClient);
    }

    @Test
    public void testGetReceivedEmail_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_EMAIL_JSON, true);

        when(httpClient.perform(eq("/emails/receiving/" + EMAIL_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ReceivedEmail result = receiving.get(EMAIL_ID);

        assertNotNull(result);
        assertEquals(EMAIL_ID, result.getId());
        assertEquals("Hello World", result.getSubject());
        assertEquals("Acme <onboarding@resend.dev>", result.getFrom());
        assertEquals("<example+123>", result.getMessageId());
        assertEquals("forwarded@example.com", result.getReceivedFor().get(0));
    }

    @Test
    public void testGetReceivedEmail_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"Email not found\"}", false);

        when(httpClient.perform(eq("/emails/receiving/bad_id"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> receiving.get("bad_id"));
        assertEquals(404, (int) ex.getStatusCode());
    }

    @Test
    public void testListReceivedEmails_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_EMAILS_JSON, true);

        when(httpClient.perform(eq("/emails/receiving"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListReceivedEmailsResponse result = receiving.list();

        assertNotNull(result);
        assertEquals(1, result.getData().size());
        assertTrue(result.hasMore());
        assertEquals("list", result.getObject());
    }

    @Test
    public void testListReceivedEmailsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(10).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_EMAILS_JSON, true);

        when(httpClient.perform(startsWith("/emails/receiving?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListReceivedEmailsResponse result = receiving.list(params);

        assertNotNull(result);
        assertEquals(1, result.getData().size());
    }

    @Test
    public void testGetAttachment_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_ATTACHMENT_JSON, true);

        when(httpClient.perform(eq("/emails/receiving/" + EMAIL_ID + "/attachments/" + ATTACHMENT_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        AttachmentDetails result = receiving.getAttachment(EMAIL_ID, ATTACHMENT_ID);

        assertNotNull(result);
        assertEquals(ATTACHMENT_ID, result.getId());
        assertEquals("avatar.png", result.getFilename());
        assertNotNull(result.getDownloadUrl());
        assertEquals("attachment", result.getObject());
    }

    @Test
    public void testListAttachments_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_ATTACHMENTS_JSON, true);

        when(httpClient.perform(eq("/emails/receiving/" + EMAIL_ID + "/attachments"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAttachmentsResponse result = receiving.listAttachments(EMAIL_ID);

        assertNotNull(result);
        assertEquals(1, result.getData().size());
        assertFalse(result.hasMore());
        assertEquals("list", result.getObject());
    }

    @Test
    public void testListAttachmentsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(5).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_ATTACHMENTS_JSON, true);

        when(httpClient.perform(startsWith("/emails/receiving/" + EMAIL_ID + "/attachments?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAttachmentsResponse result = receiving.listAttachments(EMAIL_ID, params);

        assertNotNull(result);
        assertEquals(1, result.getData().size());
    }
}
