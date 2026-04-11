package com.resend.services.automations.model;

/**
 * Builder for creating contact_delete automation steps.
 */
public class ContactDeleteStepBuilder extends AbstractStepBuilder<ContactDeleteStepBuilder> {

    /**
     * Constructs a ContactDeleteStepBuilder with the specified key.
     *
     * @param key The step key.
     */
    public ContactDeleteStepBuilder(String key) {
        super(key);
    }

    @Override
    protected StepType getType() {
        return StepType.CONTACT_DELETE;
    }
}
