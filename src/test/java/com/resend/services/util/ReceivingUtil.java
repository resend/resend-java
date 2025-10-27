package com.resend.services.util;

import com.resend.services.receiving.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for creating test data for Receiving service tests.
 */
public class ReceivingUtil {

    /**
     * Creates a sample ReceivedEmail for testing.
     *
     * @return A ReceivedEmail instance.
     */
    public static ReceivedEmail createReceivedEmail() {
        ReceivedEmail email = new ReceivedEmail();
        email.setObject("email");
        email.setId("4ef9a417-02e9-4d39-ad75-9611e0fcc33c");
        email.setTo(Arrays.asList("delivered@resend.dev"));
        email.setFrom("Acme <onboarding@resend.dev>");
        email.setCreatedAt("2023-04-03T22:13:42.674981+00:00");
        email.setSubject("Hello World");
        email.setHtml("Congrats on sending your <strong>first email</strong>!");
        email.setText(null);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("return-path", "lucas.costa@resend.com");
        headers.put("mime-version", "1.0");
        email.setHeaders(headers);

        email.setBcc(new ArrayList<String>());
        email.setCc(new ArrayList<String>());
        email.setReplyTo(new ArrayList<String>());
        email.setMessageId("<example+123>");

        List<ReceivedEmailAttachment> attachments = new ArrayList<ReceivedEmailAttachment>();
        ReceivedEmailAttachment attachment = new ReceivedEmailAttachment();
        attachment.setId("2a0c9ce0-3112-4728-976e-47ddcd16a318");
        attachment.setFilename("avatar.png");
        attachment.setContentType("image/png");
        attachment.setContentDisposition("inline");
        attachment.setContentId("img001");
        attachments.add(attachment);

        email.setAttachments(attachments);
        return email;
    }

    /**
     * Creates a list of sample ReceivedEmails for testing.
     *
     * @return A list of ReceivedEmail instances.
     */
    public static List<ReceivedEmail> createReceivedEmailList() {
        List<ReceivedEmail> emails = new ArrayList<ReceivedEmail>();

        ReceivedEmail email1 = new ReceivedEmail();
        email1.setId("a39999a6-88e3-48b1-888b-beaabcde1b33");
        email1.setTo(Arrays.asList("recipient@example.com"));
        email1.setFrom("sender@example.com");
        email1.setCreatedAt("2025-10-09 14:37:40.951732+00");
        email1.setSubject("Hello World");
        email1.setBcc(new ArrayList<String>());
        email1.setCc(new ArrayList<String>());
        email1.setReplyTo(new ArrayList<String>());
        email1.setMessageId("<111-222-333@email.provider.example.com>");

        ReceivedEmailAttachment attachment1 = new ReceivedEmailAttachment();
        attachment1.setFilename("example.txt");
        attachment1.setContentType("text/plain");
        attachment1.setContentId(null);
        attachment1.setContentDisposition("attachment");
        attachment1.setId("47e999c7-c89c-4999-bf32-aaaaa1c3ff21");
        attachment1.setSize(13L);
        email1.setAttachments(Arrays.asList(attachment1));

        emails.add(email1);
        return emails;
    }

    /**
     * Creates a sample ListReceivedEmailsResponse for testing.
     *
     * @return A ListReceivedEmailsResponse instance.
     */
    public static ListReceivedEmailsResponse createListReceivedEmailsResponse() {
        return new ListReceivedEmailsResponse("list", true, createReceivedEmailList());
    }

    /**
     * Creates a sample AttachmentDetails for testing.
     *
     * @return An AttachmentDetails instance.
     */
    public static AttachmentDetails createAttachmentDetails() {
        AttachmentDetails details = new AttachmentDetails();
        details.setObject("attachment");
        details.setId("2a0c9ce0-3112-4728-976e-47ddcd16a318");
        details.setFilename("avatar.png");
        details.setContentType("image/png");
        details.setContentDisposition("inline");
        details.setContentId("img001");
        details.setDownloadUrl("https://inbound-cdn.resend.com/4ef9a417-02e9-4d39-ad75-9611e0fcc33c/attachments/2a0c9ce0-3112-4728-976e-47ddcd16a318?some-params=example&signature=sig-123");
        details.setExpiresAt("2025-10-17T14:29:41.521Z");
        return details;
    }

    /**
     * Creates a list of sample AttachmentDetails for testing.
     *
     * @return A list of AttachmentDetails instances.
     */
    public static List<AttachmentDetails> createAttachmentDetailsList() {
        List<AttachmentDetails> attachments = new ArrayList<AttachmentDetails>();
        attachments.add(createAttachmentDetails());
        return attachments;
    }

    /**
     * Creates a sample ListAttachmentsResponse for testing.
     *
     * @return A ListAttachmentsResponse instance.
     */
    public static ListAttachmentsResponse createListAttachmentsResponse() {
        return new ListAttachmentsResponse("list", false, createAttachmentDetailsList());
    }
}
