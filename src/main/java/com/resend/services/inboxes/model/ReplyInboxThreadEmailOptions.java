package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.emails.model.Attachment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a request to reply to a thread email. At least one of {@code html} or {@code text} must contain more than whitespace.
 */
public class ReplyInboxThreadEmailOptions {

    @JsonProperty("cc")
    private final List<String> cc;

    @JsonProperty("bcc")
    private final List<String> bcc;

    @JsonProperty("html")
    private final String html;

    @JsonProperty("text")
    private final String text;

    @JsonProperty("subject")
    private final String subject;

    @JsonProperty("reply_all")
    private final Boolean replyAll;

    @JsonProperty("attachments")
    private final List<Attachment> attachments;

    private ReplyInboxThreadEmailOptions(Builder builder) {
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.html = builder.html;
        this.text = builder.text;
        this.subject = builder.subject;
        this.replyAll = builder.replyAll;
        this.attachments = builder.attachments;
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
     * Get the HTML body of the reply.
     *
     * @return The HTML body of the reply.
     */
    public String getHtml() {
        return html;
    }

    /**
     * Get the plain-text body of the reply.
     *
     * @return The plain-text body of the reply.
     */
    public String getText() {
        return text;
    }

    /**
     * Get the subject of the reply.
     *
     * @return The subject of the reply.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get whether to reply to everyone on the original message.
     *
     * @return Whether to reply to everyone on the original message.
     */
    public Boolean getReplyAll() {
        return replyAll;
    }

    /**
     * Get the files to attach, as on {@code POST /emails}.
     *
     * @return The files to attach, as on {@code POST /emails}.
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     * Create a new builder instance for constructing ReplyInboxThreadEmailOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ReplyInboxThreadEmailOptions objects.
     */
    public static class Builder {
        private List<String> cc;
        private List<String> bcc;
        private String html;
        private String text;
        private String subject;
        private Boolean replyAll;
        private List<Attachment> attachments;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set the CC recipients. Without {@code reply_all}, the reply copies only these addresses. With {@code reply_all}, these addresses are merged with the copied recipients, without duplicates.
         *
         * @param cc The CC recipients.
         * @return The builder instance.
         */
        public Builder cc(String... cc) {
            this.cc = new ArrayList<>(Arrays.asList(cc));
            return this;
        }

        /**
         * Set the CC recipients. Without {@code reply_all}, the reply copies only these addresses. With {@code reply_all}, these addresses are merged with the copied recipients, without duplicates.
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
         * Set the BCC recipients. BCC recipients of the original message are never copied.
         *
         * @param bcc The BCC recipients.
         * @return The builder instance.
         */
        public Builder bcc(String... bcc) {
            this.bcc = new ArrayList<>(Arrays.asList(bcc));
            return this;
        }

        /**
         * Set the BCC recipients. BCC recipients of the original message are never copied.
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
         * Set the HTML body of the reply.
         *
         * @param html The HTML body of the reply.
         * @return The builder instance.
         */
        public Builder html(String html) {
            this.html = html;
            return this;
        }

        /**
         * Set the plain-text body of the reply.
         *
         * @param text The plain-text body of the reply.
         * @return The builder instance.
         */
        public Builder text(String text) {
            this.text = text;
            return this;
        }

        /**
         * Set the subject of the reply. Cannot contain a line break. When omitted, the thread subject is used, prefixed with {@code Re:} if it does not already start with it.
         *
         * @param subject The subject of the reply.
         * @return The builder instance.
         */
        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Set whether to reply to everyone on the original message. For a message the inbox received, the reply goes to its {@code Reply-To} addresses, or to its sender when it has none, and copies the other addresses of its {@code To} header and its {@code cc} recipients in {@code cc}. For a message the inbox sent, the reply goes to that message's {@code Reply-To} addresses when they are not the inbox address, otherwise to its {@code to} recipients, and copies its {@code cc} recipients. The inbox address is always excluded, and duplicates are removed. Defaults to {@code false}.
         *
         * @param replyAll Whether to reply to everyone on the original message.
         * @return The builder instance.
         */
        public Builder replyAll(Boolean replyAll) {
            this.replyAll = replyAll;
            return this;
        }

        /**
         * Set the files to attach, as on {@code POST /emails}. Each one needs {@code content} or {@code path}. Up to 10 files. The {@code content} of all files can total at most 25 MB.
         *
         * @param attachments The files to attach, as on {@code POST /emails}.
         * @return The builder instance.
         */
        public Builder attachments(Attachment... attachments) {
            this.attachments = new ArrayList<>(Arrays.asList(attachments));
            return this;
        }

        /**
         * Set the files to attach, as on {@code POST /emails}. Each one needs {@code content} or {@code path}. Up to 10 files. The {@code content} of all files can total at most 25 MB.
         *
         * @param attachments The files to attach, as on {@code POST /emails}.
         * @return The builder instance.
         */
        public Builder attachments(List<Attachment> attachments) {
            this.attachments = attachments == null ? null : new ArrayList<>(attachments);
            return this;
        }

        /**
         * Add one value to the files to attach, as on {@code POST /emails}.
         *
         * @param attachment The value to add.
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
         * Build a new ReplyInboxThreadEmailOptions object.
         *
         * @return A new ReplyInboxThreadEmailOptions object.
         * @throws IllegalArgumentException if both {@code html} and {@code text} are null or blank.
         */
        public ReplyInboxThreadEmailOptions build() {
            if (isBlank(html) && isBlank(text)) {
                throw new IllegalArgumentException("At least one of html or text must contain more than whitespace");
            }
            return new ReplyInboxThreadEmailOptions(this);
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
    }
}
