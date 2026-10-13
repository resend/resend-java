package com.resend.services.inboxes.model;

import com.resend.services.emails.model.Attachment;

import java.util.List;

final class DraftAttachments {

    private DraftAttachments() {
    }

    static void validate(List<Attachment> attachments) {
        if (attachments == null) {
            return;
        }
        for (Attachment attachment : attachments) {
            if (attachment == null) {
                throw new IllegalArgumentException("attachments must not contain null");
            }
            if (isEmpty(attachment.getContent()) || isEmpty(attachment.getFileName()) || isEmpty(attachment.getContentType())) {
                throw new IllegalArgumentException("Each draft attachment needs content, filename and content_type");
            }
            if (attachment.getPath() != null || attachment.getContentId() != null) {
                throw new IllegalArgumentException("A draft attachment cannot have a path or content_id");
            }
        }
    }

    private static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
