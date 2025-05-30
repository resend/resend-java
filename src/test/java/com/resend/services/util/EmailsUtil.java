package com.resend.services.util;

import com.resend.core.net.RequestOptions;
import com.resend.services.batch.model.BatchEmail;
import com.resend.services.batch.model.CreateBatchEmailsResponse;
import com.resend.services.emails.model.*;
import com.resend.core.net.AbstractHttpResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EmailsUtil {

    public static Attachment createAttachment() {
        return Attachment.builder()
                .fileName("invoice.pdf")
                .content("invoice.pdf")
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
        return new CreateBatchEmailsResponse(Arrays.asList(new BatchEmail("123"), new BatchEmail("321")));
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

    public static CreateEmailResponse createSendEmailResponse() {
        return new CreateEmailResponse("mock_id");
    }

    public static AbstractHttpResponse createAbstractHttpResponse() {
        return new AbstractHttpResponse(200, "test", true);

    }
}
