package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.emails.model.Attachment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a request to create a draft. At least one of {@code to}, {@code cc}, {@code bcc}, {@code subject}, {@code text}, {@code html} or {@code attachments} must be non-empty. The combined recipients of {@code to}, {@code cc} and {@code bcc} cannot exceed 50. {@code thread_id} and {@code reply_to_email_id} must be sent together or not at all.
 */
public class CreateInboxDraftOptions {

    @JsonProperty("to")
    private final List<String> to;

    @JsonProperty("cc")
    private final List<String> cc;

    @JsonProperty("bcc")
    private final List<String> bcc;

    @JsonProperty("subject")
    private final String subject;

    @JsonProperty("html")
    private final String html;

    @JsonProperty("text")
    private final String text;

    @JsonProperty("attachments")
    private final List<Attachment> attachments;

    @JsonProperty("thread_id")
    private final String threadId;

    @JsonProperty("reply_to_email_id")
    private final String replyToEmailId;

    private CreateInboxDraftOptions(Builder builder) {
        this.to = builder.to;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.subject = builder.subject;
        this.html = builder.html;
        this.text = builder.text;
        this.attachments = builder.attachments;
        this.threadId = builder.threadId;
        this.replyToEmailId = builder.replyToEmailId;
    }

    /**
     * Get the recipients.
     *
     * @return The recipients.
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * Get the CC recipients.
     *
     * @return The CC recipients.
     */
    public List<String> getCc() {
        return cc;
    }

    /**
     * Get the BCC recipients.
     *
     * @return The BCC recipients.
     */
    public List<String> getBcc() {
        return bcc;
    }

    /**
     * Get the subject.
     *
     * @return The subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get the HTML body.
     *
     * @return The HTML body.
     */
    public String getHtml() {
        return html;
    }

    /**
     * Get the plain text body.
     *
     * @return The plain text body.
     */
    public String getText() {
        return text;
    }

    /**
     * Get the files to store on the draft.
     *
     * @return The files to store on the draft.
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     * Get the Thread ID to reply in.
     *
     * @return The Thread ID to reply in.
     */
    public String getThreadId() {
        return threadId;
    }

    /**
     * Get the Email ID to reply to, as returned in {@code data[].id} by the list thread emails endpoint.
     *
     * @return The Email ID to reply to, as returned in {@code data[].id} by the list thread emails endpoint.
     */
    public String getReplyToEmailId() {
        return replyToEmailId;
    }

    /**
     * Create a new builder instance for constructing CreateInboxDraftOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateInboxDraftOptions objects.
     */
    public static class Builder {
        private List<String> to;
        private List<String> cc;
        private List<String> bcc;
        private String subject;
        private String html;
        private String text;
        private List<Attachment> attachments;
        private String threadId;
        private String replyToEmailId;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set the recipients. Each value can include a friendly name, for example {@code Ada Lovelace <ada@example.org>}. A reply draft without {@code to} is addressed to the sender of the email it replies to, or to its Reply-To when set. Send an empty list to leave it empty.
         *
         * @param to The recipients.
         * @return The builder instance.
         */
        public Builder to(String... to) {
            this.to = new ArrayList<>(Arrays.asList(to));
            return this;
        }

        /**
         * Set the recipients. Each value can include a friendly name, for example {@code Ada Lovelace <ada@example.org>}. A reply draft without {@code to} is addressed to the sender of the email it replies to, or to its Reply-To when set. Send an empty list to leave it empty.
         *
         * @param to The recipients.
         * @return The builder instance.
         */
        public Builder to(List<String> to) {
            this.to = to == null ? null : new ArrayList<>(to);
            return this;
        }

        /**
         * Add one value to the recipients.
         *
         * @param recipient The value to add.
         * @return The builder instance.
         */
        public Builder addTo(String recipient) {
            if (this.to == null) {
                this.to = new ArrayList<>();
            }
            this.to.add(recipient);
            return this;
        }

        /**
         * Set the CC recipients.
         *
         * @param cc The CC recipients.
         * @return The builder instance.
         */
        public Builder cc(String... cc) {
            this.cc = new ArrayList<>(Arrays.asList(cc));
            return this;
        }

        /**
         * Set the CC recipients.
         *
         * @param cc The CC recipients.
         * @return The builder instance.
         */
        public Builder cc(List<String> cc) {
            this.cc = cc == null ? null : new ArrayList<>(cc);
            return this;
        }

        /**
         * Add one value to the CC recipients.
         *
         * @param recipient The value to add.
         * @return The builder instance.
         */
        public Builder addCc(String recipient) {
            if (this.cc == null) {
                this.cc = new ArrayList<>();
            }
            this.cc.add(recipient);
            return this;
        }

