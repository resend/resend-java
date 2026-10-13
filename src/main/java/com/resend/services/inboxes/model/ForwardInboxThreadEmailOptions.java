package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.emails.model.Attachment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a request to forward a thread email. {@code to}, {@code cc} and {@code bcc} combined cannot exceed 50 recipients.
 */
public class ForwardInboxThreadEmailOptions {

    @JsonProperty("to")
    private final List<String> to;

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

    @JsonProperty("attachments")
    private final List<Attachment> attachments;

    @JsonProperty("headers")
    private final Map<String, String> headers;

    private ForwardInboxThreadEmailOptions(Builder builder) {
        this.to = builder.to;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.html = builder.html;
        this.text = builder.text;
        this.subject = builder.subject;
        this.attachments = builder.attachments;
        this.headers = builder.headers;
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
     * Get the HTML note sent above the quoted original.
     *
     * @return The HTML note sent above the quoted original.
     */
    public String getHtml() {
        return html;
    }

    /**
     * Get the plain-text note sent above the quoted original.
     *
     * @return The plain-text note sent above the quoted original.
     */
    public String getText() {
        return text;
    }

    /**
     * Get the subject of the forward.
     *
     * @return The subject of the forward.
     */
    public String getSubject() {
        return subject;
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
     * Retrieves the custom headers of the email.
     *
     * @return The custom headers of the email.
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Create a new builder instance for constructing ForwardInboxThreadEmailOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ForwardInboxThreadEmailOptions objects.
     */
    public static class Builder {
        private List<String> to;
        private List<String> cc;
        private List<String> bcc;
        private String html;
        private String text;
        private String subject;
        private List<Attachment> attachments;
        private Map<String, String> headers;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set the recipients. Must name at least one address.
         *
         * @param to The recipients.
         * @return The builder instance.
         */
        public Builder to(String... to) {
            this.to = new ArrayList<>(Arrays.asList(to));
            return this;
        }

        /**
         * Set the recipients. Must name at least one address.
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
         * Set the CC recipients. Counts toward the 50-recipient limit.
         *
         * @param cc The CC recipients.
         * @return The builder instance.
         */
        public Builder cc(String... cc) {
            this.cc = new ArrayList<>(Arrays.asList(cc));
            return this;
        }

        /**
         * Set the CC recipients. Counts toward the 50-recipient limit.
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
         * Set the BCC recipients. Counts toward the 50-recipient limit.
         *
         * @param bcc The BCC recipients.
         * @return The builder instance.
         */
        public Builder bcc(String... bcc) {
            this.bcc = new ArrayList<>(Arrays.asList(bcc));
            return this;
        }

        /**
         * Set the BCC recipients. Counts toward the 50-recipient limit.
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
         * Set the HTML note sent above the quoted original.
         *
         * @param html The HTML note sent above the quoted original.
         * @return The builder instance.
         */
        public Builder html(String html) {
            this.html = html;
            return this;
        }

        /**
         * Set the plain-text note sent above the quoted original.
         *
         * @param text The plain-text note sent above the quoted original.
         * @return The builder instance.
         */
        public Builder text(String text) {
            this.text = text;
            return this;
        }

        /**
         * Set the subject of the forward. Cannot contain a line break. When omitted, the thread subject is used, prefixed with {@code Fwd:} if it does not already start with it.
         *
         * @param subject The subject of the forward.
         * @return The builder instance.
         */
        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Set the files to attach, as on {@code POST /emails}. Each one needs {@code content} or {@code path}. They share one limit with the attachments of the original message, which the forward also sends: up to 10 files and at most 25 MB of {@code content}.
         *
         * @param attachments The files to attach, as on {@code POST /emails}.
         * @return The builder instance.
         */
        public Builder attachments(Attachment... attachments) {
            this.attachments = new ArrayList<>(Arrays.asList(attachments));
            return this;
        }

        /**
         * Set the files to attach, as on {@code POST /emails}. Each one needs {@code content} or {@code path}. They share one limit with the attachments of the original message, which the forward also sends: up to 10 files and at most 25 MB of {@code content}.
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
         * Set the custom headers of the email. The {@code In-Reply-To}, {@code References} and {@code Reply-To} headers are set by Resend and cannot be set here. Sending one of them, in any letter case, returns a 422.
         *
         * @param headers The map of custom headers for the email.
         * @return This builder instance for method chaining.
         */
        public Builder headers(Map<String, String> headers) {
            this.headers = headers == null ? null : new LinkedHashMap<>(headers);
            return this;
        }

        /**
         * Add a single custom header to the email headers. The {@code In-Reply-To}, {@code References} and {@code Reply-To} headers are set by Resend and cannot be set here. Sending one of them, in any letter case, returns a 422.
         *
         * @param key The key of the custom header.
         * @param value The value of the custom header.
         * @return This builder instance for method chaining.
         */
        public Builder addHeader(String key, String value) {
            if (this.headers == null) {
                this.headers = new LinkedHashMap<>();
            }
            this.headers.put(key, value);
            return this;
        }

        /**
         * Build a new ForwardInboxThreadEmailOptions object.
         *
         * @return A new ForwardInboxThreadEmailOptions object.
         * @throws IllegalArgumentException if {@code to} is null or empty.
         */
        public ForwardInboxThreadEmailOptions build() {
            if (to == null || to.isEmpty()) {
                throw new IllegalArgumentException("At least one to recipient must be provided");
            }
            return new ForwardInboxThreadEmailOptions(this);
        }
    }
}
