package com.resend.services.emails;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
import com.resend.services.emails.model.*;
import com.resend.services.util.EmailsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class EmailsTest {

    private static final String EMAIL_ID = "qwert";
    private static final String UPDATE_EMAIL_ID = "123";
    private static final String ATTACHMENT_ID = "2a0c9ce0-3112-4728-976e-47ddcd16a318";
    private static final String ATTACHMENT_EMAIL_ID = "4ef9a417-02e9-4d39-ad75-9611e0fcc33c";

    private static final String SEND_RESPONSE_JSON = "{\"id\":\"mock_id\"}";

    private static final String GET_EMAIL_JSON =
            "{\"object\":\"email_object\",\"id\":\"" + EMAIL_ID + "\"," +
            "\"from\":\"sender@example.com\",\"to\":[\"recipient@example.com\"]," +
            "\"created_at\":\"2023-04-08T00:11:13.110779+00:00\"," +
            "\"subject\":\"Test Email Subject\"," +
            "\"html\":\"<html><body>This is the HTML content</body></html>\"," +
            "\"text\":\"This is the plain text content\"," +
            "\"bcc\":[\"bcc@example.com\"],\"cc\":[\"cc@example.com\"]," +
            "\"reply_to\":[\"replyto@example.com\"],\"last_event\":\"last_event_status\"," +
            "\"message_id\":\"<lc2vu8.gpa9o4@email.example.com>\"}";

    private static final String UPDATE_RESPONSE_JSON = "{\"id\":\"" + UPDATE_EMAIL_ID + "\",\"object\":\"emails\"}";

    private static final String CANCEL_RESPONSE_JSON = "{\"id\":\"" + UPDATE_EMAIL_ID + "\",\"object\":\"emails\"}";

    private static final String LIST_RESPONSE_JSON =
            "{\"object\":\"emails\",\"has_more\":true,\"data\":[" +
            "{\"id\":\"email_1\",\"from\":\"sender1@example.com\"}," +
            "{\"id\":\"email_2\",\"from\":\"sender2@example.com\"}," +
            "{\"id\":\"email_3\",\"from\":\"sender3@example.com\"}" +
            "]}";

    private static final String ATTACHMENT_RESPONSE_JSON =
            "{\"object\":\"attachment\",\"id\":\"" + ATTACHMENT_ID + "\"," +
            "\"filename\":\"avatar.png\",\"size\":4096,\"content_type\":\"image/png\"," +
            "\"download_url\":\"https://outbound-cdn.resend.com/attachments/" + ATTACHMENT_ID + "\"}";

    private static final String LIST_ATTACHMENTS_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"object\":\"attachment\",\"id\":\"" + ATTACHMENT_ID + "\",\"filename\":\"avatar.png\",\"size\":4096,\"content_type\":\"image/png\"}," +
            "{\"object\":\"attachment\",\"id\":\"3b0d9ce0-4223-5839-087f-58eede27b429\",\"filename\":\"invoice.pdf\",\"size\":8192,\"content_type\":\"application/pdf\"}" +
            "]}";

    @Mock
    private IHttpClient httpClient;

    private Emails emails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emails = new Emails("test-api-key", httpClient);
    }

    @Test
    public void testSendEmail_Success() throws ResendException {
        CreateEmailOptions createEmailOptions = EmailsUtil.createEmailOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, SEND_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateEmailResponse response = emails.send(createEmailOptions);

        assertNotNull(response);
        assertEquals("mock_id", response.getId());
    }

    @Test
    public void testSendEmail_ApiError_ThrowsResendException() throws ResendException {
        CreateEmailOptions createEmailOptions = EmailsUtil.createEmailOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid recipient\"}", false);

        when(httpClient.perform(eq("/emails"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> emails.send(createEmailOptions));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testSendEmail_WithIdempotencyKey_Success() throws ResendException {
        CreateEmailOptions createEmailOptions = EmailsUtil.createEmailOptions();
        RequestOptions requestOptions = EmailsUtil.createRequestOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, SEND_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class), any(RequestOptions.class)))
                .thenReturn(httpResponse);

        CreateEmailResponse response = emails.send(createEmailOptions, requestOptions);

        assertNotNull(response);
        assertEquals("mock_id", response.getId());
    }

    @Test
    public void testRetrieveEmail_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_EMAIL_JSON, true);

        when(httpClient.perform(eq("/emails/" + EMAIL_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        Email response = emails.get(EMAIL_ID);

        assertNotNull(response);
        assertEquals(EMAIL_ID, response.getId());
        assertEquals("sender@example.com", response.getFrom());
        assertEquals("<lc2vu8.gpa9o4@email.example.com>", response.getMessageId());
    }

    @Test
    public void testUpdateEmail_Success() throws ResendException {
        UpdateEmailOptions updateEmailOptions = EmailsUtil.updateEmailOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/" + UPDATE_EMAIL_ID), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateEmailResponse response = emails.update(UPDATE_EMAIL_ID, updateEmailOptions);

        assertNotNull(response);
        assertEquals(UPDATE_EMAIL_ID, response.getId());
    }

    @Test
    public void testCancelEmail_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CANCEL_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/" + UPDATE_EMAIL_ID + "/cancel"), anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(httpResponse);

        CancelEmailResponse response = emails.cancel(UPDATE_EMAIL_ID);

        assertNotNull(response);
        assertEquals(UPDATE_EMAIL_ID, response.getId());
    }

    @Test
    public void testListEmails_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListEmailsResponseSuccess response = emails.list();

        assertNotNull(response);
        assertEquals(3, response.getData().size());
    }

    @Test
    public void testListEmailsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(3).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(startsWith("/emails?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListEmailsResponseSuccess response = emails.list(params);

        assertNotNull(response);
        assertEquals(3, response.getData().size());
    }

    @Test
    public void testGetAttachment_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, ATTACHMENT_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/" + ATTACHMENT_EMAIL_ID + "/attachments/" + ATTACHMENT_ID),
                anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        AttachmentResponse response = emails.getAttachment(ATTACHMENT_EMAIL_ID, ATTACHMENT_ID);

        assertNotNull(response);
        assertEquals(ATTACHMENT_ID, response.getId());
        assertEquals("avatar.png", response.getFilename());
        assertEquals(4096, response.getSize());
    }

    @Test
    public void testListAttachments_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_ATTACHMENTS_JSON, true);

        when(httpClient.perform(eq("/emails/" + ATTACHMENT_EMAIL_ID + "/attachments"),
                anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAttachmentsResponse response = emails.listAttachments(ATTACHMENT_EMAIL_ID);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals("list", response.getObject());
    }

    @Test
    public void testListAttachmentsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(10).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_ATTACHMENTS_JSON, true);

        when(httpClient.perform(startsWith("/emails/" + ATTACHMENT_EMAIL_ID + "/attachments?"),
                anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAttachmentsResponse response = emails.listAttachments(ATTACHMENT_EMAIL_ID, params);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
    }

    @Test
    public void testSendEmail_WithTemplate_Success() throws ResendException {
        Template template = Template.builder()
                .id("template_123")
                .addVariable("firstName", "John")
                .build();

        CreateEmailOptions emailWithTemplate = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("john.doe@example.com")
                .subject("Welcome John!")
                .template(template)
                .build();

        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, SEND_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateEmailResponse response = emails.send(emailWithTemplate);

        assertNotNull(response);
        assertEquals("mock_id", response.getId());
    }
}