        /**
         * Set the BCC recipients.
         *
         * @param bcc The BCC recipients.
         * @return The builder instance.
         */
        public Builder bcc(String... bcc) {
            this.bcc = new ArrayList<>(Arrays.asList(bcc));
            return this;
        }

        /**
         * Set the BCC recipients.
         *
         * @param bcc The BCC recipients.
         * @return The builder instance.
         */
        public Builder bcc(List<String> bcc) {
            this.bcc = bcc == null ? null : new ArrayList<>(bcc);
            return this;
        }

        /**
         * Add one value to the BCC recipients.
         *
         * @param recipient The value to add.
         * @return The builder instance.
         */
        public Builder addBcc(String recipient) {
            if (this.bcc == null) {
                this.bcc = new ArrayList<>();
            }
            this.bcc.add(recipient);
            return this;
        }

        /**
         * Set the subject. Max 2000 characters. A string that is only whitespace, or that contains a line break, is rejected.
         *
         * @param subject The subject.
         * @return The builder instance.
         */
        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Set the HTML body.
         *
         * @param html The HTML body.
         * @return The builder instance.
         */
        public Builder html(String html) {
            this.html = html;
            return this;
        }

        /**
         * Set the plain text body.
         *
         * @param text The plain text body.
         * @return The builder instance.
         */
        public Builder text(String text) {
            this.text = text;
            return this;
        }

        /**
         * Set the files to store on the draft. Each one needs {@code content}, {@code filename} and
         * {@code content_type}. A {@code path} or {@code content_id} is not accepted. A draft can carry up to
         * 10 files and 25 MB in total.
         *
         * @param attachments The files to store on the draft.
         * @return The builder instance.
         */
        public Builder attachments(Attachment... attachments) {
            this.attachments = new ArrayList<>(Arrays.asList(attachments));
            return this;
        }

        /**
         * Set the files to store on the draft. Each one needs {@code content}, {@code filename} and
         * {@code content_type}. A {@code path} or {@code content_id} is not accepted. A draft can carry up to
         * 10 files and 25 MB in total.
         *
         * @param attachments The files to store on the draft.
         * @return The builder instance.
         */
        public Builder attachments(List<Attachment> attachments) {
            this.attachments = attachments == null ? null : new ArrayList<>(attachments);
            return this;
        }

        /**
         * Add one file to store on the draft.
         *
         * @param attachment The file to add.
         * @return The builder instance.
         */
        public Builder addAttachment(Attachment attachment) {
            if (this.attachments == null) {
                this.attachments = new ArrayList<>();
            }
            this.attachments.add(attachment);
            return this;
        }

        /**
         * Set the Thread ID to reply in. Must be sent with {@code reply_to_email_id}.
         *
         * @param threadId The Thread ID to reply in.
         * @return The builder instance.
         */
        public Builder threadId(String threadId) {
            this.threadId = threadId;
            return this;
        }

        /**
         * Set the Email ID to reply to, as returned in {@code data[].id} by the list thread emails endpoint. Must be sent with {@code thread_id}.
         *
         * @param replyToEmailId The Email ID to reply to, as returned in {@code data[].id} by the list thread emails endpoint.
         * @return The builder instance.
         */
        public Builder replyToEmailId(String replyToEmailId) {
            this.replyToEmailId = replyToEmailId;
            return this;
        }

        /**
         * Build a new CreateInboxDraftOptions object.
         *
         * @return A new CreateInboxDraftOptions object.
         * @throws IllegalArgumentException if every content field is empty, an attachment lacks {@code content}, {@code filename} or {@code content_type} or has a {@code path} or {@code content_id}, or only one of {@code thread_id} and {@code reply_to_email_id} is set.
         */
        public CreateInboxDraftOptions build() {
            if (isEmpty(to) && isEmpty(cc) && isEmpty(bcc) && isBlank(subject) && isBlank(text) && isBlank(html)
                    && (attachments == null || attachments.isEmpty())) {
                throw new IllegalArgumentException("At least one of to, cc, bcc, subject, text, html or attachments must be non-empty");
            }
            DraftAttachments.validate(attachments);
            if ((threadId == null) != (replyToEmailId == null)) {
                throw new IllegalArgumentException("thread_id and reply_to_email_id must be provided together");
            }
            return new CreateInboxDraftOptions(this);
        }

        private static boolean isBlank(String value) {
            if (value == null) {
                return true;
            }
            for (int i = 0; i < value.length(); i++) {
                char c = value.charAt(i);
                if (!Character.isWhitespace(c) && !Character.isSpaceChar(c)) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isEmpty(List<String> values) {
            return values == null || values.isEmpty();
        }
    }
}
