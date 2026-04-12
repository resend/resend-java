package com.resend.services.automations.model;

/**
 * Builder for creating condition automation steps.
 */
public class ConditionStepBuilder extends AbstractStepBuilder<ConditionStepBuilder> {

    private ConditionRule rule;

    /**
     * Constructs a ConditionStepBuilder with the specified key.
     *
     * @param key The step key.
     */
    public ConditionStepBuilder(String key) {
        super(key);
    }

    @Override
    protected StepType getType() {
        return StepType.CONDITION;
    }

    /**
     * Sets the condition rule.
     *
     * @param rule The condition rule.
     * @return The builder instance.
     */
    public ConditionStepBuilder rule(ConditionRule rule) {
        this.rule = rule;
        return this;
    }

    @Override
    public AutomationStep build() {
        return AutomationStep.builder()
                .key(key)
                .type(getType())
                .config(rule != null ? rule.toMap() : config)
                .build();
    }
}
