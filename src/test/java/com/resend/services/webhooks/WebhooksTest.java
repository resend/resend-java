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
}
