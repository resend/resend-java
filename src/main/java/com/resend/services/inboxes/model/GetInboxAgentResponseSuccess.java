package com.resend.services.inboxes.model;

import java.util.List;

/**
 * Represents a successful response for retrieving the agent settings of an inbox.
 * Extends the InboxAgent class.
 */
public class GetInboxAgentResponseSuccess extends InboxAgent {

    /**
     * Default constructor
     */
    public GetInboxAgentResponseSuccess() {

    }

    /**
     * Constructs a GetInboxAgentResponseSuccess.
     *
     * @param object         The object type. Always {@code inbox_agent}.
     * @param instructions   The instructions the agent follows when it handles the threads of the inbox. Null when no instructions are set.
     * @param tone           The tone the agent writes in. One of the presets {@code concise_professional}, {@code concise_casual}, {@code expanded_professional}, or {@code expanded_casual}, or custom text in the owner's own words, such as {@code friendly and concise}. Older dashboard values such as {@code Concise · Casual} are custom text. Null when no tone is set.
     * @param enabledActions The actions the agent can take. Empty when no actions are enabled.
     */
    public GetInboxAgentResponseSuccess(String object, String instructions, String tone, List<String> enabledActions) {
        super(object, instructions, tone, enabledActions);
    }
}
