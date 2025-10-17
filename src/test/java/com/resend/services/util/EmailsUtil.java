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
}
