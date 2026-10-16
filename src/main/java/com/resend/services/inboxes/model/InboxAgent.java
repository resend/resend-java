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

    @JsonProperty("actions")
    private List<String> actions;

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
     * @param tone           The tone the agent writes in. One of {@code concise_professional}, {@code concise_casual}, {@code expanded_professional}, or {@code expanded_casual}. Null when no tone is set.
     * @param actions The actions the agent can take. Empty when no actions are enabled.
     */
    public InboxAgent(String object, String instructions, String tone, List<String> actions) {
        this.object = object;
        this.instructions = instructions;
        this.tone = tone;
        this.actions = actions;
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
     * Get the tone the agent writes in. One of {@code concise_professional}, {@code concise_casual}, {@code expanded_professional}, or {@code expanded_casual}. Null when no tone is set.
     *
     * @return The tone the agent writes in. One of {@code concise_professional}, {@code concise_casual}, {@code expanded_professional}, or {@code expanded_casual}. Null when no tone is set.
     */
    public String getTone() {
        return tone;
    }

    /**
     * Get the actions the agent can take. Empty when no actions are enabled.
     *
     * @return The actions the agent can take. Empty when no actions are enabled.
     */
    public List<String> getActions() {
        return actions;
    }
}
