package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents the agent settings of an inbox.
 */
public class InboxAgent {

    @JsonProperty("object")
    private String object;

    @JsonProperty("instructions")
    private String instructions;

    @JsonProperty("tone")
    private String tone;

    @JsonProperty("enabled_actions")
    private List<String> enabledActions;

    /**
     * Default constructor
     */
    public InboxAgent() {

    }

    /**
     * Constructs a InboxAgent.
     *
     * @param object         The object type. Always {@code inbox_agent}.
     * @param instructions   The instructions the agent follows when it handles the threads of the inbox. Null when no instructions are set.
     * @param tone           The tone the agent writes in. One of the presets {@code concise_professional}, {@code concise_casual}, {@code expanded_professional}, or {@code expanded_casual}, or custom text in the owner's own words, such as {@code friendly and concise}. Older dashboard values such as {@code Concise · Casual} are custom text. Null when no tone is set.
     * @param enabledActions The actions the agent can take. Empty when no actions are enabled.
     */
    public InboxAgent(String object, String instructions, String tone, List<String> enabledActions) {
        this.object = object;
        this.instructions = instructions;
        this.tone = tone;
        this.enabledActions = enabledActions;
    }

    /**
     * Get the object type. Always {@code inbox_agent}.
     *
     * @return The object type. Always {@code inbox_agent}.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the instructions the agent follows when it handles the threads of the inbox. Null when no instructions are set.
     *
     * @return The instructions the agent follows when it handles the threads of the inbox. Null when no instructions are set.
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Get the tone the agent writes in. One of the presets {@code concise_professional}, {@code concise_casual}, {@code expanded_professional}, or {@code expanded_casual}, or custom text in the owner's own words, such as {@code friendly and concise}. Older dashboard values such as {@code Concise · Casual} are custom text. Null when no tone is set.
     *
     * @return The tone the agent writes in. One of the presets {@code concise_professional}, {@code concise_casual}, {@code expanded_professional}, or {@code expanded_casual}, or custom text in the owner's own words, such as {@code friendly and concise}. Older dashboard values such as {@code Concise · Casual} are custom text. Null when no tone is set.
     */
    public String getTone() {
        return tone;
    }

    /**
     * Get the actions the agent can take. Empty when no actions are enabled.
     *
     * @return The actions the agent can take. Empty when no actions are enabled.
     */
    public List<String> getEnabledActions() {
        return enabledActions;
    }
}
