package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a complete template object.
 */
public class GetTemplateResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("alias")
    private @Nullable String alias;

    @JsonProperty("name")
    private @Nullable String name;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("updated_at")
    private @Nullable String updatedAt;

    @JsonProperty("status")
    private @Nullable String status;

    @JsonProperty("published_at")
    private @Nullable String publishedAt;

    @JsonProperty("from")
    private @Nullable String from;

    @JsonProperty("subject")
    private @Nullable String subject;

    @JsonProperty("reply_to")
    private @Nullable List<String> replyTo;

    @JsonProperty("html")
    private @Nullable String html;

    @JsonProperty("text")
    private @Nullable String text;

    @JsonProperty("variables")
    private @Nullable List<Variable> variables;

    /**
     * Default constructor.
     */
    public GetTemplateResponseSuccess() {
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(@Nullable String object) {
        this.object = object;
    }

    /**
     * Gets the ID of the template.
     *
     * @return The ID of the template.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Sets the ID of the template.
     *
     * @param id The ID of the template.
     */
    public void setId(@Nullable String id) {
        this.id = id;
    }

    /**
     * Gets the alias of the template.
     *
     * @return The alias of the template.
     */
    public @Nullable String getAlias() {
        return alias;
    }

    /**
     * Sets the alias of the template.
     *
     * @param alias The alias of the template.
     */
    public void setAlias(@Nullable String alias) {
        this.alias = alias;
    }

    /**
     * Gets the name of the template.
     *
     * @return The name of the template.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Sets the name of the template.
     *
     * @param name The name of the template.
     */
    public void setName(@Nullable String name) {
        this.name = name;
    }

    /**
     * Gets the creation timestamp of the template.
     *
     * @return The creation timestamp.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the template.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(@Nullable String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last update timestamp of the template.
     *
     * @return The last update timestamp.
     */
    public @Nullable String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update timestamp of the template.
     *
     * @param updatedAt The last update timestamp.
     */
    public void setUpdatedAt(@Nullable String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the status of the template.
     *
     * @return The status of the template.
     */
    public @Nullable String getStatus() {
        return status;
    }

    /**
     * Sets the status of the template.
     *
     * @param status The status of the template.
     */
    public void setStatus(@Nullable String status) {
        this.status = status;
    }

    /**
     * Gets the publication timestamp of the template.
     *
     * @return The publication timestamp.
     */
    public @Nullable String getPublishedAt() {
        return publishedAt;
    }

    /**
     * Sets the publication timestamp of the template.
     *
     * @param publishedAt The publication timestamp.
     */
    public void setPublishedAt(@Nullable String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * Gets the sender email address.
     *
     * @return The sender email address.
     */
    public @Nullable String getFrom() {
        return from;
    }

    /**
     * Sets the sender email address.
     *
     * @param from The sender email address.
     */
    public void setFrom(@Nullable String from) {
        this.from = from;
    }

    /**
     * Gets the email subject.
     *
     * @return The email subject.
     */
    public @Nullable String getSubject() {
        return subject;
    }

    /**
     * Sets the email subject.
     *
     * @param subject The email subject.
     */
    public void setSubject(@Nullable String subject) {
        this.subject = subject;
    }

    /**
     * Gets the reply-to email addresses.
     *
     * @return The reply-to email addresses.
     */
    public @Nullable List<String> getReplyTo() {
        return replyTo;
    }

    /**
     * Sets the reply-to email addresses.
     *
     * @param replyTo The reply-to email addresses.
     */
    public void setReplyTo(@Nullable List<String> replyTo) {
        this.replyTo = replyTo;
    }

    /**
     * Gets the HTML version of the template.
     *
     * @return The HTML version of the template.
     */
    public @Nullable String getHtml() {
        return html;
    }

    /**
     * Sets the HTML version of the template.
     *
     * @param html The HTML version of the template.
     */
    public void setHtml(@Nullable String html) {
        this.html = html;
    }

    /**
     * Gets the plain text version of the template.
     *
     * @return The plain text version of the template.
     */
    public @Nullable String getText() {
        return text;
    }

    /**
     * Sets the plain text version of the template.
     *
     * @param text The plain text version of the template.
     */
    public void setText(@Nullable String text) {
        this.text = text;
    }

    /**
     * Gets the list of variables used in the template.
     *
     * @return The list of variables.
     */
    public @Nullable List<Variable> getVariables() {
        return variables;
    }

    /**
     * Sets the list of variables used in the template.
     *
     * @param variables The list of variables.
     */
    public void setVariables(@Nullable List<Variable> variables) {
        this.variables = variables;
    }
}
