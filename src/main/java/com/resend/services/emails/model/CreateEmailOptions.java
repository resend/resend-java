package com.resend.services.emails.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a request to send an email.
 */
public class CreateEmailOptions {
    @JsonProperty("from")
    private final String from;

    @JsonProperty("to")
    private final List<String> to;

    @JsonProperty("subject")
    private final String subject;

    @JsonProperty("text")
    private final String text;

    @JsonProperty("cc")
    private final List<String> cc;

    @JsonProperty("bcc")
    private final List<String> bcc;

    @JsonProperty("reply_to")
    private final List<String> replyTo;

    @JsonProperty("html")
    private final String html;

    @JsonProperty("headers")
    private final Map<String, String> headers;

    @JsonProperty("attachments")
    private final List<Attachment> attachments;

    @JsonProperty("tags")
    private final List<Tag> tags;

    private CreateEmailOptions(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.text = builder.text;
        this.subject = builder.subject;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.replyTo = builder.replyTo;
        this.attachments = builder.attachments;
        this.tags = builder.tags;
        this.html = builder.html;
        this.headers = builder.headers;
    }

    /**
     * Retrieves the sender's email address.
     *
     * @return The sender's email address.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Retrieves the list of recipients' email addresses.
     *
     * @return The list of recipients' email addresses.
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * Retrieves the plain text version of the message.
     *
     * @return The plain text version of the message.
     */
    public String getText() {
        return text;
    }

    /**
     * Retrieves the email subject.
     *
     * @return The email subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Retrieves the list of recipient email addresses.
     *
     * @return The list of recipient email addresses.
     */
    public List<String> getCc() {
        return cc;
    }

    /**
     * Retrieves the list of recipient email addresses.
     *
     * @return The list of recipient email addresses.
     */
    public List<String> getBcc() {
        return bcc;
    }

    /**
     * Retrieves the plain text version of the message.
     *
     * @return The plain text version of the message.
     */
    public List<String> getReplyTo() {
        return replyTo;
    }

