package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents options for creating a template.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTemplateOptions {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("alias")
    private final String alias;

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

    @JsonProperty("variables")
    private final List<Variable> variables;

    private CreateTemplateOptions(Builder builder) {
        this.name = builder.name;
        this.alias = builder.alias;
        this.from = builder.from;
        this.subject = builder.subject;
        this.replyTo = builder.replyTo;
        this.html = builder.html;
        this.text = builder.text;
        this.variables = builder.variables;
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
     * Gets the alias of the template.
     *
     * @return The alias of the template.
     */
    public String getAlias() {
        return alias;
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
     * Gets the email subject.
     *
     * @return The email subject.
     */
    public String getSubject() {
        return subject;
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
     * Gets the HTML version of the template.
     *
     * @return The HTML version of the template.
     */
    public String getHtml() {
        return html;
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
     * Gets the list of variables used in the template.
     *
     * @return The list of variables.
     */
    public List<Variable> getVariables() {
        return variables;
    }

    /**
     * Creates a new builder instance to construct CreateTemplateOptions.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateTemplateOptions instances.
     */
    public static class Builder {
        private String name;
        private String alias;
        private String from;
        private String subject;
        private List<String> replyTo;
        private String html;
        private String text;
        private List<Variable> variables;

        /**
         * Sets the name of the template.
         *
         * @param name The name of the template.
         * @return This builder instance for method chaining.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the alias of the template.
         *
         * @param alias The alias of the template.
         * @return This builder instance for method chaining.
         */
        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        /**
         * Sets the sender email address.
         *
         * @param from The sender email address.
         * @return This builder instance for method chaining.
         */
        public Builder from(String from) {
            this.from = from;
            return this;
        }

        /**
         * Sets the email subject.
         *
         * @param subject The email subject.
         * @return This builder instance for method chaining.
         */
        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Sets the reply-to email addresses.
         *
         * @param replyTo The reply-to email addresses.
         * @return This builder instance for method chaining.
         */
        public Builder replyTo(String... replyTo) {
            if (this.replyTo == null) {
                this.replyTo = new ArrayList<>();
            }
            for (String email : replyTo) {
                this.replyTo.add(email);
            }
            return this;
        }

        /**
         * Sets the reply-to email addresses.
         *
         * @param replyTo The reply-to email addresses.
         * @return This builder instance for method chaining.
         */
        public Builder replyTo(List<String> replyTo) {
            this.replyTo = replyTo;
            return this;
        }

        /**
         * Adds a single reply-to email address.
         *
         * @param email The reply-to email address to add.
         * @return This builder instance for method chaining.
         */
        public Builder addReplyTo(String email) {
            if (this.replyTo == null) {
                this.replyTo = new ArrayList<>();
            }
            this.replyTo.add(email);
            return this;
        }

        /**
         * Sets the HTML version of the template.
         *
         * @param html The HTML version of the template.
         * @return This builder instance for method chaining.
         */
        public Builder html(String html) {
            this.html = html;
            return this;
        }

        /**
         * Sets the plain text version of the template.
         *
         * @param text The plain text version of the template.
         * @return This builder instance for method chaining.
         */
        public Builder text(String text) {
            this.text = text;
            return this;
        }

        /**
         * Sets the list of variables used in the template.
         *
         * @param variables The list of variables.
         * @return This builder instance for method chaining.
         */
        public Builder variables(Variable... variables) {
            if (this.variables == null) {
                this.variables = new ArrayList<>();
            }
            for (Variable variable : variables) {
                this.variables.add(variable);
            }
            return this;
        }

        /**
         * Sets the list of variables used in the template.
         *
         * @param variables The list of variables.
         * @return This builder instance for method chaining.
         */
        public Builder variables(List<Variable> variables) {
            this.variables = variables;
            return this;
        }

        /**
         * Adds a single variable to the template.
         *
         * @param variable The variable to add.
         * @return This builder instance for method chaining.
         */
        public Builder addVariable(Variable variable) {
            if (this.variables == null) {
                this.variables = new ArrayList<>();
            }
            this.variables.add(variable);
            return this;
        }

        /**
         * Builds and returns a CreateTemplateOptions instance.
         *
         * @return A CreateTemplateOptions instance.
         */
        public CreateTemplateOptions build() {
            return new CreateTemplateOptions(this);
        }
    }
}
