package com.resend.services.automations.model;

import org.jspecify.annotations.Nullable;


/**
 * Builder for creating wait_for_event automation steps.
 */
public class WaitForEventStepBuilder extends AbstractStepBuilder<WaitForEventStepBuilder> {

    /**
     * Constructs a WaitForEventStepBuilder with the specified key.
     *
     * @param key The step key.
     */
    public WaitForEventStepBuilder(String key) {
        super(key);
    }

    @Override
    protected StepType getType() {
        return StepType.WAIT_FOR_EVENT;
    }

    /**
     * Sets the event name to wait for.
     *
     * @param eventName The event name.
     * @return The builder instance.
     */
    public WaitForEventStepBuilder eventName(String eventName) {
        return addConfig("event_name", eventName);
    }

    /**
     * Sets the timeout duration.
     *
     * @param timeout The timeout string (e.g., "7d", "1h").
     * @return The builder instance.
     */
    public WaitForEventStepBuilder timeout(@Nullable String timeout) {
        return addConfig("timeout", timeout);
    }

    /**
     * Sets the filter rule for the event.
     *
     * @param filterRule The filter rule.
     * @return The builder instance.
     */
    public WaitForEventStepBuilder filterRule(@Nullable FilterRule filterRule) {
        config.put("filter_rule", filterRule != null ? filterRule.toMap() : null);
        return this;
    }
}
