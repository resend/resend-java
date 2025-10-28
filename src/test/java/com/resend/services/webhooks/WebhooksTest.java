package com.resend.services.webhooks;

import com.resend.core.exception.ResendException;
import com.resend.core.net.ListParams;
import com.resend.services.util.WebhooksUtil;
import com.resend.services.webhooks.dto.WebhookDTO;
import com.resend.services.webhooks.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WebhooksTest {

    @Mock
    private Webhooks webhooks;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        webhooks = mock(Webhooks.class);
    }

    @Test
    public void testCreateWebhook_Success() throws ResendException {
        CreateWebhookResponseSuccess expectedResponse = WebhooksUtil.createWebhookResponse();

        CreateWebhookOptions request = WebhooksUtil.createWebhookRequest();
        when(webhooks.create(request))
                .thenReturn(expectedResponse);

        CreateWebhookResponseSuccess response = webhooks.create(request);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getObject(), response.getObject());
        assertEquals(expectedResponse.getSigningSecret(), response.getSigningSecret());
        assertEquals("webhook", response.getObject());
        assertEquals("4dd369bc-aa82-4ff3-97de-514ae3000ee0", response.getId());
    }

    @Test
    public void testUpdateWebhook_Success() throws ResendException {
        UpdateWebhookResponseSuccess expectedResponse = WebhooksUtil.updateWebhookResponse();
        String webhookId = "4dd369bc-aa82-4ff3-97de-514ae3000ee0";

        UpdateWebhookOptions request = WebhooksUtil.updateWebhookRequest();
        when(webhooks.update(webhookId, request))
                .thenReturn(expectedResponse);

        UpdateWebhookResponseSuccess response = webhooks.update(webhookId, request);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getObject(), response.getObject());
        assertEquals("webhook", response.getObject());
        assertEquals("4dd369bc-aa82-4ff3-97de-514ae3000ee0", response.getId());
    }

    @Test
    public void testGetWebhook_Success() throws ResendException {
        GetWebhookResponseSuccess expectedWebhook = WebhooksUtil.getWebhookResponse();

        when(webhooks.get(expectedWebhook.getId()))
                .thenReturn(expectedWebhook);

        GetWebhookResponseSuccess response = webhooks.get(expectedWebhook.getId());

        assertNotNull(response);
        assertEquals(expectedWebhook, response);
        assertEquals(expectedWebhook.getId(), response.getId());
        assertEquals("webhook", response.getObject());
        assertEquals(WebhookStatus.ENABLED, response.getStatus());
        assertEquals("https://webhook.example.com/handler", response.getEndpoint());
    }

    @Test
    public void testListWebhooks_Success() throws ResendException {
        ListWebhooksResponseSuccess expectedResponse = WebhooksUtil.listWebhooksResponse();

        when(webhooks.list()).thenReturn(expectedResponse);

        ListWebhooksResponseSuccess response = webhooks.list();

        assertNotNull(response);
        assertEquals(expectedResponse.getData().size(), response.getData().size());
        assertEquals(2, response.getData().size());
        assertEquals("list", response.getObject());
        assertFalse(response.hasMore());
    }

    @Test
    public void testListWebhooksWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(1).build();
        ListWebhooksResponseSuccess expectedResponse = WebhooksUtil.listWebhooksResponse();
        WebhookDTO paginatedData = expectedResponse.getData().get(0);
        ListWebhooksResponseSuccess paginatedResponse = new ListWebhooksResponseSuccess(
                "list",
                true,
                java.util.Collections.singletonList(paginatedData)
        );

        when(webhooks.list(params)).thenReturn(paginatedResponse);

        ListWebhooksResponseSuccess response = webhooks.list(params);

        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertTrue(response.hasMore());
    }

    @Test
    public void testRemoveWebhook_Success() throws ResendException {
        RemoveWebhookResponseSuccess expectedResponse = WebhooksUtil.removeWebhookResponse();

        when(webhooks.remove(expectedResponse.getId()))
                .thenReturn(expectedResponse);

        RemoveWebhookResponseSuccess response = webhooks.remove(expectedResponse.getId());

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals("webhook", response.getObject());
        assertTrue(response.getDeleted());
    }

    @Test
    public void testVerifyWebhook_Success() throws Exception {
        Webhooks webhooksService = new Webhooks("test-api-key");

        // Test data
        String secret = "whsec_MfKQ9r8GKYqrTwjUPD8ILPZIo2LaLaSw";
        String payload = "{\"type\":\"email.sent\",\"created_at\":\"2024-01-01T00:00:00.000Z\"}";
        String msgId = "msg_test123";
        long currentTimestamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(currentTimestamp);

        // Generate valid signature
        String signedContent = msgId + "." + timestamp + "." + payload;
        String secretKey = secret.substring(6); // Remove "whsec_" prefix
        byte[] decodedSecret = Base64.getDecoder().decode(secretKey);

        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedSecret, "HmacSHA256");
        hmac.init(secretKeySpec);
        byte[] hash = hmac.doFinal(signedContent.getBytes("UTF-8"));
        String signature = "v1," + Base64.getEncoder().encodeToString(hash);

        // Create verification options
        VerifyWebhookOptions options = VerifyWebhookOptions.builder()
                .payload(payload)
                .addHeader("svix-id", msgId)
                .addHeader("svix-timestamp", timestamp)
                .addHeader("svix-signature", signature)
                .secret(secret)
                .build();

        // Should not throw exception
        assertDoesNotThrow(() -> webhooksService.verify(options));
    }

    @Test
    public void testVerifyWebhook_InvalidSignature() {
        Webhooks webhooksService = new Webhooks("test-api-key");

        String secret = "whsec_MfKQ9r8GKYqrTwjUPD8ILPZIo2LaLaSw";
        String payload = "{\"type\":\"email.sent\",\"created_at\":\"2024-01-01T00:00:00.000Z\"}";
        String msgId = "msg_test123";
        long currentTimestamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(currentTimestamp);

        // Invalid signature
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

        // Timestamp from 10 minutes ago (should fail 5-minute tolerance)
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
        // Test that verification works with multiple signatures in the header
        Webhooks webhooksService = new Webhooks("test-api-key");

        String secret = "whsec_MfKQ9r8GKYqrTwjUPD8ILPZIo2LaLaSw";
        String payload = "{\"type\":\"email.delivered\"}";
        String msgId = "msg_multi_sig";
        long currentTimestamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(currentTimestamp);

        // Generate valid signature
        String signedContent = msgId + "." + timestamp + "." + payload;
        String secretKey = secret.substring(6);
        byte[] decodedSecret = Base64.getDecoder().decode(secretKey);

        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedSecret, "HmacSHA256");
        hmac.init(secretKeySpec);
        byte[] hash = hmac.doFinal(signedContent.getBytes("UTF-8"));
        String validSignature = Base64.getEncoder().encodeToString(hash);

        // Multiple signatures: one invalid, one valid
        String multipleSignatures = "v1,invalid_sig v1," + validSignature;

        VerifyWebhookOptions options = VerifyWebhookOptions.builder()
                .payload(payload)
                .addHeader("svix-id", msgId)
                .addHeader("svix-timestamp", timestamp)
                .addHeader("svix-signature", multipleSignatures)
                .secret(secret)
                .build();

        // Should not throw exception (one valid signature is enough)
        assertDoesNotThrow(() -> webhooksService.verify(options));
    }
}
