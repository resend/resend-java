package com.resend.services.automations.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base class for automation step builders.
 *
 * @param <T> The concrete builder type for fluent method chaining.
 */
public abstract class AbstractStepBuilder<T extends AbstractStepBuilder<T>> {

    /** The step key identifier. */
    protected final String key;

    /** The step configuration map. */
    protected final Map<String, Object> config = new HashMap<>();

    /**
     * Constructs an AbstractStepBuilder with the specified key.
     *
     * @param key The step key identifier.
     */
    protected AbstractStepBuilder(String key) {
        this.key = key;
    }

    /**
     * Returns the step type for this builder.
     *
     * @return The step type.
     */
    protected abstract StepType getType();

    /**
     * Returns this builder instance for fluent chaining.
     *
     * @return This builder instance.
     */
    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    /**
     * Adds a configuration entry.
     *
     * @param name The configuration key.
     * @param value The configuration value.
     * @return This builder instance.
     */
    protected T addConfig(String name, Object value) {
        config.put(name, value);
        return self();
    }

    /**
     * Builds a new AutomationStep instance.
     *
     * @return A new AutomationStep.
     */
    public AutomationStep build() {
        return AutomationStep.builder()
                .key(key)
                .type(getType())
                .config(new HashMap<>(config))
                .build();
    }
}
