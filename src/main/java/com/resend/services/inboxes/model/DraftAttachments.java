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
            if (isEmpty(attachment.getFileName())) {
                throw new IllegalArgumentException("Each draft attachment needs a filename");
            }
            boolean hasContent = attachment.getContent() != null;
            boolean hasPath = attachment.getPath() != null;
            if (hasContent == hasPath) {
                throw new IllegalArgumentException("Each draft attachment needs either content or path, not both");
            }
            if (hasContent && isEmpty(attachment.getContent())) {
                throw new IllegalArgumentException("A draft attachment's content cannot be empty");
            }
            if (hasPath && isEmpty(attachment.getPath())) {
                throw new IllegalArgumentException("A draft attachment's path cannot be empty");
            }
            if (hasContent && isEmpty(attachment.getContentType())) {
                throw new IllegalArgumentException("A draft attachment with content needs a content_type");
            }
            if (attachment.getContentId() != null) {
                throw new IllegalArgumentException("A draft attachment cannot have a content_id");
            }
        }
    }

    private static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
