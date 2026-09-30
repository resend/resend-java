package com.resend.services.automations.model;

import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a filter rule for wait_for_event steps.
 */
public class FilterRule {

    private final Map<String, @Nullable Object> rule;

    private FilterRule(Map<String, @Nullable Object> rule) {
        this.rule = Collections.<String, @Nullable Object>unmodifiableMap(new HashMap<>(rule));
    }

    /**
     * Returns the filter rule as a Map.
     *
     * @return An unmodifiable copy of the filter rule map.
     */
    public Map<String, @Nullable Object> toMap() {
        return rule;
    }

    /**
     * Creates a new builder instance.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing FilterRule objects.
     */
    public static class Builder {

        private @Nullable String type = "rule";
        private @Nullable String field;
        private @Nullable String operator;
        private @Nullable Object value;

        /**
         * Constructs a new Builder instance.
         */
        public Builder() {}

        /**
         * Sets the rule type.
         *
         * @param type The rule type, or {@code null} to leave it unset.
         * @return The builder instance.
         */
        public Builder type(@Nullable String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the field to filter on.
         *
         * @param field The field name.
         * @return The builder instance.
         */
        public Builder field(String field) {
            this.field = field;
            return this;
        }

        /**
         * Sets the comparison operator.
         *
         * @param operator The operator (e.g., "eq", "neq", "gt", "lt").
         * @return The builder instance.
         */
        public Builder operator(String operator) {
            this.operator = operator;
            return this;
        }

        /**
         * Sets the value to compare against.
         *
         * @param value The comparison value, or {@code null} to leave it unset.
         * @return The builder instance.
         */
        public Builder value(@Nullable Object value) {
            this.value = value;
            return this;
        }

        /**
         * Builds a new FilterRule instance.
         *
         * @return A new FilterRule.
         */
        public FilterRule build() {
            Map<String, @Nullable Object> rule = new HashMap<>();
            rule.put("type", type);
            rule.put("field", field);
            rule.put("operator", operator);
            rule.put("value", value);
            return new FilterRule(rule);
        }
    }
}
