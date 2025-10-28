package com.resend.services.util;

import com.resend.core.net.RequestOptions;
import com.resend.services.batch.model.BatchEmail;
import com.resend.services.batch.model.BatchError;
import com.resend.services.batch.model.CreateBatchEmailsResponse;
import com.resend.services.emails.model.*;
import com.resend.core.net.AbstractHttpResponse;

import java.util.*;

public class EmailsUtil {

    public static Attachment createAttachment() {
        return Attachment.builder()
                .fileName("invoice.pdf")
                .content("invoice.pdf")
                .contentType("pdf")
                .contentId("my-image")
                .build();
    }

    public static Tag createTag() {
        return Tag.builder()
                .name("tagName")
                .value("tagValue")
                .build();
    }

    public static CreateEmailOptions createEmailOptions() {
        return CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to(Arrays.asList("example@resend.dev"))
                .cc(Arrays.asList("example@resend.dev"))
                .bcc(Arrays.asList("example@resend.dev"))
                .replyTo(Arrays.asList("example@resend.dev", "example@resend.dev"))
                .text("Hello, this is a text email.")
                .subject("Test email with attachments")
                .attachments(Arrays.asList(createAttachment()))
                .tags(Arrays.asList(createTag()))
                .scheduledAt("2024-08-20T11:52:01.858Z")
                .build();
    }

    public static RequestOptions createRequestOptions() {
        return RequestOptions.builder()
                .setIdempotencyKey("welcome-user/123456789").build();
    }

    public static UpdateEmailOptions updateEmailOptions() {
        return UpdateEmailOptions.builder()
                .scheduledAt("2024-08-20T11:52:01.858Z")
                .build();
    }

    public static UpdateEmailResponse updateEmailResponse() {
        return new UpdateEmailResponse("123", "emails");
    }

    public static CancelEmailResponse cancelEmailResponse() {
        return new CancelEmailResponse("123", "emails");
    }

    public static List<CreateEmailOptions> createBatchEmailOptions() {
        return Arrays.asList(createEmailOptions(), createEmailOptions());
    }

    public static CreateBatchEmailsResponse createBatchEmailsResponse() {
        return new CreateBatchEmailsResponse(Arrays.asList(new BatchEmail("123"), new BatchEmail("321")), new ArrayList<>());
    }

    public static CreateBatchEmailsResponse createPermissiveBatchEmailsResponse() {
        List<BatchEmail> successes = Arrays.asList(
                new BatchEmail("123"),
                new BatchEmail("321")
        );

        List<BatchError> errors = Arrays.asList(
                new BatchError(456, "Invalid recipient address"),
                new BatchError(789, "Domain not reachable")
        );

        return new CreateBatchEmailsResponse(successes, errors);
    }

    public static Email createTestEmail() {
        return new Email(
                "email_object",
                "qwert",
                "sender@example.com",
                Arrays.asList("recipient@example.com"),
                "2023-04-08T00:11:13.110779+00:00",
                "Test Email Subject",
                "<html><body>This is the HTML content</body></html>",
                "This is the plain text content",
                Arrays.asList("bcc@example.com"),
                Arrays.asList("cc@example.com"),
                Arrays.asList("replyto@example.com"),
                "last_event_status"
        );
    }

