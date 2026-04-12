package com.resend.services.automations.model;

/**
 * Builder for creating delay automation steps.
 */
public class DelayStepBuilder extends AbstractStepBuilder<DelayStepBuilder> {

    /**
     * Constructs a DelayStepBuilder with the specified key.
     *
     * @param key The step key.
     */
    public DelayStepBuilder(String key) {
        super(key);
    }

    @Override
    protected StepType getType() {
        return StepType.DELAY;
    }

    /**
     * Sets the delay duration.
     *
     * @param duration The duration string (e.g., "1d", "30m", "1h").
     * @return The builder instance.
     */
    public DelayStepBuilder duration(String duration) {
        return addConfig("duration", duration);
    }
}