    /**
     * Retrieves the HTML version of the message.
     *
     * @return The HTML version of the message.
     */
    public String getHtml() {
        return html;
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
     * Retrieves the list of attachments of the email.
     *
     * @return The list of attachments of the email.
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     * Retrieves the tags of the email.
     *
     * @return The tags of the email.
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Creates a new builder instance to construct CreateEmailOptions.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateEmailOptions instances.
     */
    public static class Builder {
        private String from;
        private List<String> to;
        private String text;
        private String subject;
        private List<String> cc;
        private List<String> bcc;
        private List<String> replyTo;
        private String html;
        private List<Attachment> attachments;
        private List<Tag> tags;
        private Map<String, String> headers;

        /**
         * Set the 'from' email address.
         *
         * @param from The email address of the sender.
         * @return This builder instance for method chaining.
         */
        public Builder from(String from) {
            this.from = from;
            return this;
        }

        /**
         * Set the list of 'to' email addresses.
         *
         * @param recipients The list of recipients' email addresses.
         * @return This builder instance for method chaining.
         */

        public Builder to(String... recipients) {
            if (this.to == null) {
                this.to = new ArrayList<>();
            }
            for (String recipient : recipients) {
                this.to.add(recipient);
            }
            return this;
        }

        /**
         * Set the list of 'to' email addresses.
         *
         * @param recipients The list of recipients' email addresses.
         * @return This builder instance for method chaining.
         */
        public Builder to(List<String> recipients) {
            this.to = recipients;
            return this;
        }

        /**
         * Add a single recipient email address to the 'to' list.
         *
         * @param recipient The recipient's email address to add.
         * @return This builder instance for method chaining.
         */
        public Builder addTo(String recipient) {
            if (this.to == null) {
                this.to = new ArrayList<>();
            }
            this.to.add(recipient);
            return this;
        }

        /**
         * Set the email body text.
         *
         * @param text The plain text body of the email.
         * @return This builder instance for method chaining.
         */
        public Builder text(String text) {
            this.text = text;
            return this;
        }

        /**
         * Set the subject of the email.
         *
         * @param subject The subject of the email.
         * @return This builder instance for method chaining.
         */
        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Set the list of 'cc' email addresses.
         *
         * @param recipients The list of carbon copy (cc) recipients' email addresses.
         * @return This builder instance for method chaining.
         */
        public Builder cc(String... recipients) {
            if (this.cc == null) {
                this.cc = new ArrayList<>();
            }
            for (String recipient : recipients) {
                this.cc.add(recipient);
            }
            return this;
        }

        /**
         * Set the list of 'cc' email addresses.
         *
         * @param cc The list of carbon copy (cc) recipients' email addresses.
         * @return This builder instance for method chaining.
         */
        public Builder cc(List<String> cc) {
            this.cc = cc;
            return this;
        }

        /**
         * Add a single recipient email address to the 'cc' list.
         *
         * @param recipient The carbon copy (cc) recipient's email address to add.
         * @return This builder instance for method chaining.
         */
        public Builder addCc(String recipient) {
            if (this.cc == null) {
                this.cc = new ArrayList<>();
            }
            this.cc.add(recipient);
            return this;
        }

        /**
         * Set the list of 'bcc' email addresses.
         *
         * @param recipients The list of blind carbon copy (bcc) recipients' email addresses.
         * @return This builder instance for method chaining.
         */

        public Builder bcc(String... recipients) {
            if (this.bcc == null) {
                this.bcc = new ArrayList<>();
            }
            for (String recipient : recipients) {
                this.bcc.add(recipient);
            }
            return this;
        }

        /**
         * Set the list of 'bcc' email addresses.
         *
         * @param recipients The list of blind carbon copy (bcc) recipients' email addresses.
         * @return This builder instance for method chaining.
         */
        public Builder bcc(List<String> recipients) {
            this.bcc = recipients;
            return this;
        }

        /**
         * Add a single recipient email address to the 'bcc' list.
         *
         * @param recipient The blind carbon copy (bcc) recipient's email address to add.
         * @return This builder instance for method chaining.
         */
        public Builder addBcc(String recipient) {
            if (this.bcc == null) {
                this.bcc = new ArrayList<>();
            }
            this.bcc.add(recipient);
            return this;
        }

        /**
         * Set the list of 'replyTo' email addresses.
         *
         * @param recipients The list of reply-to email addresses.
         * @return This builder instance for method chaining.
         */
        public Builder replyTo(String... recipients) {
            if (this.replyTo == null) {
                this.replyTo = new ArrayList<>();
            }
            for (String recipient : recipients) {
                this.replyTo.add(recipient);
            }
            return this;
        }

        /**
         * Set the list of 'replyTo' email addresses.
         *
         * @param recipients The list of reply-to email addresses.
         * @return This builder instance for method chaining.
         */
        public Builder replyTo(List<String> recipients) {
            this.replyTo = replyTo;
            return this;
        }

        /**
         * Add a single recipient email address to the 'replyTo' list.
         *
         * @param recipient The reply-to recipient's email address to add.
         * @return This builder instance for method chaining.
         */
        public Builder addReplyTo(String recipient) {
            if (this.replyTo == null) {
                this.replyTo = new ArrayList<>();
            }
            this.replyTo.add(recipient);
            return this;
        }


        /**
         * Set the HTML content of the email.
         *
         * @param html The HTML content of the email.
         * @return This builder instance for method chaining.
         */
        public Builder html(String html) {
            this.html = html;
            return this;
        }

        /**
         * Set the custom headers of the email.
         *
         * @param headers The map of custom headers for the email.
         * @return This builder instance for method chaining.
         */
        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        /**
         * Add a single custom header to the email headers.
         *
         * @param key The key of the custom header.
         * @param value The value of the custom header.
         * @return This builder instance for method chaining.
         */
        public Builder addHeader(String key, String value) {
            if (this.headers == null) {
                this.headers = new HashMap<>();
            }
            this.headers.put(key, value);
            return this;
        }

        /**
         * Set the list of attachments for the email.
         *
         * @param attachments The list of attachments for the email.
         * @return This builder instance for method chaining.
         */
        public Builder attachments(Attachment... attachments) {
            if (this.attachments == null) {
                this.attachments = new ArrayList<>();
            }
            for (Attachment attachment : attachments) {
                this.attachments.add(attachment);
            }
            return this;
        }

        /**
         * Set the list of attachments for the email.
         *
         * @param attachments The list of attachments for the email.
         * @return This builder instance for method chaining.
         */
        public Builder attachments(List<Attachment> attachments) {
            this.attachments = attachments;
            return this;
        }

        /**
         * Add a single attachment to the list of attachments for the email.
         *
         * @param attachment The attachment to add.
         * @return This builder instance for method chaining.
         */
        public Builder addAttachment(Attachment attachment) {
            if (attachments == null) {
                attachments = new ArrayList<>();
            }
            attachments.add(attachment);
            return this;
        }

        /**
         * Set the list of tags for the email.
         *
         * @param tags The list of tags for the email.
         * @return This builder instance for method chaining.
         */
        public Builder tags(Tag... tags) {
            if (this.tags == null) {
                this.tags = new ArrayList<>();
            }
            for (Tag tag : tags) {
                this.tags.add(tag);
            }
            return this;
        }

        /**
         * Set the list of tags for the email.
         *
         * @param tags The list of tags for the email.
         * @return This builder instance for method chaining.
         */
        public Builder tags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        /**
         * Add a single tag to the list of tags for the email.
         *
         * @param tag The tag to add.
         * @return This builder instance for method chaining.
         */
        public Builder addTag(Tag tag) {
            if (this.tags == null) {
                this.tags = new ArrayList<>();
            }
            this.tags.add(tag);
            return this;
        }

        /**
         * Builds and returns a {@code CreateEmailOptions} based on the configured properties.
         *
         * @return A {@code CreateEmailOptions} instance.
         */
        public CreateEmailOptions build() {
            return new CreateEmailOptions(this);
        }

    }
}

