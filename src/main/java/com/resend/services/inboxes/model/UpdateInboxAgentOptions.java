package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a request to update the agent settings of an inbox.
 * At least one of {@code instructions}, {@code tone}, or {@code enabled_actions} must be provided.
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
    public String getTone() {
        return (String) fields.get("tone");
    }

    /**
     * Get the actions the agent can take.
     *
     * @return The enabled actions, or null when they are not set.
     */
    @SuppressWarnings("unchecked")
    public List<InboxAgentAction> getEnabledActions() {
        return (List<InboxAgentAction>) fields.get("enabled_actions");
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
         * Set the tone the agent writes in. Use one of the presets {@code concise_professional}, {@code concise_casual},
         * {@code expanded_professional}, or {@code expanded_casual}, or describe the tone in your own words, such as
         * {@code friendly and concise}. Custom text is max 64 characters. Leading and trailing whitespace is trimmed,
         * and the trimmed value must not be empty.
         * Pass null to clear the tone.
         *
         * @param tone The tone, or null to clear it.
         * @return The builder instance.
         */
        public Builder tone(String tone) {
            fields.put("tone", tone);
            return this;
        }

        /**
         * Set the actions the agent can take. Replaces the whole set. Duplicates are removed. Pass no actions to disable all actions.
         *
         * @param enabledActions The enabled actions.
         * @return The builder instance.
         */
        public Builder enabledActions(InboxAgentAction... enabledActions) {
            fields.put("enabled_actions", new ArrayList<>(Arrays.asList(enabledActions)));
            return this;
        }

        /**
         * Set the actions the agent can take. Replaces the whole set. Duplicates are removed. Pass an empty list to disable all actions.
         * Pass null to leave the actions unchanged.
         *
         * @param enabledActions The enabled actions.
         * @return The builder instance.
         */
        public Builder enabledActions(List<InboxAgentAction> enabledActions) {
            if (enabledActions == null) {
                fields.remove("enabled_actions");
            } else {
                fields.put("enabled_actions", new ArrayList<>(enabledActions));
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
                throw new IllegalArgumentException("At least one of instructions, tone or enabled_actions must be provided");
            }
            return new UpdateInboxAgentOptions(this);
        }
    }
}
