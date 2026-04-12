package com.resend.services.automations.model;

/**
 * Builder for creating trigger automation steps.
 */
public class TriggerStepBuilder extends AbstractStepBuilder<TriggerStepBuilder> {

    /**
     * Constructs a TriggerStepBuilder with the specified key.
     *
     * @param key The step key.
     */
    public TriggerStepBuilder(String key) {
        super(key);
    }

    @Override
    protected StepType getType() {
        return StepType.TRIGGER;
    }

    /**
     * Sets the event name that triggers the automation.
     *
     * @param eventName The event name.
     * @return The builder instance.
     */
    public TriggerStepBuilder eventName(String eventName) {
        return addConfig("event_name", eventName);
    }
}
