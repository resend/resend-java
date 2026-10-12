package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a request to update a draft.
 * Send at least one of {@code to}, {@code cc}, {@code bcc}, {@code subject}, {@code text} or {@code html}.
 * Omitted fields are left unchanged. Setting {@code text} or {@code html} replaces the whole body,
 * so the body part you omit becomes {@code null}. The merged draft must contain at least one non-empty field,
 * and its combined recipients cannot exceed 50.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class UpdateInboxDraftOptions {

    private final Map<String, Object> fields;

    private UpdateInboxDraftOptions(Builder builder) {
        this.fields = Collections.unmodifiableMap(new LinkedHashMap<>(builder.fields));
    }

    @JsonAnyGetter
    @JsonInclude(content = JsonInclude.Include.ALWAYS)
    private Map<String, Object> getFields() {
        return fields;
    }

    /**
     * Get the recipients.
     *
     * @return The recipients, or null when they are not set or are cleared.
     */
    @SuppressWarnings("unchecked")
    public List<String> getTo() {
        return (List<String>) fields.get("to");
    }

    /**
     * Get the CC recipients.
     *
     * @return The CC recipients, or null when they are not set or are cleared.
     */
    @SuppressWarnings("unchecked")
    public List<String> getCc() {
        return (List<String>) fields.get("cc");
    }

    /**
     * Get the BCC recipients.
     *
     * @return The BCC recipients, or null when they are not set or are cleared.
     */
    @SuppressWarnings("unchecked")
    public List<String> getBcc() {
        return (List<String>) fields.get("bcc");
    }

    /**
     * Get the subject.
     *
     * @return The subject, or null when it is not set or is cleared.
     */
    public String getSubject() {
        return (String) fields.get("subject");
    }

    /**
     * Get the HTML body.
     *
     * @return The HTML body, or null when it is not set or is cleared.
     */
    public String getHtml() {
        return (String) fields.get("html");
    }

    /**
     * Get the plain text body.
     *
     * @return The plain text body, or null when it is not set or is cleared.
     */
    public String getText() {
        return (String) fields.get("text");
    }

    /**
     * Create a new builder instance for constructing UpdateInboxDraftOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateInboxDraftOptions objects.
     */
    public static class Builder {
        private final Map<String, Object> fields = new LinkedHashMap<>();

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set the recipients. Each value can include a friendly name.
         *
         * @param to The recipients.
         * @return The builder instance.
         */
        public Builder to(String... to) {
            fields.put("to", new ArrayList<>(Arrays.asList(to)));
            return this;
        }

        /**
         * Set the recipients. Each value can include a friendly name. Pass null to clear.
         *
         * @param to The recipients, or null to clear them.
         * @return The builder instance.
         */
        public Builder to(List<String> to) {
            fields.put("to", to == null ? null : new ArrayList<>(to));
            return this;
        }

        /**
         * Set the CC recipients.
         *
         * @param cc The CC recipients.
         * @return The builder instance.
         */
        public Builder cc(String... cc) {
            fields.put("cc", new ArrayList<>(Arrays.asList(cc)));
            return this;
        }

        /**
         * Set the CC recipients. Pass null or an empty list to clear.
         *
         * @param cc The CC recipients, or null to clear them.
         * @return The builder instance.
         */
        public Builder cc(List<String> cc) {
            fields.put("cc", cc == null ? null : new ArrayList<>(cc));
            return this;
        }

        /**
         * Set the BCC recipients.
         *
         * @param bcc The BCC recipients.
         * @return The builder instance.
         */
        public Builder bcc(String... bcc) {
            fields.put("bcc", new ArrayList<>(Arrays.asList(bcc)));
            return this;
        }

        /**
         * Set the BCC recipients. Pass null or an empty list to clear.
         *
         * @param bcc The BCC recipients, or null to clear them.
         * @return The builder instance.
         */
        public Builder bcc(List<String> bcc) {
            fields.put("bcc", bcc == null ? null : new ArrayList<>(bcc));
            return this;
        }

        /**
         * Set the subject. Max 2000 characters. Must not contain a line break. Pass null to clear.
         *
         * @param subject The subject, or null to clear it.
         * @return The builder instance.
         */
        public Builder subject(String subject) {
            fields.put("subject", subject);
            return this;
        }

        /**
         * Set the HTML body. Pass null to clear.
         *
         * @param html The HTML body, or null to clear it.
         * @return The builder instance.
         */
        public Builder html(String html) {
            fields.put("html", html);
            return this;
        }

        /**
         * Set the plain text body. Pass null to clear.
         *
         * @param text The plain text body, or null to clear it.
         * @return The builder instance.
         */
        public Builder text(String text) {
            fields.put("text", text);
            return this;
        }

        /**
         * Build a new UpdateInboxDraftOptions object.
         *
         * @return A new UpdateInboxDraftOptions object.
         */
        public UpdateInboxDraftOptions build() {
            return new UpdateInboxDraftOptions(this);
        }
    }
}
