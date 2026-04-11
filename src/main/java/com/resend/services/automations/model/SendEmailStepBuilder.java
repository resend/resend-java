package com.resend.services.automations.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for creating send_email automation steps.
 */
public class SendEmailStepBuilder extends AbstractStepBuilder<SendEmailStepBuilder> {

    private String templateId;
    private Map<String, Object> templateVariables;

    /**
     * Constructs a SendEmailStepBuilder with the specified key.
     *
     * @param key The step key.
     */
    public SendEmailStepBuilder(String key) {
        super(key);
    }

    @Override
    protected StepType getType() {
        return StepType.SEND_EMAIL;
    }

    /**
     * Sets the template ID.
     *
     * @param templateId The template ID.
     * @return The builder instance.
     */
    public SendEmailStepBuilder template(String templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * Sets the template variables.
     *
     * @param variables The template variables map.
     * @return The builder instance.
     */
    public SendEmailStepBuilder templateVariables(Map<String, Object> variables) {
        this.templateVariables = variables != null ? new HashMap<>(variables) : null;
        return this;
    }

    /**
     * Adds a single template variable.
     *
     * @param key The variable key.
     * @param value The variable value.
     * @return The builder instance.
     */
    public SendEmailStepBuilder templateVariable(String key, Object value) {
        if (this.templateVariables == null) {
            this.templateVariables = new HashMap<>();
        }
        this.templateVariables.put(key, value);
        return this;
    }

    /**
     * Sets the email subject.
     *
     * @param subject The email subject.
     * @return The builder instance.
     */
    public SendEmailStepBuilder subject(String subject) {
        return addConfig("subject", subject);
    }

    /**
     * Sets the sender email address.
     *
     * @param from The sender email address.
     * @return The builder instance.
     */
    public SendEmailStepBuilder from(String from) {
        return addConfig("from", from);
    }

    /**
     * Sets the reply-to email address.
     *
     * @param replyTo The reply-to email address.
     * @return The builder instance.
     */
    public SendEmailStepBuilder replyTo(String replyTo) {
        return addConfig("reply_to", replyTo);
    }

    @Override
    public AutomationStep build() {
        if (templateId != null) {
            Map<String, Object> template = new HashMap<>();
            template.put("id", templateId);
            if (templateVariables != null && !templateVariables.isEmpty()) {
                template.put("variables", templateVariables);
            }
            config.put("template", template);
        }
        return super.build();
    }
}
