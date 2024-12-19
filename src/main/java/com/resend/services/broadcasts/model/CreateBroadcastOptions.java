package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

/**
 * Represents a request to create a broadcast email.
 */
public class CreateBroadcastOptions {
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
    private CreateBroadcastOptions(Builder builder) {
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
     * Creates a new Builder instance.
     *
     * @return a new Builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for creating instances of CreateBroadcastOptions.
     */
    public static class Builder {

        private String audienceId;
        private String from;
        private String subject;
        private List<String> replyTo;
        private String html;
        private String text;
        private Object react;
        private String name;

        /**
         * Sets the audience ID.
         *
         * @param audienceId the unique identifier of the audience.
         * @return the builder instance.
         */
        public Builder audienceId(String audienceId) {
            this.audienceId = audienceId;
            return this;
        }

        /**
         * Sets the sender's email address.
         *
         * @param from the sender's email address.
         * @return the builder instance.
         */
        public Builder from(String from) {
            this.from = from;
            return this;
        }

        /**
         * Sets the subject of the email.
         *
         * @param subject the subject line.
         * @return the builder instance.
         */
        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Sets the reply-to email addresses.
         *
         * @param replyTo one or more reply-to email addresses.
         * @return the builder instance.
         */
        public Builder replyTo(String... replyTo) {
            if (this.replyTo == null) {
                this.replyTo = new ArrayList<>();
            }
            this.replyTo.addAll(Arrays.asList(replyTo));
            return this;
        }

        /**
         * Sets the reply-to email addresses as a list.
         *
         * @param replyTo the list of reply-to email addresses.
         * @return the builder instance.
         */
        public Builder replyTo(List<String> replyTo) {
            this.replyTo = replyTo;
            return this;
        }

        /**
         * Sets the HTML content of the email.
         *
         * @param html the HTML content.
         * @return the builder instance.
         */
        public Builder html(String html) {
            this.html = html;
            return this;
        }

        /**
         * Sets the plain text content of the email.
         *
         * @param text the plain text content.
         * @return the builder instance.
         */
        public Builder text(String text) {
            this.text = text;
            return this;
        }

        /**
         * Sets the React component or JSON object for dynamic content.
         *
         * @param react the React component or JSON object.
         * @return the builder instance.
         */
        public Builder react(Object react) {
            this.react = react;
            return this;
        }

        /**
         * Sets the name of the broadcast.
         *
         * @param name the name of the broadcast.
         * @return the builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Builds and returns a CreateBroadcastOptions instance.
         *
         * @return a new CreateBroadcastOptions instance.
         */
        public CreateBroadcastOptions build() {
            return new CreateBroadcastOptions(this);
        }
    }
}
