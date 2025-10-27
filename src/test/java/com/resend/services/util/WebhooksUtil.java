package com.resend.services.util;

import com.resend.services.webhooks.dto.WebhookDTO;
import com.resend.services.webhooks.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebhooksUtil {

    public static final CreateWebhookOptions createWebhookRequest() {
        return CreateWebhookOptions.builder()
                .endpoint("https://example.com/webhook")
                .events(
                        WebhookEvent.EMAIL_SENT,
                        WebhookEvent.EMAIL_DELIVERED,
                        WebhookEvent.EMAIL_BOUNCED
                )
                .build();
    }

    public static final CreateWebhookResponseSuccess createWebhookResponse() {
        CreateWebhookResponseSuccess response = new CreateWebhookResponseSuccess();
        response.setObject("webhook");
        response.setId("4dd369bc-aa82-4ff3-97de-514ae3000ee0");
        response.setSigningSecret("whsec_xxxxxxxxxx");
        return response;
    }

    public static final UpdateWebhookOptions updateWebhookRequest() {
        return UpdateWebhookOptions.builder()
                .endpoint("https://example.com/updated-webhook")
                .events(
                        WebhookEvent.EMAIL_SENT,
                        WebhookEvent.EMAIL_DELIVERED
                )
                .status(WebhookStatus.ENABLED)
                .build();
    }

    public static final UpdateWebhookResponseSuccess updateWebhookResponse() {
        UpdateWebhookResponseSuccess response = new UpdateWebhookResponseSuccess();
        response.setObject("webhook");
        response.setId("4dd369bc-aa82-4ff3-97de-514ae3000ee0");
        return response;
    }

    public static final Webhook getWebhookResponse() {
        Webhook webhook = new Webhook();
        webhook.setObject("webhook");
        webhook.setId("4dd369bc-aa82-4ff3-97de-514ae3000ee0");
        webhook.setCreatedAt("2023-08-22T15:28:00.000Z");
        webhook.setStatus(WebhookStatus.ENABLED);
        webhook.setEndpoint("https://webhook.example.com/handler");
        webhook.setEvents(Arrays.asList("email.sent", "email.received"));
        webhook.setSigningSecret("whsec_xxxxxxxxxx");
        return webhook;
    }

    public static final ListWebhooksResponseSuccess listWebhooksResponse() {
        List<WebhookDTO> data = new ArrayList<>();

        WebhookDTO webhook1 = new WebhookDTO(
                "7ab123cd-ef45-6789-abcd-ef0123456789",
                "2023-09-10T10:15:30.000Z",
                WebhookStatus.DISABLED,
                "https://first-webhook.example.com/handler",
                Arrays.asList("email.delivered", "email.bounced")
        );

        WebhookDTO webhook2 = new WebhookDTO(
                "4dd369bc-aa82-4ff3-97de-514ae3000ee0",
                "2023-08-22T15:28:00.000Z",
                WebhookStatus.ENABLED,
                "https://second-webhook.example.com/receive",
                Arrays.asList("email.received")
        );

        data.add(webhook1);
        data.add(webhook2);

        return new ListWebhooksResponseSuccess("list", false, data);
    }

    public static final RemoveWebhookResponseSuccess removeWebhookResponse() {
        return new RemoveWebhookResponseSuccess(
                "webhook",
                "4dd369bc-aa82-4ff3-97de-514ae3000ee0",
                true
        );
    }
}
