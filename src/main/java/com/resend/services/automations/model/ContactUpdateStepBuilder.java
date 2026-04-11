package com.resend.services.automations.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for creating contact_update automation steps.
 */
public class ContactUpdateStepBuilder extends AbstractStepBuilder<ContactUpdateStepBuilder> {

    private Map<String, Object> properties;

    /**
     * Constructs a ContactUpdateStepBuilder with the specified key.
     *
     * @param key The step key.
     */
    public ContactUpdateStepBuilder(String key) {
        super(key);
    }

    @Override
    protected StepType getType() {
        return StepType.CONTACT_UPDATE;
    }

    /**
     * Sets the first name.
     *
     * @param firstName The first name value or expression.
     * @return The builder instance.
     */
    public ContactUpdateStepBuilder firstName(Object firstName) {
        return addConfig("first_name", firstName);
    }

    /**
     * Sets the last name.
     *
     * @param lastName The last name value or expression.
     * @return The builder instance.
     */
    public ContactUpdateStepBuilder lastName(Object lastName) {
        return addConfig("last_name", lastName);
    }

    /**
     * Sets the unsubscribed flag.
     *
     * @param unsubscribed Whether the contact is unsubscribed.
     * @return The builder instance.
     */
    public ContactUpdateStepBuilder unsubscribed(boolean unsubscribed) {
        return addConfig("unsubscribed", unsubscribed);
    }

    /**
     * Sets the contact properties map.
     *
     * @param properties The properties map.
     * @return The builder instance.
     */
    public ContactUpdateStepBuilder properties(Map<String, Object> properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Adds a single contact property.
     *
     * @param key The property key.
     * @param value The property value.
     * @return The builder instance.
     */
    public ContactUpdateStepBuilder property(String key, Object value) {
        if (this.properties == null) {
            this.properties = new HashMap<>();
        }
        this.properties.put(key, value);
        return this;
    }

    @Override
    public AutomationStep build() {
        if (properties != null && !properties.isEmpty()) {
            config.put("properties", properties);
        }
        return super.build();
    }
}
