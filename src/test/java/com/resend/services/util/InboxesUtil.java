package com.resend.services.util;

import com.resend.services.emails.model.Attachment;
import com.resend.services.inboxes.model.*;

public class InboxesUtil {

    public static final String INBOX_ID = "b3e2b2b6-3f0e-4c8e-9ad3-2f43a1e2c7f1";
    public static final String LABEL_ID = "7f9c1d2e-4a6b-4c3d-8e15-9b0a7c6d5e34";
    public static final String OTHER_LABEL_ID = "2b6d0f4e-8a1c-4e3b-9d57-1c0e8a6f4b21";
    public static final String THREAD_ID = "4d8e2a1c-9b3f-4c6d-8a21-3e5f7c9c0d12";
    public static final String EMAIL_ID = "5b1a9f47-2c8d-4e6f-9a03-1d2e3f4a5b60";
    public static final String SENT_EMAIL_ID = "6a0c8e58-3d9e-4f7a-8b14-2e3f4a5b6c71";
    public static final String DRAFT_ID = "c3a1e8b4-2d5f-4a7c-9e10-6b8d7f5a4c32";

    public static CreateInboxOptions createInboxRequest() {
        return CreateInboxOptions.builder()
                .emailAddress("support@acme.com")
                .name("Customer Support")
                .fromName("Ada from Support")
                .forwarding(true)
                .build();
    }

    public static UpdateInboxOptions updateInboxRequest() {
        return UpdateInboxOptions.builder()
                .fromName("Ada from Support")
                .build();
    }

    public static CreateInboxLabelOptions createLabelRequest() {
        return CreateInboxLabelOptions.builder()
                .name("Urgent")
                .color("#E93D82")
                .build();
    }

    public static UpdateInboxLabelOptions updateLabelRequest() {
        return UpdateInboxLabelOptions.builder()
                .color("#F76B15")
                .build();
    }

    public static ReplyInboxThreadEmailOptions replyRequest() {
        return ReplyInboxThreadEmailOptions.builder()
                .text("Refund issued for order 1041.")
                .cc("billing@acme.com")
                .replyAll(true)
                .addAttachment(Attachment.builder().fileName("receipt.txt").content("cmVjZWlwdA==").build())
                .build();
    }

    public static ForwardInboxThreadEmailOptions forwardRequest() {
        return ForwardInboxThreadEmailOptions.builder()
                .to("colleague@example.org")
                .text("Flagging this refund request for you.")
                .build();
    }

    public static CreateInboxDraftOptions createReplyDraftRequest() {
        return CreateInboxDraftOptions.builder()
                .text("Refund issued for order 1041.")
                .threadId(THREAD_ID)
                .replyToEmailId(EMAIL_ID)
                .build();
    }
}