    public static List<Email> createEmailList() {
        List<Email> emails = new ArrayList<>();

        emails.add(new Email(
                "email_1",
                "qwert1",
                "sender1@example.com",
                Arrays.asList("recipient1@example.com"),
                "2023-04-08T00:11:13.110779+00:00",
                "Test Email Subject 1",
                "<html><body>This is the HTML content 1</body></html>",
                "This is the plain text content 1",
                Arrays.asList("bcc1@example.com"),
                Arrays.asList("cc1@example.com"),
                Arrays.asList("replyto1@example.com"),
                "last_event_status_1"
        ));

        emails.add(new Email(
                "email_2",
                "qwert2",
                "sender2@example.com",
                Arrays.asList("recipient2@example.com"),
                "2023-04-09T00:11:13.110779+00:00",
                "Test Email Subject 2",
                "<html><body>This is the HTML content 2</body></html>",
                "This is the plain text content 2",
                Arrays.asList("bcc2@example.com"),
                Arrays.asList("cc2@example.com"),
                Arrays.asList("replyto2@example.com"),
                "last_event_status_2"
        ));

        emails.add(new Email(
                "email_3",
                "qwert3",
                "sender3@example.com",
                Arrays.asList("recipient3@example.com"),
                "2023-04-10T00:11:13.110779+00:00",
                "Test Email Subject 3",
                "<html><body>This is the HTML content 3</body></html>",
                "This is the plain text content 3",
                Arrays.asList("bcc3@example.com"),
                Arrays.asList("cc3@example.com"),
                Arrays.asList("replyto3@example.com"),
                "last_event_status_3"
        ));

        return emails;
    }

    public static CreateEmailResponse createSendEmailResponse() {
        return new CreateEmailResponse("mock_id");
    }

    public static AbstractHttpResponse createAbstractHttpResponse() {
        return new AbstractHttpResponse(200, "test", true);

    }

    public static AttachmentResponse createAttachmentResponse() {
        AttachmentResponse response = new AttachmentResponse();
        response.setObject("attachment");
        response.setId("2a0c9ce0-3112-4728-976e-47ddcd16a318");
        response.setFilename("avatar.png");
        response.setSize(4096);
        response.setContentType("image/png");
        response.setContentDisposition("inline");
        response.setContentId("img001");
        response.setDownloadUrl("https://outbound-cdn.resend.com/4ef9a417-02e9-4d39-ad75-9611e0fcc33c/attachments/2a0c9ce0-3112-4728-976e-47ddcd16a318?some-params=example&signature=sig-123");
        response.setExpiresAt("2025-10-17T14:29:41.521Z");
        return response;
    }

    public static List<AttachmentResponse> createAttachmentResponseList() {
        List<AttachmentResponse> attachments = new ArrayList<>();

        AttachmentResponse attachment1 = new AttachmentResponse();
        attachment1.setObject("attachment");
        attachment1.setId("2a0c9ce0-3112-4728-976e-47ddcd16a318");
        attachment1.setFilename("avatar.png");
        attachment1.setSize(4096);
        attachment1.setContentType("image/png");
        attachment1.setContentDisposition("inline");
        attachment1.setContentId("img001");
        attachment1.setDownloadUrl("https://outbound-cdn.resend.com/4ef9a417-02e9-4d39-ad75-9611e0fcc33c/attachments/2a0c9ce0-3112-4728-976e-47ddcd16a318?some-params=example&signature=sig-123");
        attachment1.setExpiresAt("2025-10-17T14:29:41.521Z");

        AttachmentResponse attachment2 = new AttachmentResponse();
        attachment2.setObject("attachment");
        attachment2.setId("3b0d9ce0-4223-5839-087f-58eede27b429");
        attachment2.setFilename("invoice.pdf");
        attachment2.setSize(8192);
        attachment2.setContentType("application/pdf");
        attachment2.setContentDisposition("attachment");
        attachment2.setContentId("doc001");
        attachment2.setDownloadUrl("https://outbound-cdn.resend.com/4ef9a417-02e9-4d39-ad75-9611e0fcc33c/attachments/3b0d9ce0-4223-5839-087f-58eede27b429?some-params=example&signature=sig-456");
        attachment2.setExpiresAt("2025-10-17T14:29:41.521Z");

        attachments.add(attachment1);
        attachments.add(attachment2);

        return attachments;
    }

    public static ListAttachmentsResponse createListAttachmentsResponse() {
        return new ListAttachmentsResponse(createAttachmentResponseList(), "list", false);
    }
}
