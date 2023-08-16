package com.resend;

import com.resend.services.emails.model.*;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.provider.AuthenticationProvider;
import com.resend.core.provider.impl.AuthenticationProviderStandard;

import java.time.OffsetDateTime;
import java.util.Arrays;

public class ResendUtil {

    public static AuthenticationProvider createAuthenticationProvider() {
        String apiKey = "re_";
        return new AuthenticationProviderStandard(apiKey);
    }
    public static Attachment createAttachment() {
        return Attachment.builder()
                .fileName("invoice.pdf")
                .content("invoice.pdf".getBytes())
                .build();
    }

    public static Tag createTag() {
        return Tag.builder()
                .name("tagName")
                .value("tagValue")
                .build();
    }

    public static SendEmailRequest createSendEmailRequest() {
        return SendEmailRequest.builder()
                .from("Acme <onboarding@resend.dev>")
                .to(Arrays.asList("example@resend.dev"))
                .cc(Arrays.asList("example@resend.dev"))
                .bcc(Arrays.asList("example@resend.dev"))
                .replyTo(Arrays.asList("example@resend.dev", "example@resend.dev"))
                .text("Hello, this is a text email.")
                .subject("Test email with attachments")
                .attachments(Arrays.asList(createAttachment()))
                .tags(Arrays.asList(createTag()))
                .build();
    }

    public static Email createTestEmail() {
        return new Email(
                "email_object",
                "qwert",
                "sender@example.com",
                Arrays.asList("recipient@example.com"),
                OffsetDateTime.now(),
                "Test Email Subject",
                "<html><body>This is the HTML content</body></html>",
                "This is the plain text content",
                Arrays.asList("bcc@example.com"),
                Arrays.asList("cc@example.com"),
                Arrays.asList("replyto@example.com"),
                "last_event_status"
        );
    }

    public static SendEmailResponse createSendEmailResponse() {
        return new SendEmailResponse("mock_id");
    }

    public static AbstractHttpResponse createAbstractHttpResponse() {
        return new AbstractHttpResponse(200, "test", true);

    }
}
