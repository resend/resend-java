package com.resend.services.webhooks;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.services.util.WebhooksUtil;
import com.resend.services.webhooks.model.*;
import com.resend.services.webhooks.model.events.EmailDeliveredEvent;
import com.resend.services.webhooks.model.events.EmailReceivedEvent;
import com.resend.services.webhooks.model.events.WebhookEventPayload;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class WebhooksTest {

    private static final String WEBHOOK_ID = "4dd369bc-aa82-4ff3-97de-514ae3000ee0";

    private static final String CREATE_WEBHOOK_JSON =
            "{\"object\":\"webhook\",\"id\":\"" + WEBHOOK_ID + "\",\"signing_secret\":\"whsec_xxxxxxxxxx\"}";

    private static final String UPDATE_WEBHOOK_JSON =
            "{\"object\":\"webhook\",\"id\":\"" + WEBHOOK_ID + "\"}";

    private static final String GET_WEBHOOK_JSON =
            "{\"object\":\"webhook\",\"id\":\"" + WEBHOOK_ID + "\"," +
            "\"created_at\":\"2023-08-22T15:28:00.000Z\",\"status\":\"enabled\"," +
            "\"endpoint\":\"https://webhook.example.com/handler\"," +
            "\"events\":[\"email.sent\",\"email.received\"]," +
            "\"signing_secret\":\"whsec_xxxxxxxxxx\"}";

    private static final String LIST_WEBHOOKS_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"id\":\"7ab123cd-ef45-6789-abcd-ef0123456789\",\"created_at\":\"2023-09-10T10:15:30.000Z\",\"status\":\"disabled\",\"endpoint\":\"https://first-webhook.example.com/handler\",\"events\":[\"email.delivered\",\"email.bounced\"]}," +
            "{\"id\":\"" + WEBHOOK_ID + "\",\"created_at\":\"2023-08-22T15:28:00.000Z\",\"status\":\"enabled\",\"endpoint\":\"https://second-webhook.example.com/receive\",\"events\":[\"email.received\"]}" +
            "]}";

    private static final String REMOVE_WEBHOOK_JSON =
            "{\"object\":\"webhook\",\"id\":\"" + WEBHOOK_ID + "\",\"deleted\":true}";

    @Mock
    private IHttpClient httpClient;

    private Webhooks webhooks;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        webhooks = new Webhooks("test-api-key", httpClient);
    }

    @Test
    public void testCreateWebhook_Success() throws ResendException {
        CreateWebhookOptions request = WebhooksUtil.createWebhookRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CREATE_WEBHOOK_JSON, true);

        when(httpClient.perform(eq("/webhooks"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateWebhookResponseSuccess response = webhooks.create(request);

        assertNotNull(response);
        assertEquals(WEBHOOK_ID, response.getId());
        assertEquals("webhook", response.getObject());
        assertEquals("whsec_xxxxxxxxxx", response.getSigningSecret());
    }

    @Test
    public void testCreateWebhook_ApiError_ThrowsResendException() throws ResendException {
        CreateWebhookOptions request = WebhooksUtil.createWebhookRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid endpoint\"}", false);

        when(httpClient.perform(eq("/webhooks"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> webhooks.create(request));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testUpdateWebhook_Success() throws ResendException {
        UpdateWebhookOptions request = WebhooksUtil.updateWebhookRequest();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_WEBHOOK_JSON, true);

        when(httpClient.perform(eq("/webhooks/" + WEBHOOK_ID), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateWebhookResponseSuccess response = webhooks.update(WEBHOOK_ID, request);

        assertNotNull(response);
        assertEquals(WEBHOOK_ID, response.getId());
        assertEquals("webhook", response.getObject());
    }

    @Test
    public void testGetWebhook_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_WEBHOOK_JSON, true);

        when(httpClient.perform(eq("/webhooks/" + WEBHOOK_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        GetWebhookResponseSuccess response = webhooks.get(WEBHOOK_ID);

        assertNotNull(response);
        assertEquals(WEBHOOK_ID, response.getId());
        assertEquals("webhook", response.getObject());
        assertEquals(WebhookStatus.ENABLED, response.getStatus());
        assertEquals("https://webhook.example.com/handler", response.getEndpoint());
    }

    @Test
    public void testListWebhooks_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_WEBHOOKS_JSON, true);

        when(httpClient.perform(eq("/webhooks"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListWebhooksResponseSuccess response = webhooks.list();

        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals("list", response.getObject());
        assertFalse(response.hasMore());
    }

    @Test
    public void testListWebhooksWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(1).build();
        String paginatedJson =
                "{\"object\":\"list\",\"has_more\":true,\"data\":[" +
                "{\"id\":\"7ab123cd-ef45-6789-abcd-ef0123456789\",\"created_at\":\"2023-09-10T10:15:30.000Z\",\"status\":\"disabled\",\"endpoint\":\"https://first-webhook.example.com/handler\",\"events\":[\"email.delivered\"]}" +
                "]}";
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, paginatedJson, true);

        when(httpClient.perform(startsWith("/webhooks?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListWebhooksResponseSuccess response = webhooks.list(params);

        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertTrue(response.hasMore());
    }

    @Test
    public void testRemoveWebhook_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, REMOVE_WEBHOOK_JSON, true);

        when(httpClient.perform(eq("/webhooks/" + WEBHOOK_ID), anyString(), eq(HttpMethod.DELETE), eq(""), isNull()))
                .thenReturn(httpResponse);

        RemoveWebhookResponseSuccess response = webhooks.remove(WEBHOOK_ID);

        assertNotNull(response);
        assertEquals(WEBHOOK_ID, response.getId());
        assertEquals("webhook", response.getObject());
        assertTrue(response.getDeleted());
    }

    @Test
    public void testVerifyWebhook_Success() throws Exception {
        Webhooks webhooksService = new Webhooks("test-api-key");

        String secret = "whsec_MfKQ9r8GKYqrTwjUPD8ILPZIo2LaLaSw";
        String payload = "{\"type\":\"email.sent\",\"created_at\":\"2024-01-01T00:00:00.000Z\"}";
        String msgId = "msg_test123";
        long currentTimestamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(currentTimestamp);

        String signedContent = msgId + "." + timestamp + "." + payload;
        String secretKey = secret.substring(6);
        byte[] decodedSecret = Base64.getDecoder().decode(secretKey);

        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedSecret, "HmacSHA256");
        hmac.init(secretKeySpec);
        byte[] hash = hmac.doFinal(signedContent.getBytes("UTF-8"));
        String signature = "v1," + Base64.getEncoder().encodeToString(hash);

        VerifyWebhookOptions options = VerifyWebhookOptions.builder()
                .payload(payload)
                .addHeader("svix-id", msgId)
                .addHeader("svix-timestamp", timestamp)
                .addHeader("svix-signature", signature)
                .secret(secret)
                .build();

        assertDoesNotThrow(() -> webhooksService.verify(options));
    }

    @Test
    public void testVerifyWebhook_ReturnsParsedPayloadWithMessageId() throws Exception {
        Webhooks webhooksService = new Webhooks("test-api-key");

        String secret = "whsec_MfKQ9r8GKYqrTwjUPD8ILPZIo2LaLaSw";
        String payload = "{" +
                "\"type\":\"email.delivered\"," +
                "\"created_at\":\"2024-01-01T00:00:00.000Z\"," +
                "\"data\":{" +
                "\"email_id\":\"56761188-7520-42d8-8898-ff6fc54ce618\"," +
                "\"message_id\":\"<111-222-333@email.example.com>\"," +
                "\"from\":\"Acme <onboarding@resend.dev>\"," +
                "\"to\":[\"delivered@resend.dev\"]," +
                "\"subject\":\"Sending this example\"," +
                "\"created_at\":\"2024-01-01T00:00:00.000Z\"" +
                "}" +
                "}";
        String msgId = "msg_test123";
        long currentTimestamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(currentTimestamp);

        String signedContent = msgId + "." + timestamp + "." + payload;
        String secretKey = secret.substring(6);
        byte[] decodedSecret = Base64.getDecoder().decode(secretKey);

        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedSecret, "HmacSHA256");
        hmac.init(secretKeySpec);
        byte[] hash = hmac.doFinal(signedContent.getBytes("UTF-8"));
        String signature = "v1," + Base64.getEncoder().encodeToString(hash);

        VerifyWebhookOptions options = VerifyWebhookOptions.builder()
                .payload(payload)
                .addHeader("svix-id", msgId)
                .addHeader("svix-timestamp", timestamp)
                .addHeader("svix-signature", signature)
                .secret(secret)
                .build();

        WebhookEventPayload event = webhooksService.verify(options);

        assertNotNull(event);
        assertEquals("email.delivered", event.getType());
        assertTrue(event instanceof EmailDeliveredEvent);
        EmailDeliveredEvent deliveredEvent = (EmailDeliveredEvent) event;
        assertEquals("<111-222-333@email.example.com>", deliveredEvent.getData().getMessageId());
        assertEquals("56761188-7520-42d8-8898-ff6fc54ce618", deliveredEvent.getData().getEmailId());
    }

    @Test
    public void testVerifyWebhook_EmailReceived_ReturnsMessageId() throws Exception {
        Webhooks webhooksService = new Webhooks("test-api-key");

        String secret = "whsec_MfKQ9r8GKYqrTwjUPD8ILPZIo2LaLaSw";
        String payload = "{" +
                "\"type\":\"email.received\"," +
                "\"created_at\":\"2024-02-22T23:41:12.126Z\"," +
                "\"data\":{" +
                "\"email_id\":\"56761188-7520-42d8-8898-ff6fc54ce618\"," +
                "\"message_id\":\"<111-222-333@email.example.com>\"," +
                "\"from\":\"sender@example.com\"," +
                "\"to\":[\"support@acme.com\"]," +
                "\"subject\":\"Question about my order\"," +
                "\"created_at\":\"2024-02-22T23:41:12.126Z\"," +
                "\"bcc\":[],\"cc\":[],\"received_for\":[\"support@acme.com\"],\"attachments\":[]" +
                "}" +
                "}";
        String msgId = "msg_received123";
        long currentTimestamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(currentTimestamp);

        String signedContent = msgId + "." + timestamp + "." + payload;
        String secretKey = secret.substring(6);
        byte[] decodedSecret = Base64.getDecoder().decode(secretKey);

        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedSecret, "HmacSHA256");
        hmac.init(secretKeySpec);
        byte[] hash = hmac.doFinal(signedContent.getBytes("UTF-8"));
        String signature = "v1," + Base64.getEncoder().encodeToString(hash);

        VerifyWebhookOptions options = VerifyWebhookOptions.builder()
                .payload(payload)
                .addHeader("svix-id", msgId)
                .addHeader("svix-timestamp", timestamp)
                .addHeader("svix-signature", signature)
                .secret(secret)
                .build();

        WebhookEventPayload event = webhooksService.verify(options);

        assertNotNull(event);
        assertTrue(event instanceof EmailReceivedEvent);
        EmailReceivedEvent receivedEvent = (EmailReceivedEvent) event;
        assertEquals("<111-222-333@email.example.com>", receivedEvent.getData().getMessageId());
    }

    @Test
    public void testVerifyWebhook_InvalidSignature() {
        Webhooks webhooksService = new Webhooks("test-api-key");

        String secret = "whsec_MfKQ9r8GKYqrTwjUPD8ILPZIo2LaLaSw";
        String payload = "{\"type\":\"email.sent\",\"created_at\":\"2024-01-01T00:00:00.000Z\"}";
        String msgId = "msg_test123";
        long currentTimestamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(currentTimestamp);

        String invalidSignature = "v1,invalid_signature_here";

        VerifyWebhookOptions options = VerifyWebhookOptions.builder()
                .payload(payload)
                .addHeader("svix-id", msgId)
                .addHeader("svix-timestamp", timestamp)
                .addHeader("svix-signature", invalidSignature)
                .secret(secret)
                .build();

        ResendException exception = assertThrows(ResendException.class, () -> {
            webhooksService.verify(options);
        });

        assertEquals(401, exception.getStatusCode());
        assertTrue(exception.getMessage().contains("signature verification failed"));
    }

    @Test
    public void testVerifyWebhook_ExpiredTimestamp() {
        Webhooks webhooksService = new Webhooks("test-api-key");

        String secret = "whsec_MfKQ9r8GKYqrTwjUPD8ILPZIo2LaLaSw";
        String payload = "{\"type\":\"email.sent\"}";
        String msgId = "msg_test123";

        long expiredTimestamp = (System.currentTimeMillis() / 1000) - 600;
        String timestamp = String.valueOf(expiredTimestamp);

        VerifyWebhookOptions options = VerifyWebhookOptions.builder()
                .payload(payload)
                .addHeader("svix-id", msgId)
                .addHeader("svix-timestamp", timestamp)
                .addHeader("svix-signature", "v1,dummy_signature")
                .secret(secret)
                .build();

        ResendException exception = assertThrows(ResendException.class, () -> {
            webhooksService.verify(options);
        });

        assertEquals(400, exception.getStatusCode());
        assertTrue(exception.getMessage().contains("outside the tolerance window"));
    }

    @Test
    public void testVerifyWebhook_NullOptions() {
        Webhooks webhooksService = new Webhooks("test-api-key");

        ResendException exception = assertThrows(ResendException.class, () -> {
            webhooksService.verify(null);
        });

        assertEquals(400, exception.getStatusCode());
        assertTrue(exception.getMessage().contains("cannot be null"));
    }

    @Test
    public void testVerifyWebhook_NullPayload() {
        Webhooks webhooksService = new Webhooks("test-api-key");

        VerifyWebhookOptions options = VerifyWebhookOptions.builder()
                .payload(null)
                .addHeader("svix-id", "msg_123")
                .addHeader("svix-timestamp", String.valueOf(System.currentTimeMillis() / 1000))
                .addHeader("svix-signature", "v1,signature")
                .secret("whsec_test")
                .build();

        ResendException exception = assertThrows(ResendException.class, () -> {
            webhooksService.verify(options);
        });

        assertEquals(400, exception.getStatusCode());
        assertTrue(exception.getMessage().contains("payload cannot be null"));
    }

    @Test
    public void testVerifyWebhook_EmptySecret() {
        Webhooks webhooksService = new Webhooks("test-api-key");

        VerifyWebhookOptions options = VerifyWebhookOptions.builder()
                .payload("{\"test\":\"data\"}")
                .addHeader("svix-id", "msg_123")
                .addHeader("svix-timestamp", String.valueOf(System.currentTimeMillis() / 1000))
                .addHeader("svix-signature", "v1,signature")
                .secret("")
                .build();

        ResendException exception = assertThrows(ResendException.class, () -> {
            webhooksService.verify(options);
        });

        assertEquals(400, exception.getStatusCode());
        assertTrue(exception.getMessage().contains("secret cannot be null or empty"));
    }

    @Test
    public void testVerifyWebhook_MultipleSignatures() throws Exception {
        Webhooks webhooksService = new Webhooks("test-api-key");

        String secret = "whsec_MfKQ9r8GKYqrTwjUPD8ILPZIo2LaLaSw";
        String payload = "{\"type\":\"email.delivered\"}";
        String msgId = "msg_multi_sig";
        long currentTimestamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(currentTimestamp);

        String signedContent = msgId + "." + timestamp + "." + payload;
        String secretKey = secret.substring(6);
        byte[] decodedSecret = Base64.getDecoder().decode(secretKey);

        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedSecret, "HmacSHA256");
        hmac.init(secretKeySpec);
        byte[] hash = hmac.doFinal(signedContent.getBytes("UTF-8"));
        String validSignature = Base64.getEncoder().encodeToString(hash);

        String multipleSignatures = "v1,invalid_sig v1," + validSignature;

        VerifyWebhookOptions options = VerifyWebhookOptions.builder()
                .payload(payload)
                .addHeader("svix-id", msgId)
                .addHeader("svix-timestamp", timestamp)
                .addHeader("svix-signature", multipleSignatures)
                .secret(secret)
                .build();

        assertDoesNotThrow(() -> webhooksService.verify(options));
    }
}
