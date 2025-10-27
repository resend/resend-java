package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a complete template object.
 */
public class Template {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("status")
    private String status;

    @JsonProperty("published_at")
    private String publishedAt;

    @JsonProperty("from")
    private String from;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("reply_to")
    private List<String> replyTo;

    @JsonProperty("html")
    private String html;

    @JsonProperty("text")
    private String text;

    @JsonProperty("variables")
    private List<Variable> variables;

    /**
     * Default constructor.
     */
    public Template() {
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Gets the ID of the template.
     *
     * @return The ID of the template.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the template.
     *
     * @param id The ID of the template.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the alias of the template.
     *
     * @return The alias of the template.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the alias of the template.
     *
     * @param alias The alias of the template.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets the name of the template.
     *
     * @return The name of the template.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the template.
     *
     * @param name The name of the template.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the creation timestamp of the template.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the template.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last update timestamp of the template.
     *
     * @return The last update timestamp.
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update timestamp of the template.
     *
     * @param updatedAt The last update timestamp.
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the status of the template.
     *
     * @return The status of the template.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the template.
     *
     * @param status The status of the template.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the publication timestamp of the template.
     *
     * @return The publication timestamp.
     */
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * Sets the publication timestamp of the template.
     *
     * @param publishedAt The publication timestamp.
     */
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * Gets the sender email address.
     *
     * @return The sender email address.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the sender email address.
     *
     * @param from The sender email address.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Gets the email subject.
     *
     * @return The email subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the email subject.
     *
     * @param subject The email subject.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the reply-to email addresses.
     *
     * @return The reply-to email addresses.
     */
    public List<String> getReplyTo() {
        return replyTo;
    }

    /**
     * Sets the reply-to email addresses.
     *
     * @param replyTo The reply-to email addresses.
     */
    public void setReplyTo(List<String> replyTo) {
        this.replyTo = replyTo;
    }

    /**
     * Gets the HTML version of the template.
     *
     * @return The HTML version of the template.
     */
    public String getHtml() {
        return html;
    }

    /**
     * Sets the HTML version of the template.
     *
     * @param html The HTML version of the template.
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * Gets the plain text version of the template.
     *
     * @return The plain text version of the template.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the plain text version of the template.
     *
     * @param text The plain text version of the template.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the list of variables used in the template.
     *
     * @return The list of variables.
     */
    public List<Variable> getVariables() {
        return variables;
    }

    /**
     * Sets the list of variables used in the template.
     *
     * @param variables The list of variables.
     */
    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }
}
