package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for updating the agent settings of an inbox.
 */
public class UpdateInboxAgentResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor
     */
    public UpdateInboxAgentResponseSuccess() {

    }

    /**
     * Constructs a UpdateInboxAgentResponseSuccess.
     *
     * @param object The object type. Always {@code inbox_agent}.
     * @param id     The ID of the inbox agent.
     */
    public UpdateInboxAgentResponseSuccess(String object, String id) {
        this.object = object;
        this.id = id;
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
     * Get the ID of the inbox agent.
     *
     * @return The ID of the inbox agent.
     */
    public String getId() {
        return id;
    }
}
