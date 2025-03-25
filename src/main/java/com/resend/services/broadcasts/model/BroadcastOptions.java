package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Represents a base class for broadcasts.
 */
public class BroadcastOptions {
    @JsonProperty("audience_id")
    private final String audienceId;

    @JsonProperty("from")
    private final String from;

    @JsonProperty("subject")
    private final String subject;

    @JsonProperty("reply_to")
    private final List<String> replyTo;

    @JsonProperty("html")
    private final String html;

    @JsonProperty("text")
    private final String text;

    @JsonProperty("name")
    private final String name;

    /**
     * Private constructor to enforce the use of the builder.
     *
     * @param builder the builder instance used to create this object.
     */
    protected BroadcastOptions(Builder builder) {
        this.audienceId = builder.audienceId;
        this.from = builder.from;
        this.subject = builder.subject;
        this.replyTo = builder.replyTo;
        this.html = builder.html;
        this.text = builder.text;
        this.name = builder.name;
    }

    /**
     * Gets the audience ID.
     *
     * @return the unique identifier of the audience.
     */
    public String getAudienceId() {
        return audienceId;
    }

    /**
     * Gets the sender's email address.
     *
     * @return the sender's email address.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the subject of the email.
     *
     * @return the subject line.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Gets the list of reply-to email addresses.
     *
     * @return the list of reply-to email addresses.
     */
    public List<String> getReplyTo() {
        return replyTo;
    }

    /**
     * Gets the HTML content of the email.
     *
     * @return the HTML content.
     */
    public String getHtml() {
        return html;
    }

    /**
     * Gets the plain text content of the email.
     *
     * @return the plain text content.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the name of the broadcast.
     *
     * @return the name of the broadcast.
     */
    public String getName() {
        return name;
    }

    /**
     * Builder class for creating instances of CreateBroadcastOptions.
     */
    protected static abstract class Builder<T extends BroadcastOptions, B extends Builder<T, B>> {

        /**
         * The ID of the audience targeted by the broadcast.
         */
        protected String audienceId;

        /**
         * The email address of the sender.
         */
        protected String from;

        /**
         * The subject line of the email.
         */
        protected String subject;

        /**
         * A list of email addresses for reply-to responses.
         */
        protected List<String> replyTo;

        /**
         * The HTML content of the email.
         */
        protected String html;

        /**
         * The plain text content of the email.
         */
        protected String text;

        /**
         * The name of the broadcast.
         */
        protected String name;

        /**
         * Sets the audience ID.
         *
         * @param audienceId the unique identifier of the audience.
         * @return the builder instance.
         */
        public B audienceId(String audienceId) {
            this.audienceId = audienceId;
            return self();
        }

        /**
         * Sets the sender's email address.
         *
         * @param from the sender's email address.
         * @return the builder instance.
         */
        public B from(String from) {
            this.from = from;
            return self();
        }

        /**
         * Sets the subject of the email.
         *
         * @param subject the subject line.
         * @return the builder instance.
         */
        public B subject(String subject) {
            this.subject = subject;
            return self();
        }

        /**
         * Sets the reply-to email addresses.
         *
         * @param replyTo one or more reply-to email addresses.
         * @return the builder instance.
         */
        public B replyTo(String... replyTo) {
            if (this.replyTo == null) {
                this.replyTo = new ArrayList<>();
            }
            this.replyTo.addAll(Arrays.asList(replyTo));
            return self();
        }

        /**
         * Sets the reply-to email addresses as a list.
         *
         * @param replyTo the list of reply-to email addresses.
         * @return the builder instance.
         */
        public B replyTo(List<String> replyTo) {
            this.replyTo = replyTo;
            return self();
        }

        /**
         * Sets the HTML content of the email.
         *
         * @param html the HTML content.
         * @return the builder instance.
         */
        public B html(String html) {
            this.html = html;
            return self();
        }

        /**
         * Sets the plain text content of the email.
         *
         * @param text the plain text content.
         * @return the builder instance.
         */
        public B text(String text) {
            this.text = text;
            return self();
        }

        /**
         * Sets the name of the broadcast.
         *
         * @param name the name of the broadcast.
         * @return the builder instance.
         */
        public B name(String name) {
            this.name = name;
            return self();
        }

        /**
         * Abstract method to be implemented by subclasses to create an instance of the corresponding ContactOptions class.
         *
         * @return A new ContactOptions object.
         */
        public abstract T build();

        /**
         * Abstract method to be implemented by subclasses to return the builder instance (used for self-referencing in methods).
         *
         * @return The builder instance.
         */
        protected abstract B self();
    }
}
