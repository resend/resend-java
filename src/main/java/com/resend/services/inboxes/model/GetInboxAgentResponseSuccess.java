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
     * @param tone           The tone the agent writes in. One of {@code concise_professional}, {@code concise_casual}, {@code expanded_professional}, or {@code expanded_casual}. Null when no tone is set.
     * @param actions The actions the agent can take. Empty when no actions are enabled.
     */
    public GetInboxAgentResponseSuccess(String object, String instructions, String tone, List<String> actions) {
        super(object, instructions, tone, actions);
    }
}
