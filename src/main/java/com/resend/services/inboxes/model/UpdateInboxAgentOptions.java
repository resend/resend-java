package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * Represents a request to update the agent settings of an inbox.
 * At least one of {@code instructions}, {@code tone}, or {@code actions} must be provided.
 * Omitted fields keep their current value.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class UpdateInboxAgentOptions {

    private final Map<String, Object> fields;

    private UpdateInboxAgentOptions(Builder builder) {
        this.fields = Collections.unmodifiableMap(new LinkedHashMap<>(builder.fields));
    }

    @JsonAnyGetter
    @JsonInclude(content = JsonInclude.Include.ALWAYS)
    private Map<String, Object> getFields() {
        return fields;
    }

    /**
     * Get the instructions the agent follows when it handles the threads of the inbox.
     *
     * @return The instructions, or null when they are not set or are cleared.
     */
    public String getInstructions() {
        return (String) fields.get("instructions");
    }

    /**
     * Get the tone the agent writes in.
     *
     * @return The tone, or null when it is not set or is cleared.
     */
    public InboxAgentTone getTone() {
        return (InboxAgentTone) fields.get("tone");
    }

    /**
     * Get the actions the agent can take.
     *
     * @return The actions, or null when they are not set.
     */
    @SuppressWarnings("unchecked")
    public List<InboxAgentAction> getActions() {
        return (List<InboxAgentAction>) fields.get("actions");
    }

    /**
     * Create a new builder instance for constructing UpdateInboxAgentOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateInboxAgentOptions objects.
     */
    public static class Builder {
        private final Map<String, Object> fields = new LinkedHashMap<>();

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set the instructions the agent follows when it handles the threads of the inbox.
         * Max 4000 characters. Leading and trailing whitespace is trimmed, and the trimmed value must not be empty.
         * Pass null to clear the instructions.
         *
         * @param instructions The instructions, or null to clear them.
         * @return The builder instance.
         */
        public Builder instructions(String instructions) {
            fields.put("instructions", instructions);
            return this;
        }

        /**
         * Set the tone the agent writes in. One of {@code concise_professional}, {@code concise_casual},
         * {@code expanded_professional}, or {@code expanded_casual}.
         * Pass null to clear the tone.
         *
         * @param tone The tone, or null to clear it.
         * @return The builder instance.
         */
        public Builder tone(InboxAgentTone tone) {
            fields.put("tone", tone);
            return this;
        }

        /**
         * Set the actions the agent can take. Replaces the whole set. Duplicates are removed. Pass no actions to disable all actions.
         *
         * @param actions The actions.
         * @return The builder instance.
         */
        public Builder actions(InboxAgentAction... actions) {
            fields.put("actions", new ArrayList<>(new LinkedHashSet<>(Arrays.asList(actions))));
            return this;
        }

        /**
         * Set the actions the agent can take. Replaces the whole set. Duplicates are removed. Pass an empty list to disable all actions.
         * Pass null to leave the actions unchanged.
         *
         * @param actions The actions.
         * @return The builder instance.
         */
        public Builder actions(List<InboxAgentAction> actions) {
            if (actions == null) {
                fields.remove("actions");
            } else {
                fields.put("actions", new ArrayList<>(new LinkedHashSet<>(actions)));
            }
            return this;
        }

        /**
         * Build a new UpdateInboxAgentOptions object.
         *
         * @return A new UpdateInboxAgentOptions object.
         * @throws IllegalArgumentException if no field is set.
         */
        public UpdateInboxAgentOptions build() {
            if (fields.isEmpty()) {
                throw new IllegalArgumentException("At least one of instructions, tone or actions must be provided");
            }
            return new UpdateInboxAgentOptions(this);
        }
    }
}
